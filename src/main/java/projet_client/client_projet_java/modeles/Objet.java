package projet_client.client_projet_java.modeles;

import javafx.scene.canvas.GraphicsContext;

// classe qui permet de gerer les objets
public abstract class Objet {
    protected int x, y; // position de l'objet

    // Permet de mettre a jour les informations de l'objet
    public abstract void update();

    // Permet de mettre a jour le rendu visuel des objets
    public abstract void render(GraphicsContext graphics);

    // Getters
    public int getX() {
        return x;
    }
}
