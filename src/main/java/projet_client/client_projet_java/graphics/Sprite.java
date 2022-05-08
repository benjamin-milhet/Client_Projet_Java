package projet_client.client_projet_java.graphics;

import javafx.scene.image.Image;

import java.io.IOException;

import static projet_client.client_projet_java.Game.SCALE;

public class Sprite {

    private final String path;
    private final double width;
    private final double height;

    private final Image image;

    public Sprite(String path, int width, int height) throws IOException {
            this.image = new Image(getClass().getResource(path).toExternalForm(), width * SCALE, height * SCALE, false, false);

            if (this.image == null) {
                throw new IOException("Impossible de charge l'image");
            }
            this.path = path;
            this.width = this.image.getWidth();
            this.height = this.image.getHeight();

    }

    public String getPath() {
        return path;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Image getImage() {
    	return this.image;
    }





}
