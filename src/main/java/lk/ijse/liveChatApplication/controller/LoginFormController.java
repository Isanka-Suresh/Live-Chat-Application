package lk.ijse.liveChatApplication.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.liveChatApplication.client.Client;
import lk.ijse.liveChatApplication.server.Server;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane anchorLogin;

    @FXML
    private TextField txtname;

    @FXML
    private Label lblError;

    @FXML
    private JFXButton btnStart;

    @FXML
    void btnStartOnAction(ActionEvent event) throws IOException {
        if(txtname.getText().equals("")){
            lblError.setText("Enter a name");
        }else {
            new Client(txtname.getText());
            txtname.clear();
        }
    }

    @FXML
    void txtnameOnAction(ActionEvent event) {

    }

}
