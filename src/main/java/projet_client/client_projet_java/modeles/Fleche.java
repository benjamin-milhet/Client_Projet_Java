package projet_client.client_projet_java.modeles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;


public class Fleche extends Entity {

    private int speed;
    private boolean isMooving;
    private String direction;
    private int scale = 3;
    private int life;

    private float compteurFleche;
    private int compteurYPrime;

    private Image[] fleche;
    private Keyboard keyboard;

    public Fleche(int speed, int x, int y, Keyboard keyboard, String direction) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.life = 1;

        this.direction = direction;

        this.keyboard = keyboard;

        this.fleche = new Image[2];
        this.getArcherImages();

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
            xPrime = 1 * this.speed;
            this.compteurYPrime++;
            if (this.compteurYPrime >= 3) {
                this.compteurYPrime = 0;
                yPrime ++;
            }

        }

        if (xPrime != 0 || yPrime != 0) {
            move(xPrime, yPrime);
            this.isMooving = true;
        } else {
            this.isMooving = false;
        }
    }

    @Override
    public void render(GraphicsContext graphics) {
        /*BufferedImage image = this.fleche[Math.round(this.compteurFleche)];
        g.drawImage(image, this.x, this.y, image.getWidth()*this.scale, image.getHeight()*this.scale, null);
        this.compteurFleche+=0.2;
        if(this.compteurFleche >= 1){
            this.compteurFleche = 0;
        }*/
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        boolean res = false;
        if(this.x + x < 0 || this.x + x > 1750 || this.y + y < 0 || this.y + y > 860){
            System.out.println("Collision avec la map");
            this.compteurYPrime = 0;
            res = true;
            this.life--;
        }
        return res;
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

    public int getLife(){
        return this.life;
    }
}
