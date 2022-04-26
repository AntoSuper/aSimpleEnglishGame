/**
 * Write a description of class ImagePanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;

public class ImagePanel extends JPanel{

    private Image image;

    public ImagePanel (String x) {      
          image = Toolkit.getDefaultToolkit().createImage(getClass().getResource(x));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);         
    }
}