package projet_client.client_projet_java.modeles;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public abstract class Entity {
    protected int x, y;

    public abstract void update();
    public abstract void render(GraphicsContext graphics);
    public abstract boolean gestionCollision(int x, int y);
}
