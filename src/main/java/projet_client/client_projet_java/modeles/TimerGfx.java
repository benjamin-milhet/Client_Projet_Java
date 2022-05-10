package projet_client.client_projet_java.modeles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TimerGfx extends Entity{

    public TimerGfx(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext graphics) {
        Color vert = Color.rgb(47, 55, 76);
        graphics.setFill(vert);
        graphics.fillRoundRect(this.x-5, this.y, 110, 100, 20, 20);
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        return false;
    }
}
