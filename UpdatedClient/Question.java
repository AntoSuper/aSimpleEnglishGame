import java.util.*;
public class Question
{
    private String correct;
    private int check;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String q;

    public Question(String q, String a1, String a2, String a3, String a4) {
        if(a1.charAt(0)=='*') {
            correct=a1.substring(1);
            this.a1=a1.substring(1);
            this.a2=a2;
            this.a3=a3;
            this.a4=a4;
        }

        if(a2.charAt(0)=='*') {
            correct=a2.substring(1);
            this.a2=a2.substring(1);
            this.a1=a1;
            this.a3=a3;
            this.a4=a4;
        }

        if(a3.charAt(0)=='*') {
            correct=a3.substring(1);
            this.a3=a3.substring(1);
            this.a2=a2;
            this.a1=a1;
            this.a4=a4;
        }

        if(a4.charAt(0)=='*') {
            correct=a4.substring(1);
            this.a4=a4.substring(1);
            this.a2=a2;
            this.a3=a3;
            this.a1=a1;
        }

        this.q=q;
        check=1;

    }

    public Question(String q, String a1, String a2, String a3) {
        if(a1.charAt(0)=='*') {
            correct=a1.substring(1);
            this.a1=a1.substring(1);
            this.a2=a2;
            this.a3=a3;
        }

        if(a2.charAt(0)=='*') {
            correct=a2.substring(1);
            this.a2=a2.substring(1);
            this.a1=a1;
            this.a3=a3;
        }

        if(a3.charAt(0)=='*') {
            correct=a3.substring(1);
            this.a3=a3.substring(1);
            this.a2=a2;
            this.a1=a1;
        }

        this.q=q;
        check=2;

    }

    public String getQuestion () {
        return q;
    }

    public String getAnswer1 () {
        return a1;
    }

    public String getAnswer2 () {
        return a2;
    }

    public String getAnswer3 () {
        return a3;
    }

    public String getAnswer4 () {
        if (check == 1) {
            return a4;
        }

        else {
            return null;
        } 
    }

    public String getCorrect () {
        return correct;
    }

    public int getCheck () {
        return check;
    }
}
