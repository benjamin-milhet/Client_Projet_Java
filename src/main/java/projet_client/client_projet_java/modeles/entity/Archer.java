package projet_client.client_projet_java.modeles.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import projet_client.client_projet_java.Game;
import projet_client.client_projet_java.graphics.Sprite;
import projet_client.client_projet_java.input.Keyboard;

import java.util.ArrayList;
import java.util.Iterator;

import static projet_client.client_projet_java.Game.SCALE;

// classe qui permet de gerer les archers
public class Archer extends Entity {

    private float compteurIdle; // compteur qui permet de gerer l'animation d'idle
    private float compteurAttack; // compteur qui permet de gerer l'animation d'attaque
    private float compteurJump; // compteur qui permet de gerer l'animation de saut
    private float compteurRun; // compteur qui permet de gerer l'animation de marche
    private final Image[] idle, attack, jump, run; // tableau de sprites qui permet de gerer les animations d'idle, d'attaque, de saut et de marche
    private ArrayList<Fleche> fleches; // tableau de fleches qui permet de gerer les fleches qui sont tirées par l'archer
    private final String position; // variable qui permet de savoir si l'archer est en position de saut ou de marche
    private final Game game; // variable qui permet de savoir quel jeu est en cours

    // constructeur de l'archer
    public Archer(Game game, String name, int speed, int x, int y, int life, Keyboard keyboard, String position) {
        this.game = game; // on affecte le jeu en cours
        this.name = name; // on affecte le nom de l'archer
        this.speed = speed; // on affecte la vitesse de l'archer
        this.x = x; // on affecte la position x de l'archer
        this.y = y; // on affecte la position y de l'archer
        this.life = life; // on affecte la vie de l'archer
        this.lifeMax = life; // on affecte la vie max de l'archer
        this.direction = "idle"; // on affecte la direction de depart de l'archer
        this.position = position; // on affecte la position de l'archer

        this.keyboard = keyboard; // on affecte le clavier

        this.idle = new Image[10]; // on affecte le tableau de sprites d'idle
        this.attack = new Image[6]; // on affecte le tableau de sprites d'attaque
        this.jump = new Image[4]; // on affecte le tableau de sprites de saut
        this.run = new Image[8]; // on affecte le tableau de sprites de marche

        this.getImages(); // on appelle la fonction qui permet de recuperer l'ensemble des images

        this.compteurIdle = 0;
        this.compteurAttack = 0;
        this.compteurJump = 0;
        this.compteurRun = 0;

        this.fleches = new ArrayList<>();
    }

    // fonction qui permet de deplacer l'archer
    public void move(int x, int y) {
        if (!(x != 0 && y != 0)) { // Permet de ne pas se deplacer en diagonal
            if(this.gestionCollision(x, y)){ // Permet de ne pas se deplacer sur une case qui est inaccessible
                if (this.position.equals("droite")){ // Si le l'archer est le joueur 1
                    if (this.getX() + (40*SCALE) <= this.game.getAdversaire().getX()) this.x += x * this.speed; // Si le l'archer est a droite du joueur 2, on le deplace
                    else this.x--; // Sinon on le deplace a gauche
                } else {  // Si le l'archer est le joueur 2
                    if (this.getX() - (40*SCALE) >= this.game.getArcher().getX()) this.x += x * this.speed; // Si le l'archer est a gauche du joueur 1, on le deplace
                    else this.x++; // Sinon on le deplace a droite
                }
                this.y += y * this.speed; // On deplace l'archer
            }
        }
    }

