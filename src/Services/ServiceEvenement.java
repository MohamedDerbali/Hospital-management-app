/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.ConnexionDB;
import Entities.Evenement;
import Entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author acer
 */
public class ServiceEvenement {

    Connection con = ConnexionDB.getInstance().getCon();

    public void ajouterEvenement(Evenement e) {

        String sql = "INSERT INTO evenement (id_evenement, date_d_evenement, date_f_evenement, heure_evenement, nom_evenement, adresse_evenement ,description_evenement) values (?,?,?,?,?,?,?)";
        PreparedStatement stm;
        try {
            stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, e.getId_evenement());
            stm.setDate(2, (Date) e.getDate_d_evenement());
            stm.setDate(3, (Date) e.getDate_f_evenement());
            stm.setString(4, e.getHeure_evenement());
            stm.setString(5, e.getNom_evenement());
            stm.setString(6, e.getAdresse_evenement());
            stm.setString(7, e.getDescription_evenement());

            int res = stm.executeUpdate();
            ResultSet reset = stm.getGeneratedKeys();
            if (res > 0) {
                System.out.println("Add Done");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerEvenement(int id_evenement) {
        try {
            String delete = "DELETE FROM evenement WHERE id_evenement= ? ";
            PreparedStatement st2 = con.prepareStatement(delete);
            st2.setInt(1, id_evenement);
            st2.executeUpdate();
            System.out.println(" supprimee !!!");

        } catch (SQLException ex) {

            System.err.println("SQLException: " + ex.getMessage());
        }

    }

    public void modifierEvenement(Evenement e) {
        try {
            String update = "UPDATE evenement SET  date_d_evenement = ? , date_f_evenement = ? , heure_evenement = ? , nom_evenement = ?, adresse_evenement = ? , description_evenement = ? where id_evenement = ?";

            PreparedStatement statement2 = con.prepareStatement(update);

            statement2.setDate(1, (Date) e.getDate_d_evenement());
            statement2.setDate(2, (Date) e.getDate_f_evenement());
            statement2.setString(3, e.getHeure_evenement());
            statement2.setString(4, e.getNom_evenement());
            statement2.setString(5, e.getAdresse_evenement());
            statement2.setString(6, e.getDescription_evenement());
            statement2.setInt(7, e.getId_evenement());
            statement2.executeUpdate();
            System.out.println("" + e.getNom_evenement() + " successfully modified!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("" + e.getNom_evenement() + " error modification!!");
        }
    }

    public List<Evenement> listEvenement() {
        List<Evenement> le = new ArrayList<>();
        try {
            String select = "SELECT  id_evenement,date_d_evenement ,date_f_evenement , heure_evenement,nom_evenement,adresse_evenement , description_evenement FROM evenement ";

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Evenement e = new Evenement();
                e.setId_evenement(result.getInt("id_evenement"));
                e.setDate_d_evenement(result.getDate("date_d_evenement"));
                e.setDate_f_evenement(result.getDate("date_f_evenement"));
                e.setHeure_evenement(result.getString("heure_evenement"));
                e.setNom_evenement(result.getString("nom_evenement"));
                e.setAdresse_evenement(result.getString("adresse_evenement"));
                e.setDescription_evenement(result.getString("description_evenement"));
                le.add(e);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return le;
    }

    public List<Evenement> afficherByNom(String nom) {
        List<Evenement> le = new ArrayList<>();
        try {
            String select = "SELECT * from evenement where nom_evenement= ?";

            PreparedStatement statement1 = con.prepareStatement(select);
            statement1.setString(1, nom);

            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                Evenement e = new Evenement();
                e.setId_evenement(result.getInt("id_evenement"));
                e.setDate_d_evenement(result.getDate("date_d_evenement"));
                e.setDate_f_evenement(result.getDate("date_f_evenement"));
                e.setHeure_evenement(result.getString("heure_evenement"));
                e.setNom_evenement(result.getString("nom_evenement"));
                e.setAdresse_evenement(result.getString("adresse_evenement"));
                e.setDescription_evenement(result.getString("description_evenement"));
                le.add(e);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return le;

    }

    public List<Evenement> afficherByAdresse(String adresse) {
        List<Evenement> le = new ArrayList<>();
        try {
            String select = "SELECT * from evenement where adresse_evenement= ?";

            PreparedStatement statement1 = con.prepareStatement(select);
            statement1.setString(1, adresse);

            ResultSet result = statement1.executeQuery();

            while (result.next()) {
                Evenement e = new Evenement();
                e.setId_evenement(result.getInt("id_evenement"));
                e.setDate_d_evenement(result.getDate("date_d_evenement"));
                e.setDate_f_evenement(result.getDate("date_f_evenement"));
                e.setHeure_evenement(result.getString("heure_evenement"));
                e.setNom_evenement(result.getString("nom_evenement"));
                e.setAdresse_evenement(result.getString("adresse_evenement"));
                e.setDescription_evenement(result.getString("description_evenement"));
                le.add(e);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return le;

    }

    public List<String> listEmail() {
        List<String> lmu = new ArrayList<>();
        try {
            String select = "SELECT  email FROM user ";

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                lmu.add(result.getString("email"));

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return lmu;
    }
}
