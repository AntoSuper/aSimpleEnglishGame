import java.awt.*;
import javax.swing.*;

public class RandomQuestionWindow extends JFrame {

    RandomQuestion q;

    public static void main(String[] args) {
        new RandomQuestionWindow();
    }

    public RandomQuestionWindow() {
        super("Random Question");
        this.setSize(600,400);
        this.setLayout(new FlowLayout());

        q = new RandomQuestion();
        add(q);

        this.setVisible(true);
    }

    public int getCheck () {
        return q.getCheck();
    }

    
}