package projet_client.client_projet_java.fichier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class GestionFichier {
    public static void write(String url, String data) throws IOException {
        FileWriter fileWriter = new FileWriter(url, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        try {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.close();
    }

    public static ObservableList read(String url) throws IOException {
        FileReader fileReader = new FileReader(url);

        BufferedReader reader = new BufferedReader (fileReader);
        ObservableList data = FXCollections.observableArrayList();

        try {
            String line = reader.readLine();
            while (line != null) {
                data.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        reader.close();

        return data;
    }
}
