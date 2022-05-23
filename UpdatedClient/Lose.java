import java.awt.*;
import javax.swing.*;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lose extends JFrame implements ActionListener
{
    public Lose(){
        Font f = new Font("TimesRoman", Font.PLAIN, 30);

        setSize(480,340);
        JPanel Background = new JPanel();

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
        newGame.setBorder(null);
        newGame.setFont(f);
        Background.add(newGame);
        

        JButton exit = new JButton("EXIT");
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.white);
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
        if(e.getActionCommand().equals("EXIT"))
        {
            this.dispose();
        }
        if(e.getActionCommand().equals("NEW GAME"))
        {
            //facci quello che ti serve
        }
    }
}