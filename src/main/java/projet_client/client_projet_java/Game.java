package projet_client.client_projet_java;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;
import projet_client.client_projet_java.modeles.*;
import projet_client.client_projet_java.modeles.entity.Archer;
import projet_client.client_projet_java.modeles.entity.Entity;
import projet_client.client_projet_java.modeles.entity.FabriqueEntity;

import java.io.IOException;

public class Game extends AnimationTimer {

    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Operation : Ninja";

    private final Keyboard keyboard;
    private final Archer archer;
    private Archer adversaire;
    private final BarreVie yourLife;
    private BarreVie adversaireLife;
    private final TimerGfx timerGfx;

    private boolean isStarted = false;

    private final GraphicsContext graphics;

    public Game(Stage stage) {
        super();
        Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE);
        this.graphics = canvas.getGraphicsContext2D();

        Scene scene = new Scene(new StackPane(canvas), WIDTH* SCALE, HEIGHT* SCALE);

        stage.setTitle(NAME);
        stage.setScene(scene);
        stage.show();

        this.keyboard = new Keyboard(scene);
        this.archer = (Archer) FabriqueEntity.fabrique(this, "Archer", 10, 50,225,100, this.keyboard, "droite");
        assert this.archer != null;
        this.yourLife = new BarreVie(this, this.archer.getLife(), this.archer.getLifeMax(), 850, 100, "droite");
        this.timerGfx = new TimerGfx(850, 80);

        this.addAdversaire("Archer");
        //Client client = new Client(this);
    }

    public Archer getArcher() {
        return archer;
    }

    public Archer getAdversaire() {
        return adversaire;
    }

    @Override
    public void handle(long now) {
        this.run(graphics);
    }

    public void run(GraphicsContext graphics) {
        if (this.isStarted) {
            this.update();
            try {
                this.render(graphics);
            } catch (IOException | InterruptedException ignored) {}
        }
    }

    public void update() {
        this.archer.update();
        this.adversaire.update();
        this.yourLife.update();
        this.adversaireLife.update();
    }

    public void render(GraphicsContext graphics) throws IOException, InterruptedException {
        Sprite backgroundSprite = new Sprite("/Background.png", 928, 522);

        graphics.clearRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        graphics.drawImage(backgroundSprite.getImage(), 0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        this.archer.render(graphics);

        this.yourLife.render(graphics);
        this.adversaireLife.render(graphics);

        this.adversaire.render(graphics);

        this.timerGfx.render(graphics);
    }

    public void addAdversaire(String adversaire) {
        this.adversaire = (Archer) FabriqueEntity.fabrique(this, adversaire, 10, 450,225,100, this.keyboard, "gauche");

        assert this.adversaire != null;
        this.adversaireLife = new BarreVie(this, this.adversaire.getLife(), this.adversaire.getLifeMax(), 850, 100, "gauche");
        this.isStarted = true;
    }


}

