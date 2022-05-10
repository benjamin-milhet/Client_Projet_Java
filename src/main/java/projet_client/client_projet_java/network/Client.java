package projet_client.client_projet_java.network;

import projet_client.client_projet_java.Game;
import projet_client.client_projet_java.input.Keyboard;
import projet_client.client_projet_java.modeles.Archer;

import java.net.*;
import java.io.*;

import static projet_client.client_projet_java.Game.SCALE;

public class Client{

    private Game game;
    private Socket socket;

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Game game, Keyboard keyboard) {
        try {
            this.socket = new Socket("localhost", 8080); // Permet de se connecter à un serveur distant sur le port 8080
            this.game = game;
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.game.addAdversaire(new Archer("archer", 10, 50 * SCALE,225 * SCALE,100, keyboard));
        } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage(String message) throws IOException {
        this.bufferedWriter.write(message);
        this.bufferedWriter.newLine();
        this.bufferedWriter.flush();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
