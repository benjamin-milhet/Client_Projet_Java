package projet_client.client_projet_java.modeles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static projet_client.client_projet_java.Game.SCALE;

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
    public void render(GraphicsContext graphics) {
        Color rouge = Color.rgb(93, 9, 35); // Color white
        graphics.setFill(rouge);
        graphics.fillRoundRect(this.x-(this.vieMax*SCALE), this.y, this.vieMax*SCALE + (1 * SCALE), 15*SCALE, 20, 20);

        Color bleu = Color.rgb(55, 90, 59); // Color white
        graphics.setFill(bleu);
        graphics.fillRoundRect(this.x+(SCALE*(this.vieMax-this.vie))-(this.vieMax*SCALE) - (1 * SCALE), this.y + (1.5 * SCALE), this.vie*SCALE, 12*SCALE, 20, 20);
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        return false;
    }
}
