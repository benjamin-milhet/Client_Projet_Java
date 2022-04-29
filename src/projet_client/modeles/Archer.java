package projet_client.modeles;

import projet_client.graphics.Sprite;
import projet_client.input.Keyboard;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Archer extends Entity{

    private String name;
    private int speed;
    private boolean isMooving;
    private int life;
    private int direction;
    private int scale = 3;

    private float compteurIdle;

    private BufferedImage[] idle;

    private Keyboard keyboard;

    public Archer(String name, int speed, int x, int y, int life, Keyboard keyboard) {
        this.name = name;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.life = life;

        this.keyboard = keyboard;

        this.idle = new BufferedImage[10];
        this.getArcherImages();
        this.compteurIdle = 0;
    }

    public void move(int x, int y) {
        if (x != 0 && y != 0) { // Permet de ne pas se deplacer en diagonal
            move(x, 0);
            move(0, y);
        } else {
            if(!this.gestionCollision(x,y)){
                if (x > 0) {
                    this.direction = 0;
                } else if (x < 0) {
                    this.direction = 1;
                } else if (y > 0) {
                    this.direction = 2;
                } else if (y < 0) {
                    this.direction = 3;
                }

                this.x += x * this.speed;
                this.y += y * this.speed;
            }
        }
        System.out.println("x : " + this.x + " y : " + this.y);
    }

    @Override
    public void update() {
        int xPrime = 0;
        int yPrime = 0;

        if (keyboard.getUp().isPressed()) yPrime--;
        if (keyboard.getDown().isPressed()) yPrime++;
        if (keyboard.getLeft().isPressed()) xPrime--;
        if (keyboard.getRight().isPressed()) xPrime++;

        if (xPrime != 0 || yPrime != 0) {
            move(xPrime, yPrime);
            this.isMooving = true;
        } else {
            this.isMooving = false;
        }
    }

    @Override
    public void render(Graphics g) {
        BufferedImage image = this.idle[Math.round(this.compteurIdle)];
        g.drawImage(image, this.x, this.y, image.getWidth()*this.scale, image.getHeight()*this.scale, null);
        this.compteurIdle+=0.3;
        if(this.compteurIdle >= 9){
            this.compteurIdle = 0;
        }
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        return false;
    }

    public String getName() {
        return name;
    }

    public void getArcherImages(){
        try {
            for (int i = 0; i < 10; i++) {
                Sprite sprite = new Sprite("archer/tile00" + i + ".png");
                this.idle[i] = sprite.getImage();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des images de l'archer");
        }
    }

}
