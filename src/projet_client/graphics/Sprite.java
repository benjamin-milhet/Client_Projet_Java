package projet_client.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    private String path;
    private int width;
    private int height;

    private int[] pixels;

    private BufferedImage image;


    public Sprite(String path) throws IOException {

        this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Background.png"));

        if (image == null) {
            throw new IOException("Impossible de charge l'image");
        }

        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();

        pixels = image.getRGB(0, 0, width, height, null, 0, width);


    }

    public void closeImage() {
    	image.flush();
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

    public int[] getPixels() {
        return pixels;
    }

    public BufferedImage getImage() {
    	return this.image;
    }




}
