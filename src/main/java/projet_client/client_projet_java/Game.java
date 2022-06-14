package projet_client.client_projet_java;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import projet_client.client_projet_java.fichier.GestionFichier;
import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;
import projet_client.client_projet_java.modeles.BarreVie;
import projet_client.client_projet_java.modeles.TimerGfx;
import projet_client.client_projet_java.modeles.entity.Archer;
import projet_client.client_projet_java.modeles.entity.FabriqueEntity;

import java.io.IOException;

public class Game extends AnimationTimer {

    public static final int WIDTH = 600; // largeur de la fenetre
    public static final int HEIGHT = WIDTH / 16 * 9; // hauteur de la fenetre
    public static final int SCALE = 3; // échelle de la fenetre
    public static final String NAME = "Operation : Ninja"; // nom de la fenetre
    private final Archer archer; // archer : Joueur 1
    private final Archer adversaire; // adversaire : Joueur 2
    private final BarreVie yourLife; // barre de vie du joueur 1
    private final BarreVie adversaireLife; // barre de vie du joueur 2
    private final TimerGfx timerGfx; // timer du jeu
    private final GraphicsContext graphics; // gestion des graphiques
    private final Stage stage; // gestion de la fenetre
    private boolean isGameFinish; // est-ce que le jeu est fini ?

    // constructeur d'une partie
    public Game(Stage stage, String joueur1, String joueur2) {
        super();
        this.stage = stage; // on recupere la fenetre
        this.isGameFinish = false; // on initialise le jeu comme non fini
        Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE); // on cree le canvas
        this.graphics = canvas.getGraphicsContext2D(); // on recupere le contexte du canvas

        Scene scene = new Scene(new StackPane(canvas), WIDTH* SCALE, HEIGHT* SCALE); // on cree la scene

        stage.setTitle(NAME); // on set le nom de la fenetre
        stage.setScene(scene); // on set la scene
        stage.show(); // on affiche la fenetre

        // touches du clavier
        Keyboard keyboard = new Keyboard(scene); // on cree le clavier
        this.archer = (Archer) FabriqueEntity.fabrique(this, joueur1, 10, 50,225,100, keyboard, "droite"); // on cree le joueur 1
        this.yourLife = new BarreVie(this, this.archer.getLife(), this.archer.getLifeMax(), 850, 100, "droite"); // on cree la barre de vie du joueur 1
        this.timerGfx = new TimerGfx(850, 80); // on cree le timer du jeu
        this.adversaire = (Archer) FabriqueEntity.fabrique(this, joueur2, 10, 450,225,100, keyboard, "gauche"); // on cree le joueur 2
        this.adversaireLife = new BarreVie(this, this.adversaire.getLife(), this.adversaire.getLifeMax(), 850, 100, "gauche"); // on cree la barre de vie du joueur 2
    }

    // Getters
    public Archer getArcher() {
        return archer;
    }

    public Archer getAdversaire() {
        return adversaire;
    }

    // Permet d'initilialiser la partie automatiquement
    @Override
    public void handle(long now) {
        this.run(graphics);
    }

    // Permet de mettre a jour les informations de l'objet en boucle
    public void run(GraphicsContext graphics) {
        this.update();
        try {
            this.render(graphics);
        } catch (IOException | InterruptedException ignored) {}
    }

    // Permet de mettre a jour les informations de l'objet
    public void update() {
        this.archer.update(); // on met a jour le joueur 1
        this.adversaire.update(); // on met a jour le joueur 2
        this.yourLife.update(); // on met a jour la barre de vie du joueur 1
        this.adversaireLife.update(); // on met a jour la barre de vie du joueur 2

        if(!this.isGameFinish) this.conditionVictoire(); // on verifie si le jeu est fini
    }

    // Permet de mettre a jour le rendu visuel des objets
    public void render(GraphicsContext graphics) throws IOException, InterruptedException {
        Sprite backgroundSprite = new Sprite("/Background.png", 928, 522); // on cree le sprite du background

        graphics.clearRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE); // on efface le canvas
        graphics.drawImage(backgroundSprite.getImage(), 0, 0, WIDTH * SCALE, HEIGHT * SCALE); // on dessine le background

        this.archer.render(graphics); // on dessine le joueur 1

        this.yourLife.render(graphics); // on dessine la barre de vie du joueur 1
        this.adversaireLife.render(graphics); // on dessine la barre de vie du joueur 2

        this.adversaire.render(graphics); // on dessine le joueur 2

        this.timerGfx.render(graphics); // on dessine le timer du jeu
    }

    // Permet de verifier si le jeu est fini
    private void conditionVictoire() {
        String nomGagnant = ""; // nom du gagnant
        if (this.archer.getLife() <= 0) { // si le joueur 1 est mort
            this.isGameFinish = true; // on met le jeu en fini
            nomGagnant = this.adversaire.getName(); // on recupere le nom du joueur 2

        } else if (this.adversaire.getLife() <= 0) { // si le joueur 2 est mort
            this.isGameFinish = true; // on met le jeu en fini
            nomGagnant = this.archer.getName(); // on recupere le nom du joueur 1
        }

        if (this.isGameFinish){ // si le jeu est fini
            try {
                GestionFichier.write("Score.txt", this.archer.getName() + " vs " + this.adversaire.getName() + " : " + nomGagnant + " WIN !"); // on ecrit dans le fichier le gagnant
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.stage.close(); // on ferme la fenetre de la partie

            Alert alert = new Alert(Alert.AlertType.INFORMATION); // on cree une alert
            alert.setTitle("Fin de la partie"); // on set le titre de l'alert

            alert.setHeaderText(null); // on set le header de l'alert
            alert.setContentText(nomGagnant + " WIN THE GAME !"); // on set le contenu de l'alert
            Platform.runLater(alert::showAndWait); // on affiche l'alert

            this.ouvrirMenu(); // on retourne au menu
        }
    }

    // Permet d'ouvrir le menu
    public void ouvrirMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/menu/Menu.fxml")); // on cree le loader
        Scene scene; // on cree la scene
        try {
            scene = new Scene(fxmlLoader.load()); // on charge la scene
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage(); // on cree le stage
        stage.setTitle("Menu"); // on set le nom de la fenetre
        stage.setScene(scene);

        stage.show(); // on affiche la fenetre
    }

}

