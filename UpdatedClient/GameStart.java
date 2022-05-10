import java.io.*;

import javax.swing.JFrame;

public class GameStart extends JFrame {

    public GameStart (String nickname, String character, String IP, int port) {
        super("Game");
        setVisible(true);
        setSize(380, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new Game(nickname, character, IP, port));
    }
}