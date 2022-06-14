package projet_client.client_projet_java.modeles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import projet_client.client_projet_java.Game;

import static projet_client.client_projet_java.Game.SCALE;

// classe qui permet de gerer la barre de vie d'un joueur
public class BarreVie extends Objet{

    private int vie; // vie du joueur
    private final int vieMax; // vie max du joueur
    private final String position; // position de la barre de vie
    private final Game game; // jeu en cours

    // constructeur de la barre de vie
    public BarreVie(Game game, int vie, int vieMax, int x, int y, String position) {
        this.game = game;
        this.vie = vie-20;
        this.vieMax = vieMax;
        this.position = position;

        this.x = x;
        this.y = y;
    }

    // Permet de mettre a jour les informations de l'objet
    @Override
    public void update() {
        if (this.position.equals("droite")) { // si la barre de vie est a droite
            this.vie = this.game.getArcher().getLife(); // on met a jour la vie du joueur 1
        } else { // si la barre de vie est a gauche
            this.vie = this.game.getAdversaire().getLife(); // on met a jour la vie du joueur 2
        }
    }

    // Permet de mettre a jour le rendu visuel des objets
    @Override
    public void render(GraphicsContext graphics) {
        // Fond de la barre de vie
        Color rouge = Color.rgb(93, 9, 35); // couleur rouge
        graphics.setFill(rouge); // on met la couleur rouge
        if(this.position.equals("droite")){ // si la barre de vie est a droite
            graphics.fillRoundRect(this.x - (this.vieMax * SCALE + (SCALE)) - 10, this.y, this.vieMax * SCALE + (SCALE) + 10, 15 * SCALE, 20, 20); // on dessine la barre de vie du joueur 1
        } else if(this.position.equals("gauche")){ // si la barre de vie est a gauche
            graphics.fillRoundRect(this.x + 100, this.y, this.vieMax * SCALE + (SCALE) + 10, 15 * SCALE, 20, 20); // on dessine la barre de vie du joueur 2
        }

        // Barre de vie
        Color vert = Color.rgb(55, 90, 59); // couleur vert
        graphics.setFill(vert); // on met la couleur vert
        if(this.position.equals("droite")){ // si la barre de vie est a droite
            graphics.fillRoundRect(this.x + (SCALE * (this.vieMax - this.vie)) - (this.vieMax * SCALE) - (SCALE), this.y + (1.5 * SCALE), this.vie * SCALE, 12 * SCALE, 20, 20); // on dessine la barre de vie du joueur 1
        } else if(this.position.equals("gauche")){ // si la barre de vie est a gauche
            graphics.fillRoundRect(this.x + 100 + (SCALE), this.y + (1.5 * SCALE), this.vie * SCALE, 12 * SCALE, 20, 20); // on dessine la barre de vie du joueur 2
        }
    }
}
