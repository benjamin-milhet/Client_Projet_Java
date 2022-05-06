package projet_client.client_projet_java;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;


import javafx.application.Application;
import projet_client.client_projet_java.modeles.Archer;
import projet_client.client_projet_java.modeles.BarreVie;
import projet_client.client_projet_java.modeles.TimerGfx;

import java.io.IOException;

public class Game extends Application implements Runnable {
    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Operation : Ninja";

    private Thread thread;
    private boolean running;
    private int tickCount = 0;

    private Scene scene;

    private Sprite sprite;
    private Keyboard keyboard;
    private Archer archer;
    private BarreVie yourLife;
    private TimerGfx timerGfx;

    public Game() throws IOException {

    }

    @Override
    public void start(Stage stage) throws Exception {
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();

        StackPane pane = new StackPane();
        Sprite sprite = new Sprite("Background.png");

        pane.setStyle("-fx-background-image: url(" + sprite.getPath() + "); -fx-background-repeat: no-repeat; -fx-background-size: " +WIDTH* SCALE+" "+HEIGHT* SCALE+"; -fx-background-position: center center;");

        this.scene = new Scene(pane, WIDTH* SCALE, HEIGHT* SCALE);

        stage.setScene(scene);
        stage.show();

        this.keyboard = new Keyboard(this);
        this.archer = new Archer("archer", 5, 310,670,100, this.keyboard);
        this.yourLife = new BarreVie(this.archer.getLife(), this.archer.getLifeMax(), 850, 100);
        this.timerGfx = new TimerGfx(850, 80);
    }

    public synchronized void stop() {
        this.running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000.0 / amountOfTicks;

        int ticks = 0;
        int frames = 0;

        long timer = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            boolean shouldRender = true;

            while (delta >= 1) {
                ticks++;
                this.update();
                delta--;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                try {
                    this.render();
                } catch (IOException | InterruptedException e) {
                }
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                System.out.println("FPS : " + frames + " TICKS : " + ticks);
                frames = 0;
                ticks = 0;
            }

        }
    }

    public void update() {
        this.tickCount++;
        this.archer.update();
    }

    public void render() throws IOException, InterruptedException {
        /*BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3); // 3 = nombre de buffers -> Plus il est eleve plus le jeu est fluide
            return;
        }

        Graphics g = bs.getDrawGraphics();

        Sprite sprite = new Sprite("Background.png");

        this.pixels = sprite.getPixels();
        imageBackground = sprite.getImage();
        g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), null); // Dessine l'image de fond

        this.archer.render(g);
        this.yourLife.render(g);
        this.timerGfx.render(g);

        g.dispose(); // Libère la mémoire
        bs.show(); // Affiche l'image de fond*/
    }



    public static void main(String[] args) throws IOException {
        Game game = new Game();
        launch(args);
    }
}

