package projet_client.modeles;

import projet_client.graphics.Sprite;
import projet_client.input.Keyboard;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fleche extends Entity {

    private int speed;
    private boolean isMooving;
    private String direction;
    private int scale = 3;

    private float compteurFleche;

    private BufferedImage[] fleche;
    private Keyboard keyboard;

    public Fleche(int speed, int x, int y, Keyboard keyboard, String direction) {
        this.speed = speed;
        this.x = x;
        this.y = y;

        this.direction = direction;

        this.keyboard = keyboard;

        this.fleche = new BufferedImage[2];
        this.getArcherImages();

        this.compteurFleche = 0;
    }

    public void move(int x, int y) {
        if (!(x != 0 && y != 0)) { // Permet de ne pas se deplacer en diagonal
            if(!this.gestionCollision(x,y)){
                this.x += x * this.speed;
                this.y += y * this.speed;
            }
        }
    }

    @Override
    public void update() {
        int xPrime = 0;
        int yPrime = 0;

        if(this.direction.equals("right")){
            xPrime++;
        }

        if (xPrime != 0 || yPrime != 0) {
            move(xPrime, yPrime);
            this.isMooving = true;
        } else {
            this.isMooving = false;
        }
    }

    @Override
    public void render(Graphics g) {
        BufferedImage image = this.fleche[Math.round(this.compteurFleche)];
        g.drawImage(image, this.x, this.y, image.getWidth()*this.scale, image.getHeight()*this.scale, null);
        this.compteurFleche+=0.2;
        if(this.compteurFleche >= 1){
            this.compteurFleche = 0;
        }
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        return false;
    }

    public void getArcherImages(){
        try {
            for (int i = 0; i < this.fleche.length; i++) {
                Sprite fleche = new Sprite("fleche/tile00" + i + ".png");
                this.fleche[i] = fleche.getImage();
            }

        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des images de l'archer");
        }
    }
}
