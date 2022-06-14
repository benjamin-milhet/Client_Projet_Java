package projet_client.client_projet_java.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import projet_client.client_projet_java.Main;
import projet_client.client_projet_java.fichier.GestionFichier;

import java.io.IOException;

public class Historique {

    public ListView listScore;
    public void retourMenu() throws IOException {
        Stage recupStage = (Stage) listScore.getScene().getWindow();
        recupStage.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/menu/Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu");
        stage.setScene(scene);

        stage.show();
    }

    public void afficherScore() throws IOException {
        listScore.setItems(GestionFichier.read("Score.txt"));
    }


    @FXML
    public void initialize() throws IOException {
        afficherScore();
    }


}
