import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class Start extends JFrame implements ActionListener {
    
    private int x = 250;
    private int y = 250;
    
    private String hotbarWallpaper = "images/hotbarWallpaper.png";
    private String startWallpaper = "images/bookWallpaper.gif";
    
    //private Giochino g;
    
    public static void main (String args []) {
        Start game = new Start ();
    }
    
    public Start () {
        super("Start");
        setLayout(new BorderLayout());
        
        ImagePanel centro = new ImagePanel (hotbarWallpaper);
        centro.setLayout(new BorderLayout());
        
        
        JLabel gameover = new JLabel ("THE FUN THEY HAD");
        gameover.setFont(new Font("SansSerif", Font.BOLD, 20));
        gameover.setForeground(Color.WHITE);
        gameover.setHorizontalAlignment(JLabel.CENTER);
        gameover.setOpaque(false);
        centro.add(gameover, BorderLayout.NORTH);
        
        
        ImagePanel bottoni = new ImagePanel(startWallpaper);
        bottoni.setLayout(new GridLayout(2, 1));
        
        JButton easy = new JButton("START");
        easy.setFont(new Font("SansSerif", Font.BOLD, 18));
        easy.setContentAreaFilled(false);
        easy.setBorderPainted(false);
        easy.setForeground(Color.BLACK);
        easy.addActionListener(this);
        bottoni.add(easy);
    
        JButton quit = new JButton("QUIT");
        quit.setFont(new Font("SansSerif", Font.BOLD, 18));
        quit.setContentAreaFilled(false);
        quit.setBorderPainted(false);
        quit.setForeground(Color.YELLOW);
        quit.setBorder(BorderFactory.createEmptyBorder());
        quit.addActionListener(this);
        bottoni.add(quit);

        centro.add(bottoni, BorderLayout.CENTER);
        
        
        add(centro, BorderLayout.CENTER);
        
        setResizable(false);
        setSize(x, y);
        setVisible(true);
    }
    
    public void actionPerformed (ActionEvent e) {
        String comando = e.getActionCommand();
        
        switch (comando) {
            case "START":  //g = new Giochino(highscore, 1500, 5);
                            setVisible(false);
                            dispose();
                break;
                
            case "ESCI": setVisible(false);
                         dispose();
                         System.exit(0);
                break;
        }
    }
}
    
