import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.Color;

class MyDefaultMetalTheme extends DefaultMetalTheme {
  public ColorUIResource getWindowTitleInactiveBackground() {
    return new ColorUIResource(new Color(188,158,130));
  }

  public ColorUIResource getWindowTitleBackground() {
    return new ColorUIResource(new Color(188,158,130));
  }

  public ColorUIResource getPrimaryControlHighlight() {
    return new ColorUIResource(new Color(188,158,130));
  }

  public ColorUIResource getPrimaryControl() {
    return new ColorUIResource(new Color(188,158,130));
  }

  public ColorUIResource getControlHighlight() {
    return new ColorUIResource(new Color(188,158,130));
  }
  
  public ColorUIResource getControl() {
    return new ColorUIResource(new Color(188,158,130));
  }
}