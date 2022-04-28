package projet_client.modeles;

public class Archer extends Entity{

    private String name;
    private int speed;
    private boolean isMooving;
    private int life;
    private int direction;
    private int scale = 1;

    public Archer(String name, int speed, int x, int y, int life) {
        this.name = name;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.life = life;
    }

    public void move(int x, int y) {
        if (x != 0 && y != 0) { // Permet de ne pas se deplacer en diagonal
            move(x, 0);
            move(0, y);
        } else {
            if(!this.gestionCollision(x,y)){
                if (x > 0) {
                    this.direction = 0;
                } else if (x < 0) {
                    this.direction = 1;
                } else if (y > 0) {
                    this.direction = 2;
                } else if (y < 0) {
                    this.direction = 3;
                }

                this.x += x * this.speed;
                this.y += y * this.speed;
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    @Override
    public boolean gestionCollision(int x, int y) {
        return false;
    }

    public String getName() {
        return name;
    }

}
