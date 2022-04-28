package projet_client.input;

public class Key {
    public boolean pressed = false;

    public void setPressed(boolean isPressed) {
        if (isPressed != this.pressed) {
            this.pressed = isPressed;
        }
    }

    public boolean isPressed() {
        return this.pressed;
    }
}
