/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.notification;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.NotificationService;
import services.NotificationnnService;

/**
 * FXML Controller class
 *
 * @author Hend
 */
public class NotificationController implements Initializable {

    @FXML
    private Label notificationLabel;
    @FXML
    private Button notificationButton;

    private NotificationnnService notificationService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        notificationService = new NotificationnnService();
        notificationService.setOnSucceeded(event -> {
            String message = (String) event.getSource().getValue();
            notificationLabel.setText(message);
        });
    }    

    @FXML
    private void handleNotificationButton(ActionEvent event) throws SQLException {
        NotificationService notificationService = new NotificationService();
        //notificationService.restart();
        //System.out.println("Notification work!");
        int userId = 2; // replace with the actual user ID
    String message = "Hello, world!";
    notification notif = new notification(userId, message, null);
    notificationService.ajouter(notif);
    System.out.println("Notification saved in the database!");
    }
    
}
