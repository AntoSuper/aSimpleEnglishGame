import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.ArrayList;

public class RandomQuestion extends JPanel implements ActionListener {

    private Question q;
    private JLabel correct;

    public RandomQuestion () {
        this.setLayout(new BorderLayout());

        ArrayList<Question> aLotOfQuestions = readFile("questions.txt");

        q = aLotOfQuestions.get((int)(Math.random() * aLotOfQuestions.size())); 

        JPanel upper = new JPanel();
        upper.setLayout(new FlowLayout());
        add(upper, BorderLayout.NORTH);

        JPanel answears = new JPanel();
        add(answears, BorderLayout.CENTER);

        JPanel below = new JPanel();
        below.setLayout(new FlowLayout());
        add(below, BorderLayout.SOUTH);

        JLabel question = new JLabel (q.getQuestion());
        question.setBackground(new Color(105,96,236));
        question.setFont(new Font("Serif", Font.BOLD, 20));
        upper.add(question);

        if (q.getCheck() == 1) {
            answears.setLayout(new GridLayout(4, 1));

            JRadioButton a1 = new JRadioButton (q.getAnswear1());
            a1.setBackground(new Color(169,164,246));
            a1.setFont(new Font("Serif", Font.PLAIN, 20));
            a1.addActionListener(this);
            answears.add(a1);

            JRadioButton a2 = new JRadioButton (q.getAnswear2());
            a2.setBackground(new Color(169,164,246));
            a2.setFont(new Font("Serif", Font.PLAIN, 20));
            a2.addActionListener(this);
            answears.add(a2);

            JRadioButton a3 = new JRadioButton (q.getAnswear3());
            a3.setBackground(new Color(169,164,246));
            a3.setFont(new Font("Serif", Font.PLAIN, 20));
            a3.addActionListener(this);
            answears.add(a3);

            JRadioButton a4 = new JRadioButton (q.getAnswear4());
            a4.setBackground(new Color(169,164,246));
            a4.setFont(new Font("Serif", Font.PLAIN, 20));
            a4.addActionListener(this);
            answears.add(a4);

            ButtonGroup group = new ButtonGroup();
            group.add(a1);
            group.add(a2);
            group.add(a3);
            group.add(a4);
        }

        if (q.getCheck() == 2) {
            answears.setLayout(new GridLayout(3, 1));
            
            JRadioButton a1 = new JRadioButton (q.getAnswear1());
            a1.setBackground(new Color(169,164,246));
            a1.setFont(new Font("Serif", Font.PLAIN, 20));
            a1.addActionListener(this);
            answears.add(a1);

            JRadioButton a2 = new JRadioButton (q.getAnswear2());
            a2.setBackground(new Color(169,164,246));
            a2.setFont(new Font("Serif", Font.PLAIN, 20));
            a2.addActionListener(this);
            answears.add(a2);

            JRadioButton a3 = new JRadioButton (q.getAnswear3());
            a3.setBackground(new Color(169,164,246));
            a3.setFont(new Font("Serif", Font.PLAIN, 20));
            a3.addActionListener(this);
            answears.add(a3);

            ButtonGroup group = new ButtonGroup();
            group.add(a1);
            group.add(a2);
            group.add(a3);
        }

        correct = new JLabel("");
        correct.setBackground(new Color(105,96,236));
        correct.setFont(new Font("Serif", Font.BOLD, 20));
        below.add(correct);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals(q.getCorrect())) {
            correct.setText("CORRECT");
        }
        else {
            correct.setText("WRONG");
        }
    }

    public static ArrayList<Question> readFile (String path) {
        ArrayList<Question> s = new ArrayList<Question>();
        String x;
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            Question q;

            do {
                x = br.readLine();
                if (x!=null) {
                   String qs[] = x.split(";");
                   if (qs.length == 5) {
                        q = new Question(qs[0], qs[1], qs[2], qs[3], qs[4]);
                        s.add(q);
                   }
                   if (qs.length == 4) {
                        q = new Question(qs[0], qs[1], qs[2], qs[3]);
                        s.add(q);
                   }
                }
            } while (x!=null);
            br.close();
            return s;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}