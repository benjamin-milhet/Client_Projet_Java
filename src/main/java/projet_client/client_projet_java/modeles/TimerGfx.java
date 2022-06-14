package projet_client.client_projet_java.modeles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// classe qui permet de gerer l'objet central entre les 2 barres de vie
// Il permetterait d'afficher un timer pour limiter le temps d'une partie
public class TimerGfx extends Objet{

    public TimerGfx(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Permet de mettre a jour les informations de l'objet
    @Override
    public void update() {}

    // Permet de mettre a jour le rendu visuel des objets
    @Override
    public void render(GraphicsContext graphics) {
        Color bleu = Color.rgb(47, 55, 76); // couleur bleu nuit
        graphics.setFill(bleu); // on met la couleur bleu nuit
        graphics.fillRoundRect(this.x-5, this.y, 110, 100, 20, 20); // on dessine le timer
    }
}
