module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo1 to javafx.fxml;
    opens DataSecurity to javafx.fxml;

    exports DataSecurity;
    exports com.example.demo1;
}