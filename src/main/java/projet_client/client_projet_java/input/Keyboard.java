package projet_client.client_projet_java.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Keyboard {
    private final Key up;
    private final Key down;
    private final Key left;
    private final Key right;

    public Keyboard(Scene scene) {
        this.up = new Key();
        this.down = new Key();
        this.left = new Key();
        this.right = new Key();

        scene.setOnKeyPressed(event -> pressedOnKey(event.getCode(), true));

        scene.setOnKeyReleased(event -> pressedOnKey(event.getCode(), false));
    }


    public void pressedOnKey(KeyCode keyCode, boolean isPressed) {
        switch (keyCode) {
            case W, Z -> this.up.setPressed(isPressed);
            case S -> this.down.setPressed(isPressed);
            case A, Q -> this.left.setPressed(isPressed);
            case D -> this.right.setPressed(isPressed);
        }
    }

    public Key getUp() {
        return up;
    }

    public Key getDown() {
        return down;
    }

    public Key getLeft() {
        return left;
    }

    public Key getRight() {
        return right;
    }
}
