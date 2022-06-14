package projet_client.client_projet_java.modeles.entity;

import projet_client.client_projet_java.input.Keyboard;
import projet_client.client_projet_java.modeles.Objet;

public abstract class Entity extends Objet {

    protected int life, lifeMax, speed;
    protected String direction, name;
    protected Keyboard keyboard;

    public abstract int getLife();
    public abstract int getLifeMax();

    public abstract void getImages();
    public abstract boolean gestionCollision(int x, int y);

    public String getName() {
        return name;
    }
}
