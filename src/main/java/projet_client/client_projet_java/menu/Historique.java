package projet_client.client_projet_java.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import projet_client.client_projet_java.Main;
import projet_client.client_projet_java.fichier.GestionFichier;

import java.io.IOException;

// Classe qui gère l'historique des scores
public class Historique {

    public ListView<String> listScore; // Liste des scores

    // Methode permettant de fermer la fentre en cours et de revenir au menu
    public void retourMenu() throws IOException {
        Stage recupStage = (Stage) listScore.getScene().getWindow(); // Récupération de la fenetre en cours
        recupStage.close(); // Fermeture de la fenetre en cours

        Stage stage = new Stage(); // Création d'une nouvelle fenetre
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/menu/Menu.fxml")); // Emplacement du fichier fxml du menu
        Scene scene = new Scene(fxmlLoader.load()); // Création de la scene du menu
        stage.setTitle("Menu"); // Titre de la fenetre du menu
        stage.setScene(scene);

        stage.show(); // Affichage de la fenetre
    }

    // Methode permettant de charger l'historique des scores
    public void afficherScore() throws IOException {
        this.listScore.setItems(GestionFichier.read("Score.txt"));
    }

    // Methode qui est initialisée lorsque le fichier fxml est chargé
    @FXML
    public void initialize() throws IOException {
        afficherScore();
    }


}