    // Permet de mettre a jour les informations de l'objet
    @Override
    public void update() {
        ArrayList<Fleche> lastFleches = new ArrayList<>(); // Tableau de fleches qui permet de stocker les fleches qui ont ete tirées
        for(Fleche f : this.fleches){ // Pour chaque fleche tirée
            if (f.getLife() > 0){ // Si la fleche n'a pas encore atteinte la fin de sa vie
                lastFleches.add(f); // On ajoute la fleche a la liste des fleches qui ont ete tirées
            }
        }
        this.fleches = lastFleches; // On affecte la liste des fleches qui ont ete tirées a la liste des fleches tirées

        for (Fleche fleche : this.fleches) {
            fleche.update(); // On met a jour les informations de chaque fleche tirée
        }

        int xPrime = 0;
        int yPrime = 0;


        if(this.compteurJump < 0.5){ // Si le compteur de saut est inferieur a 0.5
            if (this.y < (225 * SCALE)){ // Si l'archer n'est pas encore au sol
                this.y = (225 * SCALE); // On le place au sol
            }
        }

        if(this.compteurJump == 0 && this.compteurAttack == 0 && this.y == (225 * SCALE)){ // Si le l'archer n'est pas en train de sauter et n'est pas en train d'attaquer

            this.direction = "idle"; // On change la direction de l'archer a celle de base
            if (this.position.equals("droite")) { // Si l'archer est le joueur 1
                if (this.keyboard.getUpPlayer1().isPressed()) { // Si l'archer du joueur 1 appuie sur la touche haut
                    yPrime = -(3 * SCALE); // On le fait sauter
                    this.direction = "up"; // On change la direction de l'archer
                    this.compteurJump = 0; // On remet le compteur de saut a 0
                }

                if (this.keyboard.getDownPlayer1().isPressed()) { // Si l'archer du joueur 1 appuie sur la touche bas
                    this.direction = "down"; // On change la direction de l'archer
                    this.compteurAttack = 0; // On remet le compteur d'attaque a 0
                }

                if (this.keyboard.getLeftPlayer1().isPressed()) { // Si l'archer du joueur 1 appuie sur la touche gauche
                    xPrime--; // On le deplace a gauche
                    this.direction = "left"; // On change la direction de l'archer
                }

                if (this.keyboard.getRightPlayer1().isPressed()) { // Si l'archer du joueur 1 appuie sur la touche droite
                    xPrime++; // On le deplace a droite
                    this.direction = "right"; // On change la direction de l'archer
                }
            } else { // Si l'archer est le joueur 2
                if (this.keyboard.getUpPlayer2().isPressed()) { // Si l'archer du joueur 2 appuie sur la touche haut
                    yPrime = -(3 * SCALE); // On le fait sauter
                    this.direction = "up"; // On change la direction de l'archer
                    this.compteurJump = 0; // On remet le compteur de saut a 0
                }

                if (this.keyboard.getDownPlayer2().isPressed()) { // Si l'archer du joueur 2 appuie sur la touche bas
                    this.direction = "down"; // On change la direction de l'archer
                    this.compteurAttack = 0; // On remet le compteur d'attaque a 0
                }

                if (this.keyboard.getLeftPlayer2().isPressed()) { // Si l'archer du joueur 2 appuie sur la touche gauche
                    xPrime--; // On le deplace a gauche
                    this.direction = "left"; // On change la direction de l'archer
                }

                if (this.keyboard.getRightPlayer2().isPressed()) { // Si l'archer du joueur 2 appuie sur la touche droite
                    xPrime++; // On le deplace a droite
                    this.direction = "right"; // On change la direction de l'archer
                }
            }

        }

        if (xPrime != 0 || yPrime != 0) {
            move(xPrime, yPrime); // On deplace l'archer
        }

        this.gestionCollision(0, 0); // On verifie si l'archer est en collision avec un autre objet
    }

