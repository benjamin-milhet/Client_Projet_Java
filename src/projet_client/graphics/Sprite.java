package projet_client.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    private String path;
    private int width;
    private int height;

    public int[] pixels;


    public Sprite(String path) throws IOException {
        BufferedImage image = ImageIO.read(Sprite.class.getResourceAsStream(path));

        if (image == null) {
            throw new IOException("Impossible de charge l'image");
        }

        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();

        pixels = image.getRGB(0, 0, width, height, null, 0, width);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (pixels[i] & 0xff) / 64;
        }

    }

    public String getPath() {
        return path;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
