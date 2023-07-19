package lk.ijse.liveChatApplication.client;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.liveChatApplication.controller.ChatFormController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class Client implements Runnable, Serializable {
        private Socket socket;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        private String userName;
        private ChatFormController chatFormController;

    public Client(String userName) throws IOException {
            this.socket = new Socket("localhost",8002);
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.userName = userName;
        try {
            launchUI();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void recieveMsg(){
        new Thread(() -> {
            while(!socket.isClosed()){
                try{
                    String message = dataInputStream.readUTF();
                    Platform.runLater(() -> {
                        chatFormController.recieveMsg(message);
                    });
                }catch (IOException e){
                    try{
                        closeSocket();
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public void sendMsg(String message){
        try{
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        }catch (IOException e){
            try{
                closeSocket();
            }catch (IOException ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private void closeSocket() throws IOException{
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
    }

    public String getUserName(){
        return userName;
    }
    public void launchUI() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/chat_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Chat Room 3005");
        stage.setResizable(false);
        stage.show();

        this.chatFormController = fxmlLoader.getController();
        chatFormController.setClient(this);
    }

    @Override
    public void run(){

    }

    @Override
    protected void finalize() throws IOException {
        Thread.interrupted();
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
    }
}
