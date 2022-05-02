import java.net.*;

public class ServerStart {
    public static void main(String[] args) throws java.io.IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
