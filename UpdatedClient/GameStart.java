import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class GameStart extends JFrame implements ActionListener {

    private Game game;
    private Client client;
    private Socket socket;
    private Timer t;

    public void openConnection (String IP, int port, User user) {
        try {
            socket = new Socket(IP, port);
            client = new Client(socket, user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameStart (String nickname, String character, String IP, int port) {
        super("Game");
        setVisible(true);
        setSize(380, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        game = new Game(nickname, character, IP, port);
        add(game);
        t = new Timer(1000, this);
        t.setActionCommand("timer"); 
        t.start();
    }

    public void actionPerformed (ActionEvent e) {
        if (game.getClosing()) {

            openConnection(game.IP, game.port, game.player);
            game.client.logout();

            this.setVisible(false);
            dispose();
            t.stop();
        }
    }
}