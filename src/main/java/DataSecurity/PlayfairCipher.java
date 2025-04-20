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
        FXMLLoader fxmlLoader = new FXMLLoader(PlayfairCipher.class.getResource("Playfair.fxml"));
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

    public String formatText() {
        StringBuilder sb = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            if (c == replacedChar) {
                sb.append((char) (c - 1));
            } else {
                sb.append(c);
            }
        }
        for (int i = 0; i < sb.length(); i += 2) {
            if (i + 1 >= sb.length()) {
                sb.append('x');
            } else if (sb.charAt(i) == sb.charAt(i + 1)) {
                sb.insert(i + 1, 'x');
            }
        }
        return sb.toString();
    }

    public int[] findPos(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("Character not found in matrix: " + c);
    }


    public String encrypt() {
        String msg = formatText(), cipher = "";
        for (int i = 0; i < msg.length(); i += 2) {
            char a = msg.charAt(i), b = msg.charAt(i + 1);
            int[] posA = findPos(a), posB = findPos(b);

            if (posA[0] == posB[0]) {
                cipher += matrix[posA[0]][(posA[1] + 1) % 5];
                cipher += matrix[posB[0]][(posB[1] + 1) % 5];
            } else if (posA[1] == posB[1]) {
                cipher += matrix[(posA[0] + 1) % 5][posA[1]];
                cipher += matrix[(posB[0] + 1) % 5][posB[1]];
            } else {
                cipher += matrix[posA[0]][posB[1]];
                cipher += matrix[posB[0]][posA[1]];
            }
        }

        return cipher;
    }

    public String decrypt() {
        String msg = plainText;
        String decryptedText = "";
        for (int i = 0; i < msg.length(); i += 2) {
            char a = msg.charAt(i), b = msg.charAt(i + 1);
            int[] posA = findPos(a), posB = findPos(b);

            if (posA[0] == posB[0]) {
                decryptedText += matrix[posA[0]][(posA[1] + 4) % 5];
                decryptedText += matrix[posB[0]][(posB[1] + 4) % 5];
            } else if (posA[1] == posB[1]) {
                decryptedText += matrix[(posA[0] + 4) % 5][posA[1]];
                decryptedText += matrix[(posB[0] + 4) % 5][posB[1]];
            } else {
                decryptedText += matrix[posA[0]][posB[1]];
                decryptedText += matrix[posB[0]][posA[1]];
            }
        }

        return decryptedText;
    }
}