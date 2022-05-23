import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GameLogin extends JFrame implements KeyListener, ActionListener, WindowListener {

    private JLabel address = new JLabel("IP Address: ", JLabel.CENTER);
    private JLabel username = new JLabel("Nickname: ", JLabel.CENTER);
    private JLabel character = new JLabel("Character: ", JLabel.CENTER);
    private JLabel story = new JLabel("Story: ", JLabel.CENTER);
    private JTextField IP = new JTextField("127.0.0.1");
    private JTextField nickname = new JTextField();
    private String[] names = {"Tommy", "Margie"};
    private JComboBox choose = new JComboBox(names);
    private JButton openStory = new JButton("Press here!");

    private int port = 9999;

    public static void main (String[] args) {
        new GameLogin();
    }

    public GameLogin() {
        super("Login");
        this.setSize(250,100);
        setLayout(new GridLayout(4,4));
        this.addWindowListener(this);
        
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

        story.setBackground(new Color(173,216,230));
        story.setOpaque(true);
        this.add(story);

        openStory.setBackground(new Color(173,216,230));
        openStory.setOpaque(true);
        openStory.addActionListener(this);
        openStory.setActionCommand("openStory");
        this.add(openStory);

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
        System.out.println("AO");
        if (e.getActionCommand().equals("openStory")) {
            try {
                Desktop.getDesktop().open(new java.io.File("files/story.html"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
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

    public void windowOpened (WindowEvent e) {
        
    }
    
    public void windowClosing (WindowEvent e) {
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
