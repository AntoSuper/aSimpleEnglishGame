import java.awt.event.*;
import javax.swing.*;
import java.net.*;

import javax.swing.SwingUtilities;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class GameStart extends JFrame implements ActionListener, WindowListener {

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
        this.addWindowListener(this);
        setSize(375, 420);
        setResizable(false);

        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        game = new Game(nickname, character, IP, port);
        add(game);

        MyDefaultMetalTheme a=new MyDefaultMetalTheme();
        
        MetalLookAndFeel.setCurrentTheme(a);
        try {
          UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
          e.printStackTrace();
        }
    
        SwingUtilities.updateComponentTreeUI(this);
        
        t = new Timer(100, this);
        t.setActionCommand("timer"); 
        t.start();

        setVisible(true);
    }

    public void actionPerformed (ActionEvent e) {
        if (game.getClosing()) {

            openConnection(game.IP, game.port, game.player);
            client.logout();
            t.stop();

            this.setVisible(false);
            dispose();
        }
    }

    public void windowOpened (WindowEvent e) {
        
    }
    
    public void windowClosing (WindowEvent e) {
        openConnection(game.IP, game.port, game.player);
        client.logout();
        t.stop();

        this.setVisible(false);
        dispose();
    }
    
    public void windowClosed (WindowEvent e) {
    
    }
    
    public void windowIconified (WindowEvent e) {
        
    }
    
    public void windowDeiconified (WindowEvent e) {
        
    }
    
    public void windowActivated (WindowEvent e) {
        
    }
    
    public void windowDeactivated (WindowEvent e) {
        
    }
}