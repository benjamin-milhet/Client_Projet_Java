package projet_client.client_projet_java.input;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Keyboard {

    private ArrayList<Key> keys;
    private Key up;
    private Key down;
    private Key left;
    private Key right;

    public Keyboard(Scene scene) {
        this.keys = new ArrayList<>();

        this.up = new Key();
        this.down = new Key();
        this.left = new Key();
        this.right = new Key();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                pressedOnKey(event.getCode(), true);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                pressedOnKey(event.getCode(), false);
            }
        });
    }


    public void pressedOnKey(KeyCode keyCode, boolean isPressed) {
        System.out.println(keyCode.toString());
        switch (keyCode) {
            case W:
                this.up.setPressed(isPressed);
                break;
            case S:
                this.down.setPressed(isPressed);
                break;
            case A:
                this.left.setPressed(isPressed);
                break;
            case D:
                this.right.setPressed(isPressed);
                break;
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
