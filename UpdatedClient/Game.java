import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.net.*;

public class Game extends JPanel implements ActionListener {
    
    private Dimension d;
    private final Font font = new Font("Arial", Font.BOLD, 14);

    private Graphics2D g2d;

    public final static int BLOCK_SIZE = 24; 
    public final static int N_BLOCKS = 15; 
    public static int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE; 
    private final int PLAYER_SPEED = 6; 
    
    private ArrayList<Robot> tantiRobot = new ArrayList<Robot>();

    public User player;
    private boolean dying = false;
    private RandomQuestion q;
    private User opponent;

    public String IP;
    public int port;
    public Client client;
    private Socket socket;
    private boolean closing;

    private Image heart, robot;
    private Image upTommy, downTommy, leftTommy, rightTommy;
    private Image upMargie, downMargie, leftMargie, rightMargie;

    private int currentSpeed = 3;
    private final int maxSpeed = 6;
    private short [] screenData;

    private Timer timerGame;
    private Timer timerQuestion;

    private short [] levelData = new short[N_BLOCKS * N_BLOCKS];
    private final short levelData1[] = { 
        19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
        17, 16, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        25, 24, 24, 24, 28, 0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
        0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
        19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
        17, 16, 16, 16, 24, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
        17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 18, 18, 18, 18, 20,
        17, 24, 24, 28, 0, 25, 24, 24, 16, 16, 16, 16, 16, 16, 20,
        21, 0,  0,  0,  0,  0,  0,   0, 17, 16, 16, 16, 16, 16, 20,
        17, 18, 18, 22, 0, 19, 18, 18, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        25, 24, 24, 24, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
    };

    private final short levelData2[] = { 
        19,	18,	26,	18,	18,	18,	18,	26,	26,	18,	18,	18,	18,	18,	22,
        17,	20,	0,	17,	16,	16,	20,	0,	0,	17,	24,	16,	16,	16,	20,
        17,	20,	0,	25,	24,	16,	20,	0,	0,	21,	0,	17,	16,	16,	20,
        17,	20,	0,	0,	0,	17,	16,	18,	18,	20,	0,	17,	16,	16,	20,
        17,	20,	0,	0,	0,	17,	16,	16,	16,	28,	0,	17,	16,	16,	20,
        17,	16,	18,	18,	18,	16,	16,	16,	20,	0,	0,	17,	16,	16,	20,
        17,	16,	16,	16,	16,	16,	16,	16,	20,	0,	0,	17,	16,	16,	20,
        17,	16,	16,	16,	16,	16,	16,	24,	16,	22,	0,	17,	16,	16,	20,
        17,	16,	16,	16,	16,	16,	20,	0,	17,	20,	0,	17,	24,	24,	20,
        17,	16,	16,	16,	24,	24,	28,	0,	17,	20,	0,	21,	0,	0,	21,
        17,	24,	16,	20,	0,	0,	0,	0,	17,	20,	0,	21,	0,	0,	21,
        21,	0,	17,	20,	0,	0,	0,	0,	17,	16,	18,	20,	0,	0,	21,
        21,	0,	17,	16,	18,	18,	18,	18,	16,	16,	16,	16,	18,	18,	20,
        21,	0,	17,	16,	16,	16,	16,	16,	16,	16,	16,	16,	16,	16,	20,
        25,	26,	24,	24,	24,	24,	24,	24,	24,	24,	24,	24,	24,	24,	28
    };

