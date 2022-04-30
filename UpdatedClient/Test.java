import java.awt.*;
import javax.swing.*;

public class Test extends JFrame {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        super("Test");
        this.setSize(600,600);
        this.setLayout(new FlowLayout());

        RandomQuestion q = new RandomQuestion();
        add(q);

        this.setVisible(true);
    }
}