package projet_client;

import java.net.*;
import java.io.*;

public class Client {

    public Client() throws IOException {
        Socket socket = new Socket("localhost", 8080);
    }
}