    private final short levelData3[] = { 
        19,	18,	18,	18,	18,	26,	26,	26,	18,	18,	18,	18,	18,	18,	22,
        17,	16,	24,	16,	20,	0,	0,	0,	17,	16,	16,	24,	16,	16,	28,
        17,	20,	0,	17,	20,	0,	0,	0,	17,	16,	20,	0,	17,	20,	0,
        17,	20,	0,	17,	16,	22,	0,	19,	16,	16,	20,	0,	17,	20,	0,
        17,	20,	0,	17,	16,	16,	18,	16,	16,	16,	28,	0,	25,	20,	0,
        17,	20,	0,	17,	16,	24,	24,	24,	16,	20,	0,	0,	0,	21,	0,
        17,	20,	0,	17,	20,	0,	0,	0,	17,	20,	0,	0,	0,	21,	0,
        17,	20,	0,	17,	20,	0,	0,	0,	17,	16,	22,	0,	19,	20,	0,
        17,	20,	0,	17,	20,	0,	0,	0,	17,	24,	20,	0,	17,	16,	22,
        17,	16,	18,	16,	20,	0,	0,	0,	21,	0,	21,	0,	17,	16,	20,
        17,	24,	24,	24,	16,	18,	18,	18,	20,	0,	21,	0,	17,	16,	20,
        21,	0,	0,	0,	17,	16,	16,	16,	20,	0,	17,	18,	24,	24,	20,
        21,	0,	0,	0,	17,	16,	16,	16,	16,	18,	16,	20,	0,	0,	21,
        17,	18,	18,	18,	16,	16,	16,	16,	16,	16,	16,	20,	0,	0,	21,
        25,	24,	24,	24,	24,	24,	24,	24,	24,	24,	24,	28,	0,	0,	29
    };

    public void openConnection (String IP, int port, User user) {
        try {
            socket = new Socket(IP, port);
            client = new Client(socket, user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game (String nickname, String character, String IP, int port) {
        this.IP = IP;
        this.port = port;
        this.player = new User(nickname, character);

        openConnection(IP, port, player);
        client.login();

        openConnection(IP, port, player);
        client.sendInfo();

        this.opponent = new User("X", "X");

        loadImages();

        setVariables();

        addKeyListener(new TAdapter());
        setFocusable(true);

        initLevel();
    }

    private void loadImages () {
        downTommy = new ImageIcon("images/guy.png").getImage();
        upTommy = new ImageIcon("images/guy.png").getImage();
        leftTommy = new ImageIcon("images/guy.png").getImage();
        rightTommy = new ImageIcon("images/guy.png").getImage();
        downMargie = new ImageIcon("images/girl.png").getImage();
        upMargie = new ImageIcon("images/girl.png").getImage();
        leftMargie = new ImageIcon("images/girl.png").getImage();
        rightMargie = new ImageIcon("images/girl.png").getImage();
        robot = new ImageIcon("images/robot.png").getImage();
        heart = new ImageIcon("images/heart.png").getImage();
    }

    private void showIntroScreen(Graphics2D g2d) {
        String start = "Press space to be ready!";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (SCREEN_SIZE)/4, 150);
    }

    private void showQuestionScreen(Graphics2D g2d) {
        String start = "Please, answear the question!";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (SCREEN_SIZE)/4, 150);
    }

    private void showWaitScreen(Graphics2D g2d) {
        String start = "Please, wait for your opponent...";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (SCREEN_SIZE)/4, 150);
    }

