package projet_client.client_projet_java.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet_client.client_projet_java.Game;
import projet_client.client_projet_java.Main;

import java.io.IOException;

// Classe qui gère le menu
public class Menu {

    public TextField fieldJ1, fieldJ2; // Champs de texte pour les noms des joueurs

    // Methode permettant de lancer le jeu
    public void startGame() {
        if (!fieldJ1.getText().isEmpty() && !fieldJ2.getText().isEmpty()) { // Si les champs de texte ne sont pas vides
            Stage recupStage = (Stage) fieldJ1.getScene().getWindow(); // Récupération de la fenetre en cours
            recupStage.close(); // Fermeture de la fenetre en cours

            Stage stage = new Stage(); // Création d'une nouvelle fenetre

            Game game = new Game(stage, fieldJ1.getText(), fieldJ2.getText()); // Création d'un nouveau jeu avec les noms des joueurs
            game.start(); // Lancement du jeu
        } else { // Si les champs de texte sont vides
            Alert alert = new Alert(Alert.AlertType.INFORMATION); // Création d'une alerte d'information
            alert.setTitle("Erreur lancement partie"); // Titre de l'alerte

            alert.setHeaderText(null); // Suppression du header de l'alerte
            alert.setContentText("Un des noms de joueur est vide !"); // Message de l'alerte

            alert.showAndWait(); // Affichage de l'alerte
        }
    }

    // Methode lance la fentre de l'historique des scores
    public void afficherHistorique() throws IOException {
        Stage recupStage = (Stage) fieldJ1.getScene().getWindow(); // Récupération de la fenetre en cours
        recupStage.close(); // Fermeture de la fenetre en cours

        Stage stage = new Stage(); // Création d'une nouvelle fenetre
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/menu/Historique.fxml")); // Emplacement du fichier fxml de l'historique
        Scene scene = new Scene(fxmlLoader.load()); // Création de la scene de l'historique
        stage.setTitle("Historique"); // Titre de la fenetre de l'historique
        stage.setScene(scene);

        stage.show(); // Affichage de la fenetre
    }
}
