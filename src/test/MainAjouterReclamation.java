/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package test;

import java.io.IOException;
import javafx.application.Application;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Hend
 */
public class MainAjouterReclamation extends Application {
    
    @Override
   public void start(Stage primaryStage) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterReclamation.fxml"));
             //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/FXML.fxml"));
             //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AdminHomePage.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ListReclamation.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterReponse.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,800,600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Reclamation");
            primaryStage.show();

            
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
