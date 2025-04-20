module com.example.playfaircipher {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.playfaircipher to javafx.fxml;
    opens DataSecurity to javafx.fxml;

    exports DataSecurity;
    exports com.example.playfaircipher;
}