    // Permet de dessiner l'archer et ses objets associes
    @Override
    public void render(GraphicsContext graphics){
        for (Fleche fleche : this.fleches) { // Pour chaque fleche tirée
            fleche.render(graphics); // On dessine la fleche
        }

        Image archerSprite = this.idle[0]; // On affecte l'image de base a l'image de base de l'archer
        if (this.direction.equals("idle")) { // Si l'archer est immobile
            archerSprite = this.idle[Math.round(this.compteurIdle)]; // on affecte l'image idle a l'image que l'on va afficher

            this.compteurIdle += 0.5; // On augmente le compteur de l'image
            if(this.compteurIdle >= 9){ // Si le compteur de l'image est superieur ou egal a 9
                this.compteurIdle = 0; // On remet le compteur de l'image a 0
            }
        } else { // Si l'archer est en train d'effectuer une action
            switch (this.direction) {
                case "down" -> { // Si l'archer est en train d'effectuer une action de tir
                    archerSprite = this.attack[Math.round(this.compteurAttack)]; // On affecte l'image de l'attaque a l'image que l'on va afficher
                    this.compteurAttack += 0.5; // On augmente le compteur de l'image
                    if (this.compteurAttack >= 5) { // Si le compteur de l'image est superieur ou egal a 5
                        this.compteurAttack = 0; // On remet le compteur de l'image a 0
                        this.direction = "idle"; // On change la direction de l'archer a celle de base

                        this.lancerFleche(); // On lance une fleche
                    }
                }
                case "right", "left" -> { // Si l'archer est en train d'effectuer une action de deplacement gauche/droite
                    archerSprite = this.run[Math.round(this.compteurRun)]; // On affecte l'image de l'animation de marche a l'image que l'on va afficher
                    this.compteurRun += 0.5; // On augmente le compteur de l'image
                    if (this.compteurRun >= 7) { // Si le compteur de l'image est superieur ou egal a 7
                        this.compteurRun = 0; // On remet le compteur de l'image a 0
                    }
                }
                case "up" -> { // Si l'archer est en train d'effectuer une action de saut
                    archerSprite = this.jump[Math.round(this.compteurJump)]; // On affecte l'image de l'animation de saut a l'image que l'on va afficher
                    this.compteurJump += 0.5; // On augmente le compteur de l'image
                    if (this.compteurJump >= 3) {  // Si le compteur de l'image est superieur ou egal a 3
                        this.compteurJump = 0; // On remet le compteur de l'image a 0
                        this.direction = "idle"; // On change la direction de l'archer a celle de base
                    }

                    if (this.position.equals("droite")) this.keyboard.getUpPlayer1().setPressed(false); // On desactive le bouton de saut du joueur 1
                    else this.keyboard.getUpPlayer2().setPressed(false); // On desactive le bouton de saut du joueur 2
                }
            }
        }
        graphics.drawImage(archerSprite, this.x, this.y, archerSprite.getWidth(), archerSprite.getHeight()); // On dessine l'archer avec l'action en cours
    }

    // Permet de gerer les collisions entre un joueur et une fleche
    @Override
    public boolean gestionCollision(int x, int y) {
        if (this.position.equals("droite")) { // Si l'archer est du joueur 1
            Iterator<Fleche> iteratorFlecheAdversaire = this.game.getAdversaire().getFleches().iterator(); // On cree un iterateur sur les fleches du joueur 2
            while (iteratorFlecheAdversaire.hasNext()) { // Tant qu'il y a des fleches du joueur 2
                if (this.getX() + (60 * SCALE) >= iteratorFlecheAdversaire.next().getX()) { // Si la fleche a toucher l'archer du joueur 1
                    this.life -=20; // On enleve 20 points de vie au joueur 1
                    iteratorFlecheAdversaire.remove(); // On supprime la fleche du joueur 2
                }
            }
        } else { // Si l'archer est du joueur 2
            Iterator<Fleche> iteratorFlecheArcher = this.game.getArcher().getFleches().iterator(); // On cree un iterateur sur les fleches du joueur 1
            while (iteratorFlecheArcher.hasNext()) { // Tant qu'il y a des fleches du joueur 1
                if (this.getX() + (20 * SCALE) <= iteratorFlecheArcher.next().getX()) { // Si la fleche a toucher l'archer du joueur 2
                    this.life -=20; // On enleve 20 points de vie au joueur 2
                    iteratorFlecheArcher.remove(); // On supprime la fleche du joueur 1
                }
            }
        }

        return true;
    }

    // Permet de lancer une fleche
    public void lancerFleche(){
        Fleche fleche; // On cree une fleche
        if (this.position.equals("droite")) fleche = new Fleche(4, this.x + (60 * SCALE), this.y + (40 * SCALE), this.keyboard, "droite"); // Si l'archer est du joueur 1 // On cree une fleche du joueur 1
        else fleche = new Fleche(4, this.x + (20 * SCALE), this.y + (40 * SCALE), this.keyboard, "gauche"); // Si l'archer est du joueur 2 // On cree une fleche du joueur 2

        this.fleches.add(fleche); // On ajoute la fleche a la liste des fleches du joueur
    }

    // Getters
    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public int getLifeMax() {
        return this.lifeMax;
    }

    // Permet de recuperer l'ensemble des images en fonction du joueur 1 ou 2
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
