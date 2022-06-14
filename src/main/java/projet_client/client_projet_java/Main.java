package projet_client.client_projet_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // Initialisation du programme
    public static void main(String[] args) {
        launch(args);
    }

    // Lancement du menu du jeu avec javaFX
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/menu/Menu.fxml")); // Emplacement du fichier fxml du menu
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu"); // Titre de la fenetre du menu
        stage.setScene(scene);

        stage.show(); // Affichage de la fenetre
    }


}
