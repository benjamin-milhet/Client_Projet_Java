package projet_client.modeles;

public abstract class Entity {
    protected int x, y;

    public abstract void update();
    public abstract void render();
    public abstract boolean gestionCollision(int x, int y);
}
