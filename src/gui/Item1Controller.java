/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Hend
 */
public class Item1Controller implements Initializable {

    @FXML
    private Label label_titre;
     @FXML
    private Label label_date;
     

      @FXML
    private Button RepBut;
    private reclamation rec;
    private ListReclamation1Controller parentController; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     public void setLabels(reclamation r) {
        this.rec= r;
        this.parentController = parentController; // save a reference to the parent controller
        label_titre.setText(r.getTitre());
        label_date.setText(r.getDate().toString());
        //label_username.setText(r.getUtilisateur().getUsername());
        //System.out.println(r.getUtilisateur()); // check if the Utilisateur object is not null
//System.out.println(r.getUtilisateur().getUsername()); // check if the username is not null or empty

    }
    
    /*@FXML
    private void delete(ActionEvent event) throws SQLException {
         System.out.println("Inside delete method"); // Add this line
        ReclamationService ps = new ReclamationService();
        reclamation rec = new reclamation(); // Create the reclamation object
        //rec.setId_reclamation(2); // Set the ID value
// Set other properties of the reclamation object as needed
System.out.println("Deleting reclamation with ID: " + rec.getId_reclamation());
        ps.supprimer(rec);
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }*/
     
    /* @FXML
private void delete(ActionEvent event) throws SQLException {
    ReclamationService ps = new ReclamationService();
    List<reclamation> reclamations = ps.recuperer(); // Get all reclamations from the database
    if (!reclamations.isEmpty()) { // If there are reclamations to delete
        for (reclamation rec : reclamations) { // Loop through all reclamations
            ps.supprimer(rec); // Delete the reclamation
            System.out.println("Deleting reclamation with ID: " + rec.getId_reclamation());
        }
        System.out.println("All reclamations deleted from database.");
    } else {
        System.out.println("No reclamations found to delete.");
    }
    Node node = (Node) event.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    stage.close();
}*/
     
    @FXML
private void delete(ActionEvent event) throws SQLException {
    ReclamationService ps = new ReclamationService();
    reclamation rec = new reclamation(); // Create the reclamation object
    rec.setId_reclamation(3); // Set the ID value
    // Set other properties of the reclamation object as needed
    
    // Create a confirmation dialog
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText("Are you sure you want to delete this reclamation?");
    alert.setContentText("Click OK to confirm, or Cancel to go back.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        System.out.println("Deleting reclamation with ID: " + rec.getId_reclamation());
        ps.supprimer(rec);
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    } else {
        // User clicked Cancel, do nothing
    }
}
     
     /*@FXML
public void deleteReclamation() {
    reclamation selectedReclamation = tableReclamations.getSelectionModel().getSelectedItem();
    if (selectedReclamation != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to delete this reclamation?");
        alert.setContentText("This action cannot be undone.");

        ButtonType deleteButton = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(deleteButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == deleteButton) {
            deleteButton.setDisable(true); // disable the button to prevent multiple clicks
            int reclamationId = selectedReclamation.getId();
            System.out.println("Deleting reclamation with ID: " + reclamationId);
            boolean isDeleted = reclamationDao.deleteReclamation(reclamationId);
            if (isDeleted) {
                System.out.println("Reclamation deleted from database.");
            } else {
                System.out.println("Failed to delete reclamation from database.");
            }
            deleteButton.setDisable(false); // re-enable the button
        }
    } else {
        System.out.println("No reclamation selected to delete.");
    }
}*/


 @FXML
    void redirect(ActionEvent event) throws IOException {
FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReponse.fxml"));
            Parent root = loader.load();
           // ListReclamationController controller = loader.getController();
            //controller.setData(ch_title.getText() + " " + ch_desc.getText()
            //+ " " + ch_date.getValue().atStartOfDay() + " " + combo.getSelectionModel().getSelectedItem().toString());
            Scene currentScene = RepBut.getScene();
            currentScene.setRoot(root);
    }

    
}
