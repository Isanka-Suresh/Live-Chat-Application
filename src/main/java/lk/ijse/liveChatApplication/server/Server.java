package lk.ijse.liveChatApplication.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private final ServerSocket serverSocket;
    private static Server server;

    public Server() throws IOException {
        serverSocket= new ServerSocket(8002);
        System.out.println("Server Online");
    }

    public static Server getServer() throws IOException {
        return server==null ? server= new Server() : server;
    }

    public void closeServer(){
        try {
            if (serverSocket != null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(!serverSocket.isClosed()){
            try {
                Socket socket= serverSocket.accept();
                ClientHandler clientHandler= new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                closeServer();
                throw new RuntimeException(e);
            }
        }
    }
}
