import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Lose extends JFrame implements ActionListener, WindowListener
{
    public Lose(){
        super ("Defeat");
        
        this.addWindowListener(this);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

        Font f = new Font("Serif", Font.BOLD, 30);

        setSize(480,340);
        JPanel Background = new JPanel();

        MyDefaultMetalTheme a=new MyDefaultMetalTheme();
        
        MetalLookAndFeel.setCurrentTheme(a);
        try {
          UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
          e.printStackTrace();
        }

        Background.setOpaque(true);
        Background.setLayout(new GridLayout(1,2));
        Background.setBackground(Color.BLACK);
        Background.setForeground(Color.BLACK);

        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        ImagePanel lose =new ImagePanel("images/lose.gif");
        
        JButton newGame = new JButton("NEW GAME");
        newGame.setBorderPainted(false);
        newGame.setBackground(Color.BLACK);
        newGame.setForeground(Color.white);
        newGame.setHorizontalAlignment(JButton.CENTER);
        newGame.setBorder(null);
        newGame.setFont(f);
        Background.add(newGame);
        

        JButton exit = new JButton("QUIT");
        exit.setBorderPainted(false);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.white);
        exit.setHorizontalAlignment(JButton.CENTER);
        exit.setBorder(null);
        exit.setFont(f);
        Background.add(exit);
        
        exit.addActionListener(this);
        newGame.addActionListener(this);

        add(lose, BorderLayout.CENTER);
        add(Background,BorderLayout.SOUTH );

        setVisible(true);

    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("QUIT"))
        {
            setVisible(false);
            dispose();
        }
        if(e.getActionCommand().equals("NEW GAME"))
        {
            GameLogin g = new GameLogin();
            setVisible(false);
            dispose();
        }
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