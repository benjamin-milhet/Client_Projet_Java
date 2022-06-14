package projet_client.client_projet_java.graphics;

import javafx.scene.image.Image;

import java.util.Objects;

import static projet_client.client_projet_java.Game.SCALE;

// classe qui permet de gerer les images
public class Sprite {

    private final Image image; // image du sprite

    // constructeur du sprite qui prend en parametre l'url de l'image et la taille de l'image
    public Sprite(String path, int width, int height) {
        this.image = new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm(), width * SCALE, height * SCALE, false, false);

    }

    // getter de l'image
    public Image getImage() {
    	return this.image;
    }
}
