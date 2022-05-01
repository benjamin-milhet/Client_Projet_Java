package projet_client.modeles;

import java.awt.*;

import static projet_client.Game.SCALE;

public class BarreVie extends Entity{

    private int vie;
    private int vieMax;

    public BarreVie(int vie, int vieMax, int x, int y) {
        this.vie = vie-20;
        this.vieMax = vieMax;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        Color rouge = new Color(209, 26, 83); // Color white
        g.setColor(rouge);
        g.fillRoundRect(this.x-(this.vieMax*SCALE), this.y, this.vieMax*SCALE, 20*SCALE, 20, 20);

        Color bleu = new Color(91, 110, 232); // Color white
        g.setColor(bleu);
        g.fillRoundRect(this.x+(SCALE*(this.vieMax-this.vie))-(this.vieMax*SCALE), this.y, this.vie*SCALE, 20*SCALE, 20, 20);
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        return false;
    }
}
