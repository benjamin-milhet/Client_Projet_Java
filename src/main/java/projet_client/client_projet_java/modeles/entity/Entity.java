package projet_client.client_projet_java.modeles.entity;

import projet_client.client_projet_java.input.Keyboard;
import projet_client.client_projet_java.modeles.Objet;

// classe qui permet de gerer les entites
public abstract class Entity extends Objet {

    protected int life, lifeMax, speed; // vie de l'entite, vie max de l'entite, vitesse de l'entite
    protected String direction, name; // direction de l'entite, nom de l'entite
    protected Keyboard keyboard; // touches du clavier

    public abstract int getLife();
    public abstract int getLifeMax();

    public abstract void getImages();
    public abstract boolean gestionCollision(int x, int y);

    public String getName() {
        return name;
    }
}
