import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Win extends JFrame implements ActionListener
{
    public Win()
    {
        setSize(500,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        
        JPanel win=new JPanel();
        JLabel w=new JLabel("CONGRATULATIONS, YOU WON!!!");

        w.setForeground(new Color(187,161,79));
        w.setFont(new Font("Serif",Font.BOLD,28));
        
        win.setBackground(new Color(164,209,162));
        win.add(w);
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
            dispose();
            //aggiungi cose relative al server
        }
        if(e.getActionCommand().equals("NEW GAME"))
        {
            //fai quello che devi fare 
        }
    }
}