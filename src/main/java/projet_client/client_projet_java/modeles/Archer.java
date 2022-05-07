package projet_client.client_projet_java.modeles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;

import java.util.ArrayList;

import static projet_client.client_projet_java.Game.SCALE;

public class Archer extends Entity{

    private String name;
    private int speed;
    private boolean isMooving;
    private int life;
    private int lifeMax;
    private String direction;

    private float compteurIdle;
    private float compteurAttack;
    private float compteurjump;
    private float compteurRun;

    private Image[] idle, attack, jump, run, dead;

    private Keyboard keyboard;
    private ArrayList<Fleche> fleches;

    public Archer(String name, int speed, int x, int y, int life, Keyboard keyboard) {
        this.name = name;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.life = life;
        this.lifeMax = life;
        this.direction = "idle";

        this.keyboard = keyboard;

        this.idle = new Image[10];
        this.attack = new Image[6];
        this.jump = new Image[4];
        this.run = new Image[8];

        this.getArcherImages();

        this.compteurIdle = 0;
        this.compteurAttack = 0;
        this.compteurjump = 0;
        this.compteurRun = 0;

        this.fleches = new ArrayList<>();
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
        ArrayList<Fleche> lastFleches = new ArrayList<>();
        for(Fleche f : this.fleches){
            if (f.getLife() > 0){
                lastFleches.add(f);
            }
        }
        this.fleches = lastFleches;

        for (int i = 0; i < this.fleches.size(); i++) {
            this.fleches.get(i).update();
        }

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
    public void render(GraphicsContext graphics){
        for (int i = 0; i < this.fleches.size(); i++) {
            this.fleches.get(i).render(graphics);
        }
        Image archerSprite;
        if (this.direction == "idle") {
            archerSprite = this.idle[Math.round(this.compteurIdle)];
            graphics.drawImage(archerSprite, this.x, this.y, archerSprite.getWidth() * SCALE, archerSprite.getHeight() * SCALE);
            this.compteurIdle+=0.3;
            if(this.compteurIdle >= 9){
                this.compteurIdle = 0;
            }
        } else {
            if (this.direction == "down") {
                archerSprite = this.attack[Math.round(this.compteurAttack)];
                graphics.drawImage(archerSprite, this.x, this.y, archerSprite.getWidth() * SCALE, archerSprite.getHeight() * SCALE);
                this.compteurAttack+=0.2;
                if(this.compteurAttack >= 5){
                    this.compteurAttack = 0;
                    this.direction = "idle";

                    Fleche fleche = new Fleche(4, this.x+180, this.y+125, this.keyboard, "right");
                    this.fleches.add(fleche);
                }

            } else if (this.direction == "right") {
                archerSprite = this.run[Math.round(this.compteurRun)];
                graphics.drawImage(archerSprite, this.x, this.y, archerSprite.getWidth() * SCALE, archerSprite.getHeight() * SCALE);
                this.compteurRun+=0.2;
                if(this.compteurRun >= 7){
                    this.compteurRun = 0;
                }

            } else if (this.direction == "left") {
                archerSprite = this.run[Math.round(this.compteurRun)];
                graphics.drawImage(archerSprite, this.x, this.y, archerSprite.getWidth() * SCALE, archerSprite.getHeight() * SCALE);
                this.compteurRun+=0.2;
                if(this.compteurRun >= 7){
                    this.compteurRun = 0;
                }

            } else if (this.direction == "up") {
                archerSprite = this.jump[Math.round(this.compteurjump)];
                graphics.drawImage(archerSprite, this.x, this.y, archerSprite.getWidth() * SCALE, archerSprite.getHeight() * SCALE);

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

    public int getLife() {
        return this.life;
    }

    public int getLifeMax() {
        return this.lifeMax;
    }

    public void getArcherImages(){
        Sprite archer;
        try {
            for (int i = 0; i < this.idle.length; i++) {
                archer = new Sprite("/archer/idle/tile00" + i + ".png");
                this.idle[i] = archer.getImage();
            }
            for (int i = 0; i < this.attack.length; i++) {
                archer = new Sprite("/archer/attack/tile00" + i + ".png");
                this.attack[i] = archer.getImage();
            }
            for (int i = 0; i < this.jump.length; i++) {
                archer = new Sprite("/archer/jump/tile00" + i + ".png");
                this.jump[i] = archer.getImage();
            }
            /*for (int i = 0; i < this.run.length; i++) {
                archer = new Sprite("archer/run/tile00" + i + ".png");
                this.run[i] = archer.getImage();
            }*/

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement des images de l'archer");
        }
    }

}
