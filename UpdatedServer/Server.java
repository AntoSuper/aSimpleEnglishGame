import java.net.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Server implements ActionListener {

    private ServerSocket serverSocket;
    private BufferedReader inFromClient;
    private BufferedWriter outToClient;

    private ArrayList<Robot> tantiRobot = new ArrayList<Robot>();
    private ArrayList<User> tantiUser = new ArrayList<User>();

    public final static int BLOCK_SIZE = 24;
    public final static int N_BLOCKS = 15;

    private int N_ROBOTS = 6;

    private int [] robotSpeed;
    private int [] dx, dy;

    private int currentSpeed = 3;
    private final int validSpeed[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;

    private short [] screenData;
    private final short levelData[] = { 
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

    private Timer t;
    private boolean inGame;

    public Server (ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        setVariables();

        try {
            while (!serverSocket.isClosed()) {
                if (allInGame() && inGame!=true) {
                    t = new Timer(40, this);
                    t.setActionCommand("moveRobots"); 
                    t.start();

                    inGame = true;
                }

                Socket connectionSocket = serverSocket.accept(); 
                System.out.println("Client connected! IP: " + connectionSocket.getRemoteSocketAddress());

                this.inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                this.outToClient = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));

                while (!connectionSocket.isClosed()) {
                    String msg[] = receiveMessage().split("§");
                    String comando = msg[0];

                    switch (comando) {
                        case "login":   User x = new User(msg[1], msg[2]);
                                        tantiUser.add(x);
                                        sendMessage("welcome§"+x.getNickname());
                                        closeConnection(connectionSocket, inFromClient, outToClient);
                            break;

                        case "logout":  User y = new User(msg[1]);
                                        for (int i=0;i<tantiUser.size();i++) {
                                            if (y.equals(tantiUser.get(i))) {
                                                tantiUser.remove(i);
                                                sendMessage("byeBye§"+y.getNickname());
                                                closeConnection(connectionSocket, inFromClient, outToClient);
                                            }
                                        }
                            break;

                        case "robotsPosition": String info = "info§";
                                                for (int i=0;i<tantiRobot.size();i++) {
                                                    info = info + tantiRobot.get(i).getX()+"&"+tantiRobot.get(i).getY() + "§";
                                                }
                                                sendMessage(info);
                                                closeConnection(connectionSocket, inFromClient, outToClient);
                            break;

                        case "myInfo": for (int i=0;i<tantiUser.size();i++) {
                                            if (msg[1].equals(tantiUser.get(i).getNickname())) {
                                                if (msg[3].equals("true")) {
                                                    tantiUser.get(i).setInGame(true);
                                                }
                                                else {
                                                    tantiUser.get(i).setInGame(false);
                                                }
                                                if (msg[4].equals("true")) {
                                                    tantiUser.get(i).setDead(true);
                                                }
                                                else {
                                                    tantiUser.get(i).setDead(false);
                                                }
                                                tantiUser.get(i).setLives(Integer.parseInt(msg[5]));
                                                tantiUser.get(i).setScore(Integer.parseInt(msg[6]));
                                                tantiUser.get(i).setX(Integer.parseInt(msg[7]));
                                                tantiUser.get(i).setY(Integer.parseInt(msg[8]));
                                                tantiUser.get(i).setDX(Integer.parseInt(msg[9]));
                                                tantiUser.get(i).setDY(Integer.parseInt(msg[10]));
                                                tantiUser.get(i).setReqDX(Integer.parseInt(msg[11]));
                                                tantiUser.get(i).setReqDY(Integer.parseInt(msg[12]));

                                                sendMessage("msgReceived");
                                                closeConnection(connectionSocket, inFromClient, outToClient);
                                                break;
                                            }
                                        }
                            break;

                        case "opponentInfo": for (int i=0;i<tantiUser.size();i++) {
                                                if (!msg[1].equals(tantiUser.get(i).getNickname())) {
                                                    String opponentInfo = "info§" + tantiUser.get(i).getNickname() + "§" + tantiUser.get(i).getCharacter() + "§";
                                                    if (tantiUser.get(i).getInGame()) {
                                                        opponentInfo = opponentInfo + "true§";
                                                    }
                                                    else {
                                                        opponentInfo = opponentInfo + "false§";
                                                    }
                                                    if (tantiUser.get(i).getDead()) {
                                                        opponentInfo = opponentInfo + "true§";
                                                    }
                                                    else {
                                                        opponentInfo = opponentInfo + "false§";
                                                    }
                                                    opponentInfo = opponentInfo + tantiUser.get(i).getLives() + "§" + tantiUser.get(i).getScore() + "§" + tantiUser.get(i).getX() + "§" + tantiUser.get(i).getY() + "§" +  tantiUser.get(i).getDX() + "§" + tantiUser.get(i).getDY() + "§" + tantiUser.get(i).getReqDX() + "§" + tantiUser.get(i).getReqDY();   

                                                    sendMessage(opponentInfo);
                                                    closeConnection(connectionSocket, inFromClient, outToClient);
                                                    break;
                                                }
                                        }
                            break;

                        case "screenData": if (levelData!=null) {
                                                String data = "data§";
                                                for (int i=0;i<N_BLOCKS * N_BLOCKS; i++) {
                                                    data = data + screenData[i] + "§";
                                                }
                                                sendMessage(data);
                                                closeConnection(connectionSocket, inFromClient, outToClient);
                                                break;
                                            }
                            break;

                        default:
                            break;
                    }
                }
            }
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private boolean allInGame () {
        if (tantiUser.size()<2) {
            return false;
        }
        for (int i=0;i<tantiUser.size();i++) {
            if (!tantiUser.get(i).getInGame()) {
                return false;
            }
        }
        return true;
    }

    private void setVariables() {
        for (int i=0;i<N_ROBOTS;i++) {
            tantiRobot.add(new Robot());
        }

        screenData = new short[N_BLOCKS * N_BLOCKS];
        dx = new int [4];
        dy = new int [4];

        initLevel();
    }

    private void initLevel() {
        for (int i=0;i<N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }
    }

    private void moveRobots() {
        int pos;
        int count;

        for (int i = 0; i < tantiRobot.size(); i++) {
            if (tantiRobot.get(i).getX() % BLOCK_SIZE == 0 && tantiRobot.get(i).getY() % BLOCK_SIZE == 0) {
                pos = tantiRobot.get(i).getX() / BLOCK_SIZE + N_BLOCKS * (int)(tantiRobot.get(i).getY() / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && tantiRobot.get(i).getDX() != 1) {
                    tantiRobot.get(i).setDX(-1);
                    tantiRobot.get(i).setDY(0);
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && tantiRobot.get(i).getDY()  != 1) {
                    tantiRobot.get(i).setDX(0);
                    tantiRobot.get(i).setDY(-1);
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && tantiRobot.get(i).getDX()  != -1) {
                    tantiRobot.get(i).setDX(1);
                    tantiRobot.get(i).setDY(0);
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && tantiRobot.get(i).getDY()  != -1) {
                    tantiRobot.get(i).setDX(0);
                    tantiRobot.get(i).setDY(1);
                    count++;
                }

                if (count == 0) {
                    if ((screenData[pos] & 15) == 15) {
                        tantiRobot.get(i).setDX(0);
                        tantiRobot.get(i).setDY(0);
                    } 
                    else {
                        tantiRobot.get(i).setDX(-tantiRobot.get(i).getDX());
                        tantiRobot.get(i).setDY(-tantiRobot.get(i).getDY());
                    }

                } 
                
                else {
                    count = (int) (Math.random() * count); 

                    if (count > 3) {
                        count = 3;
                    }

                    tantiRobot.get(i).setDX(dx[count]);
                    tantiRobot.get(i).setDY(dy[count]);
                }
            }

            tantiRobot.get(i).setX(tantiRobot.get(i).getX() + (tantiRobot.get(i).getDX() * robotSpeed[i]));
            tantiRobot.get(i).setY(tantiRobot.get(i).getY() + (tantiRobot.get(i).getDY() * robotSpeed[i]));
            
            //drawRobot(g2d, robot_x[i] + 1, robot_y[i] + 1);

            /*if (player.getX() > (robot_x[i] - 12) && player.getX() < (robot_x[i] + 12)
                && player.getY() > (robot_y[i] - 12) && player.getY()< (robot_y[i] + 12)
                && player.getInGame()) {
                    player.morto=true;
            }*/
        }
    }

    private void continueLevel () {
        int dx = 1;
        int random;

        for (int i=0;i<tantiRobot.size();i++) {
            tantiRobot.get(i).setX(4 * BLOCK_SIZE);
            tantiRobot.get(i).setY(4 * BLOCK_SIZE);
            tantiRobot.get(i).setDX(dx);
            tantiRobot.get(i).setDY(0);

            dx = -dx;
            random = (int) Math.random() * (currentSpeed +1);

            robotSpeed [i] = validSpeed[random];
        }

        //broadcast message quando tutti morti o tutti secondo livello?????
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "moveRobots": moveRobots();
                break;
        }
    }

    public void sendMessage (String msg) {
        try {
                outToClient.write(msg);
                outToClient.newLine();
                outToClient.flush();
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage () {
        try {
            String msg = inFromClient.readLine();
            return msg;
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection(Socket socket, BufferedReader in, BufferedWriter out) {
        try {
            if (inFromClient != null) {
                in.close();
            }
            if (outToClient != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } 
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void closeServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
