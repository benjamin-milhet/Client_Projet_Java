package projet_client.client_projet_java.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

// classe qui permet de gerer les touches du clavier
public class Keyboard {
    private final Key upPlayer1, downPlayer1, leftPlayer1, rightPlayer1, upPlayer2, downPlayer2, leftPlayer2, rightPlayer2; // touches du joueur 1 et 2

    // constructeur de la classe qui initialise les touches du joueur 1 et 2
    public Keyboard(Scene scene) {
        // touches du joueur 1
        this.upPlayer1 = new Key();
        this.downPlayer1 = new Key();
        this.leftPlayer1 = new Key();
        this.rightPlayer1 = new Key();

        // touches du joueur 2
        this.upPlayer2 = new Key();
        this.downPlayer2 = new Key();
        this.leftPlayer2 = new Key();
        this.rightPlayer2 = new Key();

        scene.setOnKeyPressed(event -> pressedOnKey(event.getCode(), true)); // lorsqu'une touche est appuyee

        scene.setOnKeyReleased(event -> pressedOnKey(event.getCode(), false)); // lorsqu'une touche est relachee
    }


    public void pressedOnKey(KeyCode keyCode, boolean isPressed) {
        // Permet de changer l'etat d'une touche
        switch (keyCode) {
            case W, Z -> this.upPlayer1.setPressed(isPressed);
            case S -> this.downPlayer1.setPressed(isPressed);
            case A, Q -> this.leftPlayer1.setPressed(isPressed);
            case D -> this.rightPlayer1.setPressed(isPressed);
            case UP -> this.upPlayer2.setPressed(isPressed);
            case DOWN -> this.downPlayer2.setPressed(isPressed);
            case LEFT -> this.leftPlayer2.setPressed(isPressed);
            case RIGHT -> this.rightPlayer2.setPressed(isPressed);
        }
    }

    // Getters permettant de savoir si une touche est appuyee ou non

    public Key getUpPlayer1() {
        return upPlayer1;
    }

    public Key getDownPlayer1() {
        return downPlayer1;
    }

    public Key getLeftPlayer1() {
        return leftPlayer1;
    }

    public Key getRightPlayer1() {
        return rightPlayer1;
    }

    public Key getUpPlayer2() {
        return this.upPlayer2;
    }

    public Key getDownPlayer2() {
        return this.downPlayer2;
    }

    public Key getLeftPlayer2() {
        return this.leftPlayer2;
    }

    public Key getRightPlayer2() {
        return this.rightPlayer2;
    }
}
