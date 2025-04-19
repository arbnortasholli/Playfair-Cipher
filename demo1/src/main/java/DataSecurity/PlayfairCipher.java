package DataSecurity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

public class PlayfairCipher extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayfairCipher.class.getResource("Anglisht.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 555);
        stage.setTitle("Playfair Cipher!");
        stage.setScene(scene);
        stage.show();
    }
}

class Playfair {
    private String key, plainText;
    private final char[][] matrix = new char[5][5];
    private char replacedChar;

    public char[][] getMatrix() {
        return matrix;
    }

    public Playfair(String key, String plainText, char replacedChar) {
        this.key = key.toLowerCase();
        this.plainText = plainText.toLowerCase();
        this.replacedChar = replacedChar;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public void prepareKey() {
        LinkedHashSet<Character> unique = new LinkedHashSet<>();
        for (char c : key.toCharArray()) {
            if (c != replacedChar) {
                unique.add(c);
            }
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (c != replacedChar) {
                unique.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        unique.forEach(sb::append);
        key = sb.toString();
    }

    public void generateMatrix() {
        if (key.length() < 25) {
            System.out.println("Key must contain at least 25 unique characters (excluding '" + this.replacedChar + "').");
            return;
        }

        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = key.charAt(k++);
            }
        }

    }
}
