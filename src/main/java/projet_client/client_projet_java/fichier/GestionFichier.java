package projet_client.client_projet_java.fichier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

// classe qui gere les fichiers
public class GestionFichier {
    // methode statique qui ecrit dans un fichier
    public static void write(String url, String data) throws IOException {
        FileWriter fileWriter = new FileWriter(url, true); // Recuperation du fichier dans lequel ecrire
        BufferedWriter writer = new BufferedWriter(fileWriter); // Creation d'un bufferedWriter qui utilise le fileWriter

        try {
            writer.write(data); // Ecriture dans le fichier
            writer.newLine(); // Retour à la ligne
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.close(); // Fermeture du fichier
    }

    // methode statique qui recupere les informations d'un fichier
    public static ObservableList<String> read(String url) throws IOException {
        FileReader fileReader = new FileReader(url); // Recuperation du fichier dans lequel lire

        BufferedReader reader = new BufferedReader (fileReader); // Creation d'un bufferedReader qui utilise le fileReader
        ObservableList<String> data = FXCollections.observableArrayList(); // Creation d'une liste observable de retour

        try {
            String line = reader.readLine(); // Lecture de la ligne
            while (line != null) {
                data.add(line); // Ajout de la ligne dans la liste
                line = reader.readLine(); // Lecture de la prochaine ligne
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        reader.close(); // Fermeture du fichier

        return data; // Retour de la liste
    }
}
