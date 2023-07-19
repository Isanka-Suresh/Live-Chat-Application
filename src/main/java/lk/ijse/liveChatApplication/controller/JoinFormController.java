package lk.ijse.liveChatApplication.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.liveChatApplication.server.Server;

import java.io.IOException;

public class JoinFormController {

    @FXML
    private JFXButton btnJoin;

    @FXML
    void btnJoinOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Chat Room 3005");
        stage.setResizable(false);
        stage.show();
        serverOnline();
    }

    private static void serverOnline() throws IOException {
        Server server = Server.getServer();
        Thread thread = new Thread(server);
        thread.start();
    }

}
