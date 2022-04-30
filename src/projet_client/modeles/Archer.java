package projet_client.modeles;

import projet_client.graphics.Sprite;
import projet_client.input.Keyboard;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Archer extends Entity{

    private String name;
    private int speed;
    private boolean isMooving;
    private int life;
    private String direction;
    private int scale = 3;

    private float compteurIdle;
    private float compteurAttack;
    private float compteurjump;
    private float compteurRun;

    private BufferedImage[] idle, attack, jump, run, dead;

    private Keyboard keyboard;

    public Archer(String name, int speed, int x, int y, int life, Keyboard keyboard) {
        this.name = name;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.life = life;
        this.direction = "idle";

        this.keyboard = keyboard;

        this.idle = new BufferedImage[10];
        this.attack = new BufferedImage[6];
        this.jump = new BufferedImage[4];
        this.run = new BufferedImage[8];

        this.getArcherImages();

        this.compteurIdle = 0;
        this.compteurAttack = 0;
        this.compteurjump = 0;
        this.compteurRun = 0;
    }

    public void move(int x, int y) {
        if (!(x != 0 && y != 0)) { // Permet de ne pas se deplacer en diagonal
            if(!this.gestionCollision(x,y)){
                this.x += x * this.speed;
                this.y += y * this.speed;
            }
        }
    }

    @Override
    public void update() {
        int xPrime = 0;
        int yPrime = 0;


        if(this.compteurjump < 0.2){
            if (this.y < 670) {
                this.y = 670;
            }
        }

        if(this.compteurjump == 0 && this.compteurAttack == 0 && this.y == 670){

            this.direction = "idle";

            if (this.keyboard.getUp().isPressed()) {
                yPrime = -30;
                this.direction = "up";
                this.compteurjump = 0;
            }

            if (this.keyboard.getDown().isPressed()) {
                //yPrime++;
                this.direction = "down";
                this.compteurAttack = 0;
            }

            if (this.keyboard.getLeft().isPressed()) {
                xPrime--;
                this.direction = "left";
            }

            if (this.keyboard.getRight().isPressed()) {
                xPrime++;
                this.direction = "right";
            }
        }

        if (xPrime != 0 || yPrime != 0) {
            move(xPrime, yPrime);
            this.isMooving = true;
        } else {
            this.isMooving = false;
            //this.direction = "idle";
        }
    }

    @Override
    public void render(Graphics g){

        if (this.direction == "idle") {
            BufferedImage image = this.idle[Math.round(this.compteurIdle)];
            g.drawImage(image, this.x, this.y, image.getWidth()*this.scale, image.getHeight()*this.scale, null);
            this.compteurIdle+=0.3;
            if(this.compteurIdle >= 9){
                this.compteurIdle = 0;
            }
        } else {
            if (this.direction == "down") {
                BufferedImage image = this.attack[Math.round(this.compteurAttack)];
                g.drawImage(image, this.x, this.y, image.getWidth()*this.scale, image.getHeight()*this.scale, null);
                this.compteurAttack+=0.2;
                if(this.compteurAttack >= 5){
                    this.compteurAttack = 0;
                    this.direction = "idle";
                    Fleche fleche = new Fleche(1, this.x, this.y, this.keyboard, "right");
                }

            } else if (this.direction == "right") {
                BufferedImage image = this.run[Math.round(this.compteurRun)];
                g.drawImage(image, this.x, this.y, image.getWidth()*this.scale, image.getHeight()*this.scale, null);
                this.compteurRun+=0.2;
                if(this.compteurRun >= 7){
                    this.compteurRun = 0;
                }

            } else if (this.direction == "left") {
                BufferedImage image = this.run[Math.round(this.compteurRun)];
                g.drawImage(image, this.x, this.y, image.getWidth()*this.scale, image.getHeight()*this.scale, null);
                this.compteurRun+=0.2;
                if(this.compteurRun >= 7){
                    this.compteurRun = 0;
                }

            } else if (this.direction == "up") {
                BufferedImage image = this.jump[Math.round(this.compteurjump)];
                g.drawImage(image, this.x, this.y, image.getWidth()*this.scale, image.getHeight()*this.scale, null);

                this.compteurjump += 0.2;
                if(this.compteurjump >= 3){
                    this.compteurjump = 0;
                    this.direction = "idle";

                }
                this.keyboard.getUp().setPressed(false);

            }
        }
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        return false;
    }

    public String getName() {
        return name;
    }

    public void getArcherImages(){
        try {
            for (int i = 0; i < this.idle.length; i++) {
                Sprite archer = new Sprite("archer/idle/tile00" + i + ".png");
                this.idle[i] = archer.getImage();
            }
            for (int i = 0; i < this.attack.length; i++) {
                Sprite attack = new Sprite("archer/attack/tile00" + i + ".png");
                this.attack[i] = attack.getImage();
            }
            for (int i = 0; i < this.jump.length; i++) {
                Sprite jump = new Sprite("archer/jump/tile00" + i + ".png");
                this.jump[i] = jump.getImage();
            }
            for (int i = 0; i < this.run.length; i++) {
                Sprite run = new Sprite("archer/run/tile00" + i + ".png");
                this.run[i] = run.getImage();
            }

        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des images de l'archer");
        }
    }

}
