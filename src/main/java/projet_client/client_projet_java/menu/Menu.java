package projet_client.client_projet_java.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet_client.client_projet_java.Game;
import projet_client.client_projet_java.Main;

import java.io.IOException;

public class Menu {

    public TextField fieldJ1, fieldJ2;

    public void startGame() {
        if (!fieldJ1.getText().isEmpty() && !fieldJ2.getText().isEmpty()) {
            Stage recupStage = (Stage) fieldJ1.getScene().getWindow();
            recupStage.close();

            Stage stage = new Stage();

            Game game = new Game(stage, fieldJ1.getText(), fieldJ2.getText());
            game.start();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur lancement partie");

            alert.setHeaderText(null);
            alert.setContentText("Un des noms de joueur est vide !");

            alert.showAndWait();
        }
    }

    public void afficherHistorique() throws IOException {
        Stage recupStage = (Stage) fieldJ1.getScene().getWindow();
        recupStage.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/menu/Historique.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Historique");
        stage.setScene(scene);

        stage.show();
    }
}
