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
import projet_client.client_projet_java.network.Client;

import java.io.IOException;

public class Game extends AnimationTimer {

    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Operation : Ninja";

    private final Keyboard keyboard;
    private final Archer archer;
    private Entity adversaire;
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
        this.archer = (Archer) FabriqueEntity.fabrique("Archer", 10, 50,225,100, this.keyboard);
        this.yourLife = new BarreVie(this.archer.getLife(), this.archer.getLifeMax(), 850, 100, "droite");
        this.timerGfx = new TimerGfx(850, 80);

        Client client = new Client(this);
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
        /*try {
            this.client.sendMessage(this.archer.getX() + "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
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
        System.out.println("addAdversaire");

        this.adversaire = FabriqueEntity.fabrique(adversaire, 10, 450,225,100, this.keyboard);

        this.adversaireLife = new BarreVie(this.adversaire.getLife(), this.adversaire.getLifeMax(), 850, 100, "gauche");
        this.isStarted = true;
    }
}

