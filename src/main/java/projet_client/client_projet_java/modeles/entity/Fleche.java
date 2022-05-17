package projet_client.client_projet_java.modeles.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;

import static projet_client.client_projet_java.Game.SCALE;


public class Fleche extends Entity {
    private float compteurFleche;
    private int compteurYPrime;

    private final Image[] fleche;

    public Fleche(int speed, int x, int y, Keyboard keyboard, String direction) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.life = 1;

        this.direction = direction;

        this.keyboard = keyboard;

        this.fleche = new Image[2];
        this.getImages();

        this.compteurFleche = 0;
        this.compteurYPrime = 0;
    }

    public void move(int x, int y) {

        if(!this.gestionCollision(x,y)){
            this.x += x * this.speed;
            this.y += y * this.speed;
        }

    }

    @Override
    public void update() {
        int xPrime = 0;
        int yPrime = 0;

        if(this.direction.equals("right")){
            xPrime = this.speed;
            this.compteurYPrime++;
            if (this.compteurYPrime >= 3) {
                this.compteurYPrime = 0;
                yPrime ++;
            }

        }

        if (xPrime != 0 || yPrime != 0) {
            move(xPrime, yPrime);
        }
    }

    @Override
    public void render(GraphicsContext graphics) {
        Image fleche = this.fleche[Math.round(this.compteurFleche)];
        graphics.drawImage(fleche, this.x, this.y, fleche.getWidth(), fleche.getHeight());

        this.compteurFleche++;
        if(this.compteurFleche >= 1){
            this.compteurFleche = 0;
        }
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        boolean res = false;
        if(this.x + x < 0 || this.x + x > (600 * SCALE) || this.y + y < 0 || this.y + y > (290 * SCALE)){
            System.out.println("Collision avec la map");
            this.compteurYPrime = 0;
            res = true;
            this.life--;
        }
        return res;
    }

    @Override
    public void getImages(){
        try {
            for (int i = 0; i < this.fleche.length; i++) {
                Sprite fleche = new Sprite("/fleche/tile00" + i + ".png", 24, 5);
                this.fleche[i] = fleche.getImage();
            }

        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des images de l'archer");
        }
    }

    @Override
    public int getLife(){
        return this.life;
    }

    @Override
    public int getLifeMax() {
        return 0;
    }
}
