package projet_client.client_projet_java.input;

import projet_client.client_projet_java.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Keyboard implements KeyListener {

    private ArrayList<Key> keys;
    private Key up;
    private Key down;
    private Key left;
    private Key right;

    public Keyboard(Game game) {
        this.keys = new ArrayList<>();

        this.up = new Key();
        this.down = new Key();
        this.left = new Key();
        this.right = new Key();

        //game.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedOnKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedOnKey(e.getKeyCode(), false);
    }

    public void pressedOnKey(int keyCode, boolean isPressed) {
        switch (keyCode) {
            case KeyEvent.VK_Z:
                this.up.setPressed(isPressed);
                break;
            case KeyEvent.VK_S:
                this.down.setPressed(isPressed);
                break;
            case KeyEvent.VK_Q:
                this.left.setPressed(isPressed);
                break;
            case KeyEvent.VK_D:
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
