module projet_client.client_projet_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens projet_client.client_projet_java to javafx.fxml;
    exports projet_client.client_projet_java;
    exports projet_client.client_projet_java.menu;
    opens projet_client.client_projet_java.menu to javafx.fxml;
}