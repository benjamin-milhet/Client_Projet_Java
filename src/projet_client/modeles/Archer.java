package projet_client.modeles;

import projet_client.input.Keyboard;

public class Archer extends Entity{

    private String name;
    private int speed;
    private boolean isMooving;
    private int life;
    private int direction;
    private int scale = 1;

    private Keyboard keyboard;

    public Archer(String name, int speed, int x, int y, int life, Keyboard keyboard) {
        this.name = name;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.life = life;

        this.keyboard = keyboard;
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
        int xPrime = 0;
        int yPrime = 0;

        if(keyboard.getUp().isPressed()) yPrime--;
        if(keyboard.getDown().isPressed()) yPrime++;
        if(keyboard.getLeft().isPressed()) xPrime--;
        if(keyboard.getRight().isPressed()) xPrime++;

        if (xPrime != 0 || yPrime != 0) {
            move(xPrime, yPrime);
            this.isMooving = true;
        } else {
            this.isMooving = false;
        }

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
