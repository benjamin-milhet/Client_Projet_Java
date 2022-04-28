package projet_client;

import projet_client.graphics.Sprite;
import projet_client.input.Key;
import projet_client.input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Operation : Ninja";

    private JFrame frame;
    private Thread thread;
    private boolean running;
    private int tickCount = 0;

    private BufferedImage imageBackground = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) imageBackground.getRaster().getDataBuffer()).getData();

    private Sprite sprite = new Sprite("/Background.png");
    private Keyboard keyboard;

    public Game() throws IOException {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.frame = new JFrame(NAME); // Création de la fenêtre avec le titre "Operation : Ninja"

        this.frame.setSize(WIDTH * SCALE, HEIGHT * SCALE); // Taille de la fenêtre
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Arrete le programme quand on ferme la fenêtre
        this.frame.setResizable(false); // Empêche le redimensionnement de la fenêtre

        this.frame.setLayout(new BorderLayout());
        this.frame.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        this.frame.add(this, BorderLayout.CENTER);
        this.frame.pack();

        this.frame.setVisible(true);

        this.keyboard = new Keyboard(this);
    }

    public synchronized void start() {
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
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
                } catch (IOException e) {
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

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = i + tickCount;
        }
    }

    public void render() throws IOException {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3); // 3 = nombre de buffers -> Plus il est eleve plus le jeu est fluide
            return;
        }

        Graphics g = bs.getDrawGraphics();

        Sprite sprite = new Sprite("Background.png");
        //final ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("background.png"));
        this.pixels = sprite.getPixels();
        imageBackground = sprite.getImage();
        g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), null); // Dessine l'image de fond
        //g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        g.dispose(); // Libère la mémoire
        bs.show(); // Affiche l'image de fond
    }



    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.start();
    }
}

