package lk.ijse.liveChatApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.liveChatApplication.server.Server;

import java.io.IOException;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/view/join_form.fxml"));
        stage.setTitle("Login");
        stage.centerOnScreen();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