    private void drawScore(Graphics2D g) {
        g.setFont(font);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + player.getScore();
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (int i = 0; i < player.getLives(); i++) {
            g.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    private void setVariables () {
        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension (400, 400);

        timerGame = new Timer(60, this);
        timerGame.setActionCommand("timer"); 
        timerGame.restart();
    }

    private void initLevel() {
        openConnection(IP, port, player);
        screenData = client.getScreenData();

        if (client.getRandomData() < 33) {
            levelData = levelData1;
        }

        if (client.getRandomData() >= 33 && client.getRandomData() < 66) {
            levelData = levelData2;
        }

        if (client.getRandomData() >= 66) {
            levelData = levelData3;
        }

        for (int i=0;i<6;i++) {
            tantiRobot.add(new Robot());
        }
    }

    private boolean allDead() {
        if (player.getDead() && opponent.getDead()) {
            return true;
        }
        else {
            return false;
        }
    }

    private void playGame(Graphics2D g2d) {
        if (!allDead()) {
            moveRobots(g2d);
        }
        if (dying) {
            death();
        }

        movePlayer(player);
        movePlayer(opponent);

        checkCollision(player);
        checkCollision(opponent);

        drawPlayer(g2d, player);
        drawPlayer(g2d, opponent);

        checkMaze();
    }

    public void checkCollision (User x) {
        if (x.getX() % BLOCK_SIZE == 0 && x.getY() % BLOCK_SIZE == 0) {
            int pos = x.getX() / BLOCK_SIZE + N_BLOCKS * (int) (x.getY() / BLOCK_SIZE); 
            short ch = screenData[pos];
            
            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                x.setScore(x.getScore()+1);
            }
        }
    }

    public void movePlayer (User x) {
        int pos;
        short ch;

        if (x.getX() % BLOCK_SIZE == 0 && x.getY() % BLOCK_SIZE == 0) {
            pos = x.getX() / BLOCK_SIZE + N_BLOCKS * (int) (x.getY() / BLOCK_SIZE); 
            ch = screenData[pos];

            if (x.getReqDX() !=0 || x.getReqDY() != 0) {
                if (!((x.getReqDX() == -1 && x.getReqDY() == 0 && (ch & 1) != 0) 
                || (x.getReqDX() == 1 && x.getReqDY() == 0 && (ch & 4) != 0) 
                || (x.getReqDX() == 0 && x.getReqDY() == -1 && (ch & 2) != 0) 
                || (x.getReqDX() == 0 && x.getReqDY() == 1 && (ch & 8) != 0))) {
                    x.setDX(x.getReqDX());
                    x.setDY(x.getReqDY());
                }
            }

            if ((x.getDX() == -1 && x.getDY() == 0 && (ch & 1) != 0) 
                || (x.getDX() == 1 && x.getDY() == 0 && (ch & 4) != 0) 
                || (x.getDX() == 0 && x.getDY()  == -1 && (ch & 2) != 0) 
                || (x.getDX() == 0 && x.getDY() == 1 && (ch & 8) != 0)) {
                    x.setDX(0);
                    x.setDY(0);
                }
        }

        x.setX(x.getX() + PLAYER_SPEED * x.getDX());
        x.setY(x.getY() + PLAYER_SPEED * x.getDY());
    }

    public void drawPlayer(Graphics2D g2d, User x) {
        if (x.getCharacter().equals("Tommy")) {
            if (x.getReqDX() == -1) {
                g2d.drawImage(leftTommy, x.getX() + 1, x.getY()+ 1, this);
            } else if (x.getReqDX() == 1) {
                g2d.drawImage(rightTommy, x.getX() + 1, x.getY() + 1, this);
            } else if (x.getReqDY() == -1) {
                g2d.drawImage(upTommy, x.getX()+ 1, x.getY() + 1, this);
            } else {
                g2d.drawImage(downTommy, x.getX() + 1, x.getY()+ 1, this);
            }
        }
        if (x.getCharacter().equals("Margie")) {
            if (x.getReqDX() == -1) {
                g2d.drawImage(leftMargie, x.getX() + 1, x.getY()+ 1, this);
            } else if (x.getReqDX() == 1) {
                g2d.drawImage(rightMargie, x.getX() + 1, x.getY() + 1, this);
            } else if (x.getReqDY() == -1) {
                g2d.drawImage(upMargie, x.getX()+ 1, x.getY() + 1, this);
            } else {
                g2d.drawImage(downMargie, x.getX() + 1, x.getY()+ 1, this);
            }
        }
    }

    private void moveRobots(Graphics2D g2d) {
        openConnection(IP, port, player);
        String [] robots = client.robotsPosition();

        int j = 0;
        for (int i=1;i<robots.length;i++) {
            String pos [] = robots[i].split("&");
            tantiRobot.set(j, new Robot(Integer.valueOf(pos[0]), Integer.valueOf(pos[1])));
            j++;
        }

        for (int i=0;i<tantiRobot.size();i++) {
            drawRobot(g2d, tantiRobot.get(i).getX() + 1, tantiRobot.get(i).getY() + 1);

            if (player.getX() > (tantiRobot.get(i).getX() - 12) && 
                player.getX() < (tantiRobot.get(i).getX() + 12) && 
                player.getY() > (tantiRobot.get(i).getY() - 12) && 
                player.getY() < (tantiRobot.get(i).getY() + 12) && 
                player.getInGame()) {
                dying = true;
            }
        }
    }

    public void drawRobot(Graphics2D g2d, int x, int y) {
        g2d.drawImage(robot, x, y, this);
    } 

    public void checkMaze () {
        int i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {
            if ((screenData[i] & 48) != 0) {
                finished = false;
            }
            i++;
        }

        if (finished) {
            player.setScore(player.getScore()+50);  
            if (currentSpeed<maxSpeed) {
                currentSpeed++;
            }
            initLevel();
        }

        openConnection(IP, port, player);
        client.sendScreenData(screenData);
    }

    private void death() {
        q = new RandomQuestion();
        player.setInGame(false);
        player.setQuestion(true);
        openConnection(IP, port, player);
        client.sendInfo();

        showQuestionScreen(g2d);
        timerGame.stop();

        timerQuestion = new Timer(1000, this);
        timerQuestion.setActionCommand("timerQuestion"); 
        timerQuestion.start();

        player.setX(7 * BLOCK_SIZE);  
        player.setY(11 * BLOCK_SIZE);
        player.setDX(0); 
        player.setDY(0);
        player.setReqDX(0);  
        player.setReqDY(0); 
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(new Color(255,0,255));
                g2d.setStroke(new BasicStroke(5));
                
                if ((levelData[i] == 0)) { 
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                 }

                if ((screenData[i] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) { 
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) { 
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) { 
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
               }
                i++;
            }
        }
    }

