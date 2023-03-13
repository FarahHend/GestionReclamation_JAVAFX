/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * FXML Controller class
 *
 * @author Hend
 */
public class EmailController implements Initializable {

    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;
    @FXML
    private TextField ccField;
    @FXML
    private TextField bccField;
    @FXML
    private TextField subjectField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextArea messageArea;
    @FXML
    private Button sendButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    // object for sending email using the Simple Mail Transfer Protocol (SMTP) protocol.
     @FXML
    public void handleSendButton() {
        // Get the email information from the input fields
        String from = fromField.getText();
        String to = toField.getText();
        String subject = subjectField.getText();
        String message = messageArea.getText();
        String password = passwordField.getText();
        
        // Set up the email session properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); // indicates that authentication is required to send email via SMTP.
        props.put("mail.smtp.starttls.enable", "true"); //enables the use of Transport Layer Security (TLS) when sending email via SMTP.
        props.put("mail.smtp.host", "smtp.gmail.com"); //specifies the hostname of the SMTP server to be used for sending email. In this case, the Gmail SMTP server is being used.
        props.put("mail.smtp.port", "587"); // specifies the port number on the SMTP server to use. Port 587 is the default port for SMTP with TLS enabled.

        
        // Create an Authenticator object to authenticate the email sender
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        
        // Create a new email session
        Session session = Session.getInstance(props, authenticator);
        
        try {
            // Create a new email message
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(from));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            emailMessage.setSubject(subject);
            emailMessage.setText(message);
            
            // Send the email message
            Transport.send(emailMessage);
            
            // Display a success message
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            // Display an error message
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
    
}
