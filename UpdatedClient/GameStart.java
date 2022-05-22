import java.awt.event.*;
import javax.swing.*;

public class GameStart extends JFrame implements ActionListener {

    private Game game;

    public GameStart (String nickname, String character, String IP, int port) {
        super("Game");
        setVisible(true);
        setSize(380, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        game = new Game(nickname, character, IP, port);
        add(game);
        Timer t = new Timer(100, this);
        t.setActionCommand("timer"); 
        t.start();
    }

    public void actionPerformed (ActionEvent e) {
        if (game.getClosing()) {
            this.setVisible(false);
            dispose();
        }
    }
}