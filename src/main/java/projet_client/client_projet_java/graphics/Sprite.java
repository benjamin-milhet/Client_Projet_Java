package projet_client.client_projet_java.graphics;

import javafx.scene.image.Image;

import java.util.Objects;

import static projet_client.client_projet_java.Game.SCALE;

public class Sprite {

    private final Image image;

    public Sprite(String path, int width, int height) {
        this.image = new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm(), width * SCALE, height * SCALE, false, false);

    }

    public Image getImage() {
    	return this.image;
    }
}
