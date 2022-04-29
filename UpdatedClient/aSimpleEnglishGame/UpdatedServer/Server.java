import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private BufferedReader inFromClient;
    private BufferedWriter outToClient;

    private ArrayList<Robot> tantiRobot = new ArrayList<Robot>();
    private ArrayList<User> tantiUser = new ArrayList<User>();

    public final static int BLOCK_SIZE = 24;
    public final static int N_BLOCKS = 15;
    private int N_ROBOTS = 6;

    dx = new int [4];
    dy = new int [4];

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

    public Server (ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
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

                        case "robotsPosition":
                            break;

                        case "myInfo":
                            break;

                        case "opponentInfo":
                            break;

                        case "screenData":
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

    private void initLevel() {
        for (int i=0;i<N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }
    }

    private void setVariables() {
        for (int i=0;i<N_ROBOTS;i++) {
            tantiRobot.add(new Robot());
        }

        screenData = new short[N_BLOCKS * N_BLOCKS];
        initLevel();
    }

    private void moveRobots() {
        int pos;
        int count;

        for (int i = 0; i < tantiRobot.size(); i++) {
            if (tantiRobot.get(i).getX() % BLOCK_SIZE == 0 && tantiRobot.get(i).getY() % BLOCK_SIZE == 0) {
                pos = tantiRobot.get(i).getX() / BLOCK_SIZE + N_BLOCKS * (int) (tantiRobot.get(i).getY() / BLOCK_SIZE);

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

                    robot_dx[i] = dx[count];
                    robot_dy[i] = dy[count];
                }
            }

            robot_x[i] = robot_x[i] + (robot_dx[i] * robotSpeed[i]);
            robot_y[i] = robot_y[i] + (robot_dy[i] * robotSpeed[i]);
            drawRobot(g2d, robot_x[i] + 1, robot_y[i] + 1);

            if (player.getX() > (robot_x[i] - 12) && player.getX() < (robot_x[i] + 12)
                && player.getY() > (robot_y[i] - 12) && player.getY()< (robot_y[i] + 12)
                && player.getInGame()) {
                    player.morto=true;
            }
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
