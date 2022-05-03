import java.io.*;
import java.net.*;

public class Client {

    private Socket clientSocket;
    private BufferedReader inFromServer;
    private BufferedWriter outToServer;
    private User user;

    public Client (Socket socket, User user) {
        try {
            this.clientSocket = socket;
            this.inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outToServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.user = user;
        }
        catch (java.io.IOException e) {
            closeConnection(clientSocket, inFromServer, outToServer);
        }
    }

    public Client (User x) {
        this.user = x;
    }

    public User getUser () {
        return user;
    }

    public void login () {
        sendMessage("login§"+user.getNickname()+"§"+user.getCharacter());
        String msg[] = receiveMessage().split("§");

        if (msg[0].equals("welcome")) {
            System.out.println("Enjoy the game!");
        }
        
        closeConnection(clientSocket, inFromServer, outToServer);
    }

    public void logout () {
        sendMessage("logout§"+user.getNickname());
        closeConnection(clientSocket, inFromServer, outToServer);
    }

    public String[]  robotsPosition () {
        sendMessage("robotsPosition§"+user.getNickname());

        String msg = receiveMessage();
        String robots[] = msg.split("§");

        closeConnection(clientSocket, inFromServer, outToServer);
        return robots;
    }

    public void sendInfo () {
        String myInfo = "myInfo§"+user.getNickname()+"§"+user.getCharacter()+"§";

        if (user.getInGame()) {
            myInfo = myInfo + "true§";
        }
        else {
            myInfo = myInfo + "false§";
        }
        if (user.getDead()) {
            myInfo = myInfo + "true§";
        }
        else {
            myInfo = myInfo + "false§";
        }

        myInfo = myInfo + user.getLives() + "§" + user.getScore() + "§" + user.getX() + "§" + user.getY() + "§" + user.getDX() + "§" + user.getDY() + "§" + user.getReqDX() + "§" + user.getReqDY();

        sendMessage(myInfo);
        closeConnection(clientSocket, inFromServer, outToServer);
    }

    public User opponentInfo () {
        sendMessage("opponentInfo§"+user.getNickname());
        String msg[] = receiveMessage().split("§");

        User opponent = new User(msg[1], msg[2]);
        if (msg[3].equals("true")) {
            opponent.setInGame(true);
        }
        else {
            opponent.setInGame(false);
        }
        if (msg[4].equals("true")) {
            opponent.setDead(true);
        }
        else {
            opponent.setDead(false);
        }
        opponent.setLives(Integer.parseInt(msg[5]));
        opponent.setScore(Integer.parseInt(msg[6]));
        opponent.setX(Integer.parseInt(msg[7]));
        opponent.setY(Integer.parseInt(msg[8]));
        opponent.setDX(Integer.parseInt(msg[9]));
        opponent.setDY(Integer.parseInt(msg[10]));
        opponent.setReqDX(Integer.parseInt(msg[11]));
        opponent.setReqDY(Integer.parseInt(msg[12]));

        closeConnection(clientSocket, inFromServer, outToServer);
        return opponent;
    }

    public short[] getScreenData () {
        sendMessage("screenData§"+user.getNickname());
        String msg[] = receiveMessage().split("§");

        int DIM = msg.length-1;
        short screenData[] = new short[DIM];
        
        int j = 0;
            for (int i=1;i<msg.length;i++) {
                screenData[j] = Short.parseShort(msg[i]);
                j++;
            }

        closeConnection(clientSocket, inFromServer, outToServer);
        return screenData;
    }

    public void sendScreenData (short[] screenData) {
        String s = "newScreenData§"+user.getNickname()+"§";

        for (int i=0;i<screenData.length;i++) {
            s = s + screenData[i] + "§";
        }

        sendMessage(s);
        closeConnection(clientSocket, inFromServer, outToServer);
    }

    public String receiveMessage () {
        if (clientSocket.isConnected()) {
            try {
                return inFromServer.readLine();
            } 
            catch (java.io.IOException e) {
                e.printStackTrace();
                closeConnection(clientSocket, inFromServer, outToServer);
            }
        }
        return null;
    }

    public void sendMessage (String msg) {
        if (clientSocket.isConnected()) {
            try {
                outToServer.write(msg);
                outToServer.newLine();
                outToServer.flush();

            } 
            catch (java.io.IOException e) {
                e.printStackTrace();
                closeConnection(clientSocket, inFromServer, outToServer);
            }
        }
    }

    public void closeConnection(Socket socket, BufferedReader in, BufferedWriter out) {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
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
}
