package projet_client.client_projet_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;
import projet_client.client_projet_java.modeles.Archer;
import projet_client.client_projet_java.modeles.BarreVie;
import projet_client.client_projet_java.modeles.TimerGfx;

import java.io.IOException;

public class Game extends Application {

    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Operation : Ninja";

    private Keyboard keyboard;
    private Archer archer;
    private BarreVie yourLife;
    private TimerGfx timerGfx;


    @Override
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE);
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        Scene scene = new Scene(new StackPane(canvas), WIDTH* SCALE, HEIGHT* SCALE);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), e -> run(graphics)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        stage.setTitle(NAME);
        stage.setScene(scene);
        stage.show();
        timeline.play();


        this.keyboard = new Keyboard(scene);
        this.archer = new Archer("archer", 5, 310,670,100, this.keyboard);
        //this.yourLife = new BarreVie(this.archer.getLife(), this.archer.getLifeMax(), 850, 100);
        //this.timerGfx = new TimerGfx(850, 80);
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
        Sprite backgroundSprite = new Sprite("/Background.png");

        graphics.clearRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        graphics.drawImage(backgroundSprite.getImage(), 0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        this.archer.render(graphics);
        /*this.yourLife.render(graphics);
        this.timerGfx.render(graphics);*/

    }



    public static void main(String[] args) throws IOException {
        launch(args);
    }
}

