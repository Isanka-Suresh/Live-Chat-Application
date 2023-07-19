package lk.ijse.liveChatApplication.controller;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.protocol.x.XMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lk.ijse.liveChatApplication.client.Client;

public class ChatFormController {

    @FXML
    private Label lblName;

    @FXML
    private TextField txtChat;

    @FXML
    private VBox lblChat;

    @FXML
    private JFXButton btnEmoji;

    @FXML
    private JFXButton btnImage;

    @FXML
    private JFXButton btnSend;

    private Client client;

    @FXML
    void btnEmojiOnAction(ActionEvent event) {

    }

    @FXML
    void btnImageOnAction(ActionEvent event) {

    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        String message=client.getUserName()+"-"+txtChat.getText();
        client.sendMsg(message);
        System.out.println(message);
        sendMsgDisplay(txtChat.getText());
        txtChat.clear();
    }

    @FXML
    void txtChatOnAction(ActionEvent event) {

    }

    public void setClient(Client client) {
        this.client=client;
        lblName.setText(client.getUserName()+"'s Chat");
    }

    public void sendMsgDisplay(String message){
        HBox hBox = new HBox();
        hBox.setStyle(
                "-fx-alignment: center-right;" +
                        " -fx-fill-height: true;" +
                        " -fx-min-height: 50px;" +
                        " -fx-pref-width: 575px;" +
                        " -fx-max-width: 575px;" +
                        " -fx-padding: 10px"
        );
        Label label = new Label(message);
        label.setStyle(
                " -fx-alignment: center-right;" +
                        " -fx-background-color:  #3a86ff;" +
                        " -fx-background-radius:15px;" +
                        " -fx-font-size: 18px;" +
                        " -fx-text-fill: #ffffff;" +
                        " -fx-wrap-text: true;" +
                        " -fx-content-display: left;" +
                        " -fx-max-width: 350px;" +
                        " -fx-padding: 10px;"
        );
        hBox.getChildren().add(label);
        lblChat.getChildren().add(hBox);
    }

    public void recieveMsg(String message) {
            HBox hBox = new HBox();
            hBox.setStyle(
                    "-fx-alignment: center-left;" +
                            " -fx-fill-height: true;" +
                            " -fx-min-height: 50px;" +
                            " -fx-pref-width: 575px;" +
                            " -fx-max-width: 575px;" +
                            " -fx-padding: 10px"
            );
            Label label = new Label(message);
            label.setStyle(
                    " -fx-alignment: center-left;" +
                            " -fx-background-color:  #cfdee7;" +
                            " -fx-background-radius:15px;" +
                            " -fx-font-size: 18px;" +
                            " -fx-text-fill: #000000;" +
                            " -fx-wrap-text: true;" +
                            " -fx-content-display: left;" +
                            " -fx-max-width: 350px;" +
                            " -fx-padding: 10px;"
            );
            hBox.getChildren().add(label);
            lblChat.getChildren().add(hBox);
    }
}
