import java.net.Socket;
import java.io.*;

import javax.swing.JFrame;

public class GameStart extends JFrame {

    public GameStart (Client player) {
        add(new Game(player));
    }
    public static void main(String[] args) throws java.io.IOException {
        InputStreamReader inputStringa = new InputStreamReader(System.in);
        BufferedReader tastiera = new BufferedReader(inputStringa);

        System.out.println("Inserisci il nome utente: ");
        String nickname = tastiera.readLine();
        User user = new User(nickname, "Margie");

        Socket socket = new Socket("127.0.0.1", 9999);
        Client player = new Client(socket);

        GameStart game = new GameStart(player, "127.0.0.1", 9999);
        game.setVisible(true);
        game.setTitle("Giochino");
        game.setSize(380, 420);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
    }
}
