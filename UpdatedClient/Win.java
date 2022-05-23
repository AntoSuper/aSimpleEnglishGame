import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Win extends JFrame implements ActionListener
{
    public Win()
    {
        super("Victory");
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

        setSize(500,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        MyDefaultMetalTheme a=new MyDefaultMetalTheme();
        
        MetalLookAndFeel.setCurrentTheme(a);
        try {
          UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        JPanel win = new JPanel();
        win.setLayout(new BorderLayout());

        JLabel w = new JLabel("CONGRATULATIONS");
        w.setForeground(Color.RED);
        w.setHorizontalAlignment(JLabel.CENTER);
        w.setFont(new Font("Serif",Font.BOLD,28));

        JLabel w1 = new JLabel("YOU HAVE FOUND THE BOOK");
        w1.setForeground(Color.RED);
        w1.setHorizontalAlignment(JLabel.CENTER);
        w1.setFont(new Font("Serif",Font.BOLD,28));
        
        win.setBackground(new Color(164,209,162));
        win.add(w, BorderLayout.NORTH);
        win.add(w1, BorderLayout.SOUTH);
        add(win,BorderLayout.NORTH);
        
        JPanel empty=new JPanel();
        JLabel emp=new JLabel("                        ");
        empty.setBackground(new Color(164,209,162));
        empty.add(emp);
        add(empty,BorderLayout.WEST);
        
        ImagePanel book = new ImagePanel("images/book.gif");
        book.setBackground(new Color(164,209,162));
        add(book,BorderLayout.CENTER);
        
        JPanel buttons=new JPanel();
        buttons.setLayout(new GridLayout(2,2));
        
        JButton quit=new JButton("QUIT");
        quit.setBorder(null);
        quit.setBackground(new Color(164,209,162));
        quit.setForeground(new Color(0,100,0));
        quit.setFont(new Font("Serif",Font.BOLD,30));
        quit.addActionListener(this);
        
        JButton newGame=new JButton("NEW GAME");
        newGame.setBorder(null);
        newGame.setBackground(new Color(164,209,162));
        newGame.setForeground(new Color(0,100,0));
        newGame.setFont(new Font("Serif",Font.BOLD,30));
        newGame.addActionListener(this);
        
        JLabel empty1=new JLabel("             ");
        empty1.setBackground(new Color(164,209,162));
        empty1.setOpaque(true);
        
        JLabel empty2=new JLabel("             ");
        empty2.setBackground(new Color(164,209,162));
        empty2.setOpaque(true);
        
        buttons.add(quit);
        buttons.add(newGame);
        buttons.add(empty1);
        buttons.add(empty2);
        
        add(buttons,BorderLayout.SOUTH);
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
}
