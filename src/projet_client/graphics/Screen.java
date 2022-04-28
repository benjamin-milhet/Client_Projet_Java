package projet_client.graphics;

public class Screen {

    public static final int MAP_WIDTH = 64;
    public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;

    public int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
    public int[] colours = new int[MAP_WIDTH * MAP_WIDTH * 4];

    public int xOffset = 0;
    public int yOffset = 0;

    public int width;
    public int height;

    public Sprite sprite;

    public Screen(int width, int height, Sprite sprite) {
        this.width = width;
        this.height = height;
        this.sprite = sprite;

        for (int i = 0; i < tiles.length; i++) {
            colours[i * 4 + 0] = 255;
        }
    }


}
