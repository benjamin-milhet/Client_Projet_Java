package projet_client.input;

public class Key {
    public boolean pressed = false;

    public void setPressed(boolean isPressed) {
        this.pressed = isPressed;
    }

    public boolean isPressed() {
        return this.pressed;
    }
}
