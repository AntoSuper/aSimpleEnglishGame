import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.Math;
import java.util.*;

public class Quiz extends JFrame
{
    private int max;
    private int min=0;
    private int range;
    
    private ArrayList <String> all=new ArrayList<String>();
    private ArrayList <Question> quiz=new ArrayList<Question>();
    
    public Quiz()
    {
        super("Quiz");
        setSize(600,400);
        setLayout(new BorderLayout());
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader tastiera = new BufferedReader(in);
        
        File x = new File("Questions.txt");
        
        readFile();
        readQuestions();
        
        int random=(int)(Math.random()*range)+min;
        
        setVisible(true);
    }
    public void readFile()
    {
        String ss;
        int i=0;
        try {
            File file = new File("Questions.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            do {
                ss = br.readLine();
                all.add(ss);
            } while (ss!=null);
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readQuestions()
    {
        
    }
}
