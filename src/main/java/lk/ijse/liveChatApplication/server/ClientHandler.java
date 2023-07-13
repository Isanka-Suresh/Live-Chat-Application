package lk.ijse.liveChatApplication.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers= new ArrayList<>();
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private String userName;

    public ClientHandler(Socket socket){
        try{
            this.socket=socket;
            this.dataOutputStream= new DataOutputStream(socket.getOutputStream());
            this.dataInputStream= new DataInputStream(socket.getInputStream());
            this.userName= dataInputStream.readUTF();
            clientHandlers.add(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(!this.socket.isClosed()){
            try{
                String message = this.dataInputStream.readUTF();
                braodcasting(message);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void braodcasting(String message) {
        for (ClientHandler clientHandler:clientHandlers) {
            try{
                if(clientHandler.userName.equals(userName)){
                    continue;
                }
                DataOutputStream dataOutputStream = new DataOutputStream(clientHandler.socket.getOutputStream());
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
            }catch (IOException e){
                try {
                    closeClient(clientHandler);
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private void closeClient(ClientHandler clientHandler) throws IOException {
        clientHandler.dataOutputStream.close();
        clientHandler.dataInputStream.close();
        clientHandler.socket.close();
        clientHandlers.remove(this);
    }

}