    public boolean allInGame () {
        if (player.getInGame() && opponent.getInGame()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);

        openConnection(IP, port, player);
        client.sendInfo();

        if (player.getInGame()) {
            openConnection(IP, port, player);
            opponent = client.opponentInfo();

            openConnection(IP, port, player);
            screenData = client.getScreenData();
        }

        if (opponent.getQuestion() ) {
            showWaitScreen(g2d);
        }

        if (player.getDead()) {
            timerGame.stop();

            Lose x = new Lose();

            closing = true;
        }
        else if (opponent.getDead()) {
            timerGame.stop();

            Win x = new Win();

            closing = true;
        }

        if (allInGame()) {
            playGame(g2d);
        }
        else {
            if ((player.getInGame() && !opponent.getInGame()) || opponent.getQuestion()) {
                showWaitScreen(g2d);
            }
            else {
                showIntroScreen(g2d);
            }
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    public boolean getClosing () {
        return closing;
    }

    class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int tasto = e.getKeyCode();

            if (player.getInGame()) {
                switch (tasto) {
                    case 37: 
                            player.setReqDX(-1);
                            player.setReqDY(0);
                        break;

                    case 39: 
                            player.setReqDX(1);
                            player.setReqDY(0);
                        break;

                    case 38: 
                            player.setReqDX(0);
                            player.setReqDY(-1);
                        break;

                    case 40: 
                            player.setReqDX(0);    
                            player.setReqDY(1);
                        break;   
                        
                    case 27:
                            if (timerGame.isRunning()) {
                                    player.setInGame(false);
                            }
                        break;    
                }
            } 
            else {
                if (tasto == 32) {
                    player.setInGame(true);
                    if (allInGame()) {
                        initLevel();
                    }
                }
            }
        }
    }
    
    
    @Override
    public void actionPerformed (ActionEvent e) {
        String command = e.getActionCommand();

        switch(command) {
            case "timer": repaint();
                break;

            case "timerQuestion": if (q.getCheck() == 2) {
                                    player.setInGame(true);
                                    player.setQuestion(false);
                                    dying = false;
                                        
                                    timerQuestion.stop();
                                    q.setVisible(false);
                                    q.dispose();
                                    timerGame.restart();
                                }
                        
                                if (q.getCheck() == 1) {
                                    player.setLives(player.getLives()-1);
                                    dying = false;
                                    player.setInGame(true);
                                    player.setQuestion(false);

                                    if (player.getLives() == 0) {
                                        player.setInGame(false);
                                        player.setDead(true);
                                    }
                        
                                    timerQuestion.stop();
                                    q.setVisible(false);
                                    q.dispose();
                                    timerGame.restart();
                                }
                    break;
        }
    }
}