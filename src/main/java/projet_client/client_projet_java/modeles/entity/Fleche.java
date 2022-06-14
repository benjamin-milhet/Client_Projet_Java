package projet_client.client_projet_java.modeles.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;

import static projet_client.client_projet_java.Game.SCALE;

// classe qui permet de gerer les fleches
public class Fleche extends Entity {
    private float compteurFleche; // compteur du sprite fleche
    private int compteurYPrime; // compteur de la position Y de la fleche

    private final Image[] fleche; // tableau des images de la fleche

    // constructeur de la fleche
    public Fleche(int speed, int x, int y, Keyboard keyboard, String direction) {
        this.speed = speed; // vitesse de la fleche
        this.x = x; // position x de la fleche
        this.y = y; // position y de la fleche
        this.life = 1; // vie de la fleche

        this.direction = direction; // direction de la fleche

        this.keyboard = keyboard; // touches du clavier

        this.fleche = new Image[2]; // tableau des images de la fleche
        this.getImages(); // on recupere les images de la fleche

        this.compteurFleche = 0;
        this.compteurYPrime = 0;
    }

    // Permet de deplacer la fleche
    public void move(int x, int y) {

        if (this.direction.equals("gauche")) x = -x; // si la direction est a gauche, on inverse la direction de la fleche

        // On deplace la fleche
        if(this.gestionCollision(x, y)){
            this.x += x * this.speed;
            this.y += y * this.speed;
        }
    }

    // Permet de mettre a jour les informations de la fleche
    @Override
    public void update() {
        int yPrime = 0; // position Y de la fleche
        int xPrime = this.speed; // position X de la fleche

        this.compteurYPrime++; // on incremente le compteur de la position Y de la fleche
        if (this.compteurYPrime >= 3) { // si le compteur de la position Y de la fleche est superieur ou egal a 3
            this.compteurYPrime = 0; // on remet le compteur de la position Y de la fleche a 0
            yPrime ++; // on incremente la position Y de la fleche
        }

        if (xPrime != 0 || yPrime != 0) {
            move(xPrime, yPrime); // on deplace la fleche
        }
    }

    // Permet de mettre a jour le rendu visuel des fleches
    @Override
    public void render(GraphicsContext graphics) {
        Image fleche = this.fleche[Math.round(this.compteurFleche)]; // on recupere l'image de la fleche
        graphics.drawImage(fleche, this.x, this.y, fleche.getWidth(), fleche.getHeight()); // on dessine la fleche

        this.compteurFleche++; // on incremente le compteur du sprite fleche
        if(this.compteurFleche >= 1){ // si le compteur du sprite fleche est superieur ou egal a 1
            this.compteurFleche = 0; // on remet le compteur du sprite fleche a 0
        }
    }


    // Permet de savoir si la fleche sort de la carte
    @Override
    public boolean gestionCollision(int x, int y) {
        boolean res = false;
        if(this.x + x < 0 || this.x + x > (600 * SCALE) || this.y + y < 0 || this.y + y > (290 * SCALE)){ // si la fleche sort de la carte
            this.compteurYPrime = 0; // on remet le compteur de la position Y de la fleche a 0
            res = true;
            this.life--; // on decremente la vie de la fleche
        }
        return !res; // on retourne le resultat
    }

    // Permet de recuperer les images de la fleche
    @Override
    public void getImages(){
        try {
            if (this.direction.equals("droite")) { // si la direction de la fleche est a droite
                for (int i = 0; i < this.fleche.length; i++) {
                    Sprite fleche = new Sprite("/fleche/droite/tile00" + i + ".png", 24, 5);
                    this.fleche[i] = fleche.getImage();
                }
            } else { // si la direction de la fleche est a gauche
                for (int i = 0; i < this.fleche.length; i++) {
                    Sprite fleche = new Sprite("/fleche/gauche/tile00" + i + ".png", 24, 5);
                    this.fleche[i] = fleche.getImage();
                }
            }
        } catch (Exception e) { // si une erreur est survenue
            System.out.println("Erreur lors du chargement des images de l'archer");
        }
    }

    // Getters
    @Override
    public int getLife(){
        return this.life;
    }

    @Override
    public int getLifeMax() {
        return 0;
    }
}
