/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.reclamation;
import entities.reponse_reclamation;
import gui.ItemController;
import gui.ListReponseController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author Hend
 */
public class Reponse_ReclamationService implements IReponseService<reponse_reclamation>{
    
   Connection cnx;

    public Reponse_ReclamationService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(reponse_reclamation t) throws SQLException {
       String req = "INSERT INTO reponse_reclamation(id_reclamation,reponse,date) VALUES(?,?,?)";
        PreparedStatement rp = cnx.prepareStatement(req);
        //rp.setInt(1, t.getId_reclamation());
        rp.setInt(1, t.getReclamation().getId_reclamation());
        rp.setString(2, t.getReponse());
        rp.setObject(3, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        rp.executeUpdate();
    }

    @Override
    public void modifier(reponse_reclamation t) throws SQLException {
        String req = "UPDATE reponse_reclamation  SET id_reponse = ?,reponse=?, date=? where Id_reclamation = ?";
        PreparedStatement rp = cnx.prepareStatement(req);
        rp.setInt(1, t.getId_reponse());
        rp.setString(2, t.getReponse());
        rp.setObject(3, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        rp.setInt(4, t.getId_reclamation());
        rp.executeUpdate();
    }

    @Override
    public void supprimer(reponse_reclamation t) throws SQLException {
         String req = "DELETE FROM reponse_reclamation WHERE id_reponse = ?";
        PreparedStatement rp = cnx.prepareStatement(req);
        rp.setInt(1, t.getId_reponse());
        rp.executeUpdate();
    }

    


private reclamation getReclamationById(int reclamationId) throws SQLException {
    String req = "SELECT * FROM reclamation WHERE id_reclamation = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, reclamationId);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        reclamation rec = new reclamation();
        rec.setId_reclamation(rs.getInt("id_reclamation"));
        //rec.setDate(rs.getObject("date", LocalDateTime.class));
        rec.setDate(rs.getDate(req));
        rec.setDescription(rs.getString("description"));
        return rec;
    }

    return null;
}

private ObservableList<reponse_reclamation> getReponsesByReclamationId(int reclamationId) throws SQLException {
    ObservableList<reponse_reclamation> reponses = FXCollections.observableArrayList();

    String req = "SELECT * FROM reponse_reclamation WHERE id_reclamation = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, reclamationId);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        reponse_reclamation rep = new reponse_reclamation();
        rep.setId_reponse(rs.getInt("id_reponse"));
        rep.setReponse(rs.getString("reponse"));
        rep.setDate(rs.getDate(req));
        rep.setReclamation(getReclamationById(reclamationId));
        reponses.add(rep);
    }

    return reponses;
}

    /*@Override
    public List<reponse_reclamation> recuperer(reponse_reclamation t) throws SQLException {
        List<reponse_reclamation> reponse_reclamations = new ArrayList<>();
        String s = "select * from reponse_reclamation";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            reponse_reclamation r = new reponse_reclamation();
            r.setReponse(rs.getString("reponse"));
            r.setDate(rs.getDate("date"));
            r.setId_reponse(rs.getInt("id_reponse"));
            
            
            reponse_reclamations.add(r);
            
        }
        return reponse_reclamations;
    }*/

    public List<reponse_reclamation> recuperer() throws SQLException {
         ObservableList<reponse_reclamation> reponse_reclamations = FXCollections.observableArrayList();
        String s = "select * from reponse_reclamation";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            reponse_reclamation r = new reponse_reclamation();
            r.setReponse(rs.getString("reponse"));
            r.setDate(rs.getDate("date"));
            r.setId_reponse(rs.getInt("id_reponse"));
            
            
            reponse_reclamations.add(r);
            
        }
        return reponse_reclamations;
    }

    public List<reponse_reclamation> rechercheReponse(String keyword) {
    List<reponse_reclamation> reponses = new ArrayList<>();
    String query = "SELECT * FROM reponse_reclamation WHERE titre LIKE ? ";
    try {
        PreparedStatement stmt = cnx.prepareStatement(query);
        stmt.setString(1, keyword + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            //int id = rs.getInt("id");
            //String titre = rs.getString("titre");
            String reponse = rs.getString("reponse");
            int id_reclamation = rs.getInt("id_reponse");
            reclamation rec = getReclamationById(id_reclamation);
            //rec.setId_reclamation(id_reclamation);
            reponse_reclamation r = new reponse_reclamation(id_reclamation, reponse, rec);
            reponses.add(r);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return reponses;
}

    
}
