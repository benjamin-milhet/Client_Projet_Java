package projet_client.client_projet_java;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;
import projet_client.client_projet_java.modeles.Archer;
import projet_client.client_projet_java.modeles.BarreVie;
import projet_client.client_projet_java.modeles.TimerGfx;

import java.io.IOException;

public class Game extends AnimationTimer {

    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Operation : Ninja";

    private Keyboard keyboard;
    private Archer archer;
    private BarreVie yourLife;
    private TimerGfx timerGfx;

    private GraphicsContext graphics;

    public Game(Stage stage) throws Exception {
        super();
        Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE);
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        this.graphics = graphics;

        Scene scene = new Scene(new StackPane(canvas), WIDTH* SCALE, HEIGHT* SCALE);

        stage.setTitle(NAME);
        stage.setScene(scene);
        stage.show();

        this.keyboard = new Keyboard(scene);
        this.archer = new Archer("archer", 10, 50 * SCALE,225 * SCALE,100, this.keyboard);
        this.yourLife = new BarreVie(this.archer.getLife(), this.archer.getLifeMax(), 850, 100);
        this.timerGfx = new TimerGfx(850, 80);
    }

    @Override
    public void handle(long now) {
        this.run(graphics);
    }

    public void run(GraphicsContext graphics) {
        this.update();
        try {
            this.render(graphics);
        } catch (IOException | InterruptedException e) {}
    }

    public void update() {
        this.archer.update();
    }

    public void render(GraphicsContext graphics) throws IOException, InterruptedException {
        Sprite backgroundSprite = new Sprite("/Background.png", 928, 522);

        graphics.clearRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        graphics.drawImage(backgroundSprite.getImage(), 0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        this.archer.render(graphics);
        this.yourLife.render(graphics);
        this.timerGfx.render(graphics);
    }
}

