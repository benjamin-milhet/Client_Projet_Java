package projet_client.client_projet_java.modeles.entity;

import projet_client.client_projet_java.input.Keyboard;

import static projet_client.client_projet_java.Game.SCALE;

public class FabriqueEntity {

    public static Entity fabrique(String nom, int speed, int x, int y, int life, Keyboard keyboard) {
        Entity res = null;

        return switch (nom) {
            case "Archer" -> new Archer("archer", speed, x * SCALE, y * SCALE, life, keyboard);
            default -> res;
        };

    }
}
