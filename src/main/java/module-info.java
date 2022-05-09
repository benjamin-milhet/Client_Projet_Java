module projet_client.client_projet_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens projet_client.client_projet_java to javafx.fxml;
    exports projet_client.client_projet_java;
    exports projet_client.client_projet_java.network;
    opens projet_client.client_projet_java.network to javafx.fxml;
}