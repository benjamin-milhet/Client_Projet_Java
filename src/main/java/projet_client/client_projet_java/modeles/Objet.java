package projet_client.client_projet_java.modeles;

import javafx.scene.canvas.GraphicsContext;

public abstract class Objet {
    protected int x, y;

    public abstract void update();
    public abstract void render(GraphicsContext graphics);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
