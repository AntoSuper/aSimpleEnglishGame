import java.io.*;

import javax.swing.JFrame;

public class GameStart extends JFrame {

    private String IP = "127.0.0.1";
    private int port = 9999;

    public GameStart (String nickname, String character) {
        add(new Game(nickname, character, IP, port));
    }
    public static void main(String[] args) throws java.io.IOException {
        InputStreamReader inputStringa = new InputStreamReader(System.in);
        BufferedReader tastiera = new BufferedReader(inputStringa);

        System.out.println("Inserisci il nome utente: ");
        String nickname = tastiera.readLine();
        System.out.println("Inserisci character ");
        String character = tastiera.readLine();

        GameStart game = new GameStart(nickname, character);
        game.setVisible(true);
        game.setTitle("Giochino");
        game.setSize(380, 420);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
    }
}
