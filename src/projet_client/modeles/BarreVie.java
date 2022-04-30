package projet_client.modeles;

import java.awt.*;

public class BarreVie extends Entity{

    private int vie;
    private int vieMax;

    public BarreVie(int vie, int vieMax, int x, int y) {
        this.vie = vie;
        this.vieMax = vieMax;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(this.x, this.y, this.vie, 20);
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        return false;
    }
}
