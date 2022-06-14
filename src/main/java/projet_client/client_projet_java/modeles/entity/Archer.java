package projet_client.client_projet_java.modeles.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import projet_client.client_projet_java.Game;
import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;

import java.util.ArrayList;
import java.util.Iterator;

import static projet_client.client_projet_java.Game.SCALE;

public class Archer extends Entity {

    private float compteurIdle;
    private float compteurAttack;
    private float compteurJump;
    private float compteurRun;

    private final Image[] idle, attack, jump, run;

    private ArrayList<Fleche> fleches;

    private final String position;
    private final Game game;

    public Archer(Game game, String name, int speed, int x, int y, int life, Keyboard keyboard, String position) {
        this.game = game;
        this.name = name;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.life = life;
        this.lifeMax = life;
        this.direction = "idle";
        this.position = position;

        this.keyboard = keyboard;

        this.idle = new Image[10];
        this.attack = new Image[6];
        this.jump = new Image[4];
        this.run = new Image[8];

        this.getImages();

        this.compteurIdle = 0;
        this.compteurAttack = 0;
        this.compteurJump = 0;
        this.compteurRun = 0;

        this.fleches = new ArrayList<>();
    }

    public void move(int x, int y) {
        if (!(x != 0 && y != 0)) { // Permet de ne pas se deplacer en diagonal
            if(this.gestionCollision(x, y)){
                if (this.position.equals("droite")){
                    if (this.getX() + (40*SCALE) <= this.game.getAdversaire().getX()) this.x += x * this.speed;
                    else this.x--;
                } else {
                    if (this.getX() - (40*SCALE) >= this.game.getArcher().getX()) this.x += x * this.speed;
                    else this.x++;
                }
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

        for (Fleche fleche : this.fleches) {
            fleche.update();
        }

        int xPrime = 0;
        int yPrime = 0;


        if(this.compteurJump < 0.5){
            if (this.y < (225 * SCALE)){
                this.y = (225 * SCALE);
            }
        }

        if(this.compteurJump == 0 && this.compteurAttack == 0 && this.y == (225 * SCALE)){

            this.direction = "idle";
            if (this.position.equals("droite")) {
                if (this.keyboard.getUpPlayer1().isPressed()) {
                    yPrime = -(3 * SCALE);
                    this.direction = "up";
                    this.compteurJump = 0;
                }

                if (this.keyboard.getDownPlayer1().isPressed()) {
                    this.direction = "down";
                    this.compteurAttack = 0;
                }

                if (this.keyboard.getLeftPlayer1().isPressed()) {
                    xPrime--;
                    this.direction = "left";
                }

                if (this.keyboard.getRightPlayer1().isPressed()) {
                    xPrime++;
                    this.direction = "right";
                }
            } else {
                if (this.keyboard.getUpPlayer2().isPressed()) {
                    yPrime = -(3 * SCALE);
                    this.direction = "up";
                    this.compteurJump = 0;
                }

                if (this.keyboard.getDownPlayer2().isPressed()) {
                    this.direction = "down";
                    this.compteurAttack = 0;
                }

                if (this.keyboard.getLeftPlayer2().isPressed()) {
                    xPrime--;
                    this.direction = "left";
                }

                if (this.keyboard.getRightPlayer2().isPressed()) {
                    xPrime++;
                    this.direction = "right";
                }
            }

        }

        if (xPrime != 0 || yPrime != 0) {
            move(xPrime, yPrime);
        }

        this.gestionCollision(0, 0);
    }

    @Override
    public void render(GraphicsContext graphics){
        for (Fleche fleche : this.fleches) {
            fleche.render(graphics);
        }

        Image archerSprite = this.idle[0];
        if (this.direction.equals("idle")) {
            archerSprite = this.idle[Math.round(this.compteurIdle)];

            this.compteurIdle += 0.5;
            if(this.compteurIdle >= 9){
                this.compteurIdle = 0;
            }
        } else {
            switch (this.direction) {
                case "down" -> {
                    archerSprite = this.attack[Math.round(this.compteurAttack)];
                    this.compteurAttack += 0.5;
                    if (this.compteurAttack >= 5) {
                        this.compteurAttack = 0;
                        this.direction = "idle";

                        this.lancerFleche();
                    }
                }
                case "right", "left" -> {
                    archerSprite = this.run[Math.round(this.compteurRun)];
                    this.compteurRun += 0.5;
                    if (this.compteurRun >= 7) {
                        this.compteurRun = 0;
                    }
                }
                case "up" -> {
                    archerSprite = this.jump[Math.round(this.compteurJump)];
                    this.compteurJump += 0.5;
                    if (this.compteurJump >= 3) {
                        this.compteurJump = 0;
                        this.direction = "idle";

                    }
                    if (this.position.equals("droite")) this.keyboard.getUpPlayer1().setPressed(false);
                    else  this.keyboard.getUpPlayer2().setPressed(false);
                }
            }
        }
        graphics.drawImage(archerSprite, this.x, this.y, archerSprite.getWidth(), archerSprite.getHeight());
    }

    @Override
    public boolean gestionCollision(int x, int y) {
        if (this.position.equals("droite")) {
            Iterator<Fleche> iteratorFlecheAdversaire = this.game.getAdversaire().getFleches().iterator();
            while (iteratorFlecheAdversaire.hasNext()) {
                if (this.getX() + (60 * SCALE) >= iteratorFlecheAdversaire.next().getX()) {
                    this.life -=20;
                    iteratorFlecheAdversaire.remove();
                }
            }
        } else {
            Iterator<Fleche> iteratorFlecheArcher = this.game.getArcher().getFleches().iterator();
            while (iteratorFlecheArcher.hasNext()) {
                if (this.getX() + (20 * SCALE) <= iteratorFlecheArcher.next().getX()) {
                    this.life -=20;
                    iteratorFlecheArcher.remove();
                }
            }
        }

        return true;
    }

    public void lancerFleche(){
        Fleche fleche;
        if (this.position.equals("droite")) fleche = new Fleche(4, this.x + (60 * SCALE), this.y + (40 * SCALE), this.keyboard, "droite");
        else fleche = new Fleche(4, this.x + (20 * SCALE), this.y + (40 * SCALE), this.keyboard, "gauche");

        this.fleches.add(fleche);
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public int getLifeMax() {
        return this.lifeMax;
    }

    @Override
    public void getImages(){
        Sprite archer;
        try {
            if (this.position.equals("droite")) {
                for (int i = 0; i < this.idle.length; i++) {
                    archer = new Sprite("/archer/droite/idle/tile00" + i + ".png", 100, 100);
                    this.idle[i] = archer.getImage();
                }
                for (int i = 0; i < this.attack.length; i++) {
                    archer = new Sprite("/archer/droite/attack/tile00" + i + ".png", 100, 100);
                    this.attack[i] = archer.getImage();
                }
                for (int i = 0; i < this.jump.length; i++) {
                    archer = new Sprite("/archer/droite/jump/tile00" + i + ".png", 100, 100);
                    this.jump[i] = archer.getImage();
                }
                for (int i = 0; i < this.run.length; i++) {
                    archer = new Sprite("/archer/droite/run/tile00" + i + ".png", 100, 100);
                    this.run[i] = archer.getImage();
                }
            } else {
                for (int i = 0; i < this.idle.length; i++) {
                    archer = new Sprite("/archer/gauche/idle/tile00" + i + ".png", 100, 100);
                    this.idle[i] = archer.getImage();
                }
                for (int i = 0; i < this.attack.length; i++) {
                    archer = new Sprite("/archer/gauche/attack/tile00" + i + ".png", 100, 100);
                    this.attack[i] = archer.getImage();
                }
                for (int i = 0; i < this.jump.length; i++) {
                    archer = new Sprite("/archer/gauche/jump/tile00" + i + ".png", 100, 100);
                    this.jump[i] = archer.getImage();
                }
                for (int i = 0; i < this.run.length; i++) {
                    archer = new Sprite("/archer/gauche/run/tile00" + i + ".png", 100, 100);
                    this.run[i] = archer.getImage();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement des images de l'archer");
        }
    }

    public ArrayList<Fleche> getFleches() {
        return fleches;
    }


}
