package projet_client.client_projet_java.modeles.entity;

import projet_client.client_projet_java.Game;
import projet_client.client_projet_java.input.Keyboard;

import static projet_client.client_projet_java.Game.SCALE;

// classe qui permet de creer les entites
public class FabriqueEntity {

    // permet de creer une entite
    // fonction non terminer, elle permetterait de creer une entite suivant le personnage choisit (1 seul personnage pour l'instant)
    public static Entity fabrique(Game game, String nom, int speed, int x, int y, int life, Keyboard keyboard, String position) {
        return new Archer(game, nom, speed, x * SCALE, y * SCALE, life, keyboard, position); // on retourne un archer
    }
}
