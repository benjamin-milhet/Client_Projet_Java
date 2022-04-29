package projet_client.modeles;

import java.awt.*;

public abstract class Entity {
    protected int x, y;

    public abstract void update();
    public abstract void render(Graphics g);
    public abstract boolean gestionCollision(int x, int y);
}
