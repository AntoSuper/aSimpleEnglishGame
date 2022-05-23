import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.*;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.*;
import java.awt.event.*;

public class GameLogin extends JFrame implements KeyListener, ActionListener {

    private JLabel address = new JLabel("IP Address: ", JLabel.CENTER);
    private JLabel username = new JLabel("Nickname: ", JLabel.CENTER);
    private JLabel character = new JLabel("Character: ", JLabel.CENTER);
    private JTextField IP = new JTextField("127.0.0.1");
    private JTextField nickname = new JTextField();

    private String[] names = {"Tommy", "Margie"};
    private JComboBox choose = new JComboBox(names);
    private int port = 9999;

    public static void main (String[] args) {
        new GameLogin();
    }

    public GameLogin() {
        super("Login");
        this.setSize(250,100);
        setLayout(new GridLayout(3,3));
        
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

        address.setOpaque(true);
        address.setBackground(new Color(173,216,230));
        this.add(address);

        IP.setBorder(null);
        IP.setBackground(new Color(173,216,230));
        this.add(IP);

        username.setBackground(new Color(173,216,230));
        username.setOpaque(true);
        this.add(username);

        nickname.setBorder(null);
        nickname.setBackground(new Color(173,216,230));
        this.add(nickname);

        character.setBackground(new Color(173,216,230));
        character.setOpaque(true);
        this.add(character);

        choose.setBorder(null);
        choose.setBackground(new Color(173,216,230));
        this.add(choose);
        
        addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                nickname.requestFocusInWindow();
            }
        });
        
        IP.addKeyListener(this);
        nickname.addKeyListener(this);
        
        MyDefaultMetalTheme a=new MyDefaultMetalTheme();
        
        MetalLookAndFeel.setCurrentTheme(a);
        try {
          UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
          e.printStackTrace();
        }
    
        SwingUtilities.updateComponentTreeUI(this);

        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    

    public void actionPerformed(ActionEvent e) {

    }

    public void keyPressed(KeyEvent e) {  
        if(e.getKeyCode()==10) {
            try {
                GameStart game = new GameStart(nickname.getText(), (String)choose.getSelectedItem(), IP.getText(), port);
                setVisible(false);
                dispose();
            }
            catch (Exception g) {
                g.printStackTrace();
            }
        }
    }

    public void keyReleased(KeyEvent e){

    }

    public void keyTyped(KeyEvent e){

    }
}
