package projet_client.client_projet_java.input;

// classe qui permet de gerer les touches du clavier
public class Key {
    public boolean pressed = false; // variable qui permet de savoir si la touche est appuyee ou non

    // permet de changer l'etat d'une touche
    public void setPressed(boolean isPressed) {
        this.pressed = isPressed;
    }

    // permet de savoir si une touche est appuyee ou non
    public boolean isPressed() {
        return this.pressed;
    }
}
