package projet_client.client_projet_java.modeles;

public class TimerGfx extends Entity{

    public TimerGfx(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        /*Color vert = new Color(70, 93, 75); // Color white
        g.setColor(vert);
        g.fillRoundRect(this.x-10, this.y, 100, 100, 20, 20);*/
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        return false;
    }
}
