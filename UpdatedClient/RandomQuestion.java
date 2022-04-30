import javax.swing.*;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;

public class RandomQuestion extends JPanel{

    private Image image;

    public RandomQuestion () {      
          
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);         
    }
}