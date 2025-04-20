package DataSecurity;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button back1;

    @FXML
    private TextField cipherfx;

    @FXML
    private TextField cipherfx1;

    @FXML
    private AnchorPane decrypt;

    @FXML
    private Button dekriptimfx;

    @FXML
    private Button enkriptimfx;

    @FXML
    private AnchorPane enkriptoanglisht;

    @FXML
    private Button enkriptofx;

    @FXML
    private Button enkriptofx1;

    @FXML
    private TextField keyfx;

    @FXML
    private TextField keyfx1;

    @FXML
    private GridPane matrixfx;

    @FXML
    private GridPane matrixfx1;

    @FXML
    private AnchorPane modifx;

    @FXML
    private TextField plaintextfx;

    @FXML
    private TextField plaintextfx1;

    @FXML
    private TextField shkronja;

    @FXML
    private TextField shkronja1;

    @FXML
    private Button clear;

    @FXML
    private Button clear2;

    private void hideAllScreens() {
        modifx.setVisible(false);
        enkriptoanglisht.setVisible(false);
        decrypt.setVisible(false);
    }

    public void dekriptoenglish() {
        hideAllScreens();
        decrypt.setVisible(true);
    }

    public void zgjedhmodin() {
        hideAllScreens();
        enkriptoanglisht.setVisible(true);
    }

    public void enkriptoenglish() {
        hideAllScreens();
        enkriptoanglisht.setVisible(true);
    }

    public void kthehu() {
        hideAllScreens();
        modifx.setVisible(true);
    }

    public void showMatrix(char[][] matrix) {
        matrixfx.getChildren().clear();
        matrixfx.setGridLinesVisible(true);
        matrixfx.setHgap(5);
        matrixfx.setVgap(5);
        matrixfx.setAlignment(Pos.CENTER);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Label label = new Label(String.valueOf(matrix[i][j]));
                label.setMinSize(50, 50);
                label.setAlignment(Pos.CENTER);
                label.setStyle("-fx-border-color: black; -fx-font-size: 18px; -fx-background-color: #f0f0f0;");
                matrixfx.add(label, j, i);
            }
        }
    }
    public void showMatrix1(char[][] matrix) {
        matrixfx1.getChildren().clear();
        matrixfx1.setGridLinesVisible(true);
        matrixfx1.setHgap(5);
        matrixfx1.setVgap(5);
        matrixfx1.setAlignment(Pos.CENTER);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Label label = new Label(String.valueOf(matrix[i][j]));
                label.setMinSize(50, 50);
                label.setAlignment(Pos.CENTER);
                label.setStyle("-fx-border-color: black; -fx-font-size: 18px; -fx-background-color: #f0f0f0;");
                matrixfx1.add(label, j, i);
            }
        }
    }

    public void clearmethod() {
        plaintextfx.clear();
        keyfx.clear();
        cipherfx.clear();
        shkronja.clear();
        matrixfx.getChildren().clear();
    }

    public void clearmethod1() {
        plaintextfx1.clear();
        keyfx1.clear();
        cipherfx1.clear();
        shkronja1.clear();
        matrixfx1.getChildren().clear();
    }

    public void enkripto() {
        String plain;
        String key;
        if (keyfx.getText().isEmpty()) {
            key = "abc";
        } else {
            key = keyfx.getText();
        }
        if (plaintextfx.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem shkruani plaintext!");
            alert.showAndWait();
            return;
        } else {
            plain = plaintextfx.getText();
        }
        if (shkronja.getText().length() > 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem shkruani vetem nje shkronje te opsioni letter!");
            alert.showAndWait();
            return;
        }

        Playfair play = new Playfair(key, plain, shkronja.getText().charAt(0));

        play.prepareKey();
        play.generateMatrix();
        String formattedText = play.formatText();
        play.setPlainText(formattedText);

        String encText = play.encrypt();
        showMatrix(play.getMatrix());
        cipherfx.setText(encText);
    }

    public void dekripto() {
        String plain1;
        String key1;
        if (keyfx1.getText().isEmpty()) {
            key1 = "abc";
        } else {
            key1 = keyfx1.getText();
        }
        if (plaintextfx1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem shkruani plaintext!");
            alert.showAndWait();
            return;
        } else {
            plain1 = plaintextfx1.getText();
        }
        if (shkronja1.getText().length() > 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem shkruani vetem nje shkronje te opsioni letter!");
            alert.showAndWait();
            return;
        }

        Playfair play = new Playfair(key1, plain1, shkronja1.getText().charAt(0));

        play.prepareKey();
        play.generateMatrix();
        String formattedText = play.formatText();
        play.setPlainText(formattedText);

        String encText = play.decrypt();
        showMatrix1(play.getMatrix());
        cipherfx1.setText(encText);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifx.setVisible(true);
        enkriptoanglisht.setVisible(false);
        decrypt.setVisible(false);
    }
}