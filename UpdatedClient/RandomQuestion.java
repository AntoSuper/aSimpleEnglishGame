import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.ArrayList;

public class RandomQuestion extends JFrame implements ActionListener {

    private Question q;
    private int check = 0;
    private JLabel correct;
    
    private JRadioButton a1;
    private JRadioButton a2;
    private JRadioButton a3;
    private JRadioButton a4;

    public RandomQuestion () {
        super("Quesrtion");
        this.setSize(600,400);
        this.setLayout(new BorderLayout());

        ArrayList<Question> aLotOfQuestions = readFile("files/questions.txt");

        q = aLotOfQuestions.get((int)(Math.random() * aLotOfQuestions.size())); 

        JPanel upper = new JPanel();
        upper.setLayout(new FlowLayout());
        add(upper, BorderLayout.NORTH);

        JPanel Answers = new JPanel();
        add(Answers, BorderLayout.CENTER);

        JPanel below = new JPanel();
        below.setLayout(new FlowLayout());
        add(below, BorderLayout.SOUTH);

        JLabel question = new JLabel (q.getQuestion());
        question.setBackground(new Color(105,96,236));
        question.setFont(new Font("Serif", Font.BOLD, 20));
        upper.add(question);

        if (q.getCheck() == 1) {
            Answers.setLayout(new GridLayout(4, 1));

            a1 = new JRadioButton (q.getAnswer1(),new ImageIcon(getClass().getResource("images/3.png")));
            a1.setBackground(new Color(169,164,246));
            a1.setFont(new Font("Serif", Font.PLAIN, 20));
            a1.addActionListener(this);
            Answers.add(a1);

            a2 = new JRadioButton (q.getAnswer2(),new ImageIcon(getClass().getResource("images/3.png")));
            a2.setBackground(new Color(169,164,246));
            a2.setFont(new Font("Serif", Font.PLAIN, 20));
            a2.addActionListener(this);
            Answers.add(a2);

            a3 = new JRadioButton (q.getAnswer3(),new ImageIcon(getClass().getResource("images/3.png")));
            a3.setBackground(new Color(169,164,246));
            a3.setFont(new Font("Serif", Font.PLAIN, 20));
            a3.addActionListener(this);
            Answers.add(a3);

            a4 = new JRadioButton (q.getAnswer4(),new ImageIcon(getClass().getResource("images/3.png")));
            a4.setBackground(new Color(169,164,246));
            a4.setFont(new Font("Serif", Font.PLAIN, 20));
            a4.addActionListener(this);
            Answers.add(a4);

            ButtonGroup group = new ButtonGroup();
            group.add(a1);
            group.add(a2);
            group.add(a3);
            group.add(a4);
        }

        if (q.getCheck() == 2) {
            Answers.setLayout(new GridLayout(3, 1));
            
            a1 = new JRadioButton (q.getAnswer1(),new ImageIcon(getClass().getResource("images/3.png")));
            a1.setBackground(new Color(169,164,246));
            a1.setFont(new Font("Serif", Font.PLAIN, 20));
            a1.addActionListener(this);
            Answers.add(a1);

            a2 = new JRadioButton (q.getAnswer2(),new ImageIcon(getClass().getResource("images/3.png")));
            a2.setBackground(new Color(169,164,246));
            a2.setFont(new Font("Serif", Font.PLAIN, 20));
            a2.addActionListener(this);
            Answers.add(a2);

            a3 = new JRadioButton (q.getAnswer3(),new ImageIcon(getClass().getResource("images/3.png")));
            a3.setBackground(new Color(169,164,246));
            a3.setFont(new Font("Serif", Font.PLAIN, 20));
            a3.addActionListener(this);
            Answers.add(a3);

            ButtonGroup group = new ButtonGroup();
            group.add(a1);
            group.add(a2);
            group.add(a3);
        }

        correct = new JLabel("");
        correct.setBackground(new Color(105,96,236));
        correct.setFont(new Font("Serif", Font.BOLD, 20));
        below.add(correct);
        this.setBackground(new Color(105,96,236));

        this.setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        String command = e.getActionCommand();

        if(q.getCheck()==1) {
            if(a1.isSelected())
            {
                a1.setIcon(new ImageIcon(getClass().getResource("images/2.png")));
            }else
            {
                a1.setIcon(new ImageIcon(getClass().getResource("images/3.png")));
            }
            if(a2.isSelected())
            {
                a2.setIcon(new ImageIcon(getClass().getResource("images/2.png")));
            }else
            {
                a2.setIcon(new ImageIcon(getClass().getResource("images/3.png")));
            }
            if(a3.isSelected())
            {
                a3.setIcon(new ImageIcon(getClass().getResource("images/2.png")));
            }else
            {
                a3.setIcon(new ImageIcon(getClass().getResource("images/3.png")));
            }
            if(a4.isSelected())
            {
                a4.setIcon(new ImageIcon(getClass().getResource("images/2.png")));
            }else
            {
                a4.setIcon(new ImageIcon(getClass().getResource("images/3.png")));
            }
        }

        if(q.getCheck()==2) {
            if(a1.isSelected())
            {
                a1.setIcon(new ImageIcon(getClass().getResource("images/2.png")));
            }else
            {
                a1.setIcon(new ImageIcon(getClass().getResource("images/3.png")));
            }
            if(a2.isSelected())
            {
                a2.setIcon(new ImageIcon(getClass().getResource("images/2.png")));
            }else
            {
                a2.setIcon(new ImageIcon(getClass().getResource("images/3.png")));
            }
            if(a3.isSelected())
            {
                a3.setIcon(new ImageIcon(getClass().getResource("images/2.png")));
            }else
            {
                a3.setIcon(new ImageIcon(getClass().getResource("images/3.png")));
            }
        }

        if (command.equals(q.getCorrect())) {
            correct.setIcon(new ImageIcon(getClass().getResource("images/yes.gif")));
            check = 2;
        }

        else {
            correct.setIcon(new ImageIcon(getClass().getResource("images/no.gif")));
            check = 1;
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

    public int getCheck () {
        return check;
    }
}