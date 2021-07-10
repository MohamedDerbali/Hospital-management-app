/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.ConnexionDB;
import Entities.Maison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YAFET
 */
public class ServiceMaison {

    Connection con;

    public ServiceMaison() {
        con = ConnexionDB.getInstance().getCon();
    }

    public void ajouterMaison(Maison m) {

        String sql = "INSERT INTO maison (nom_maison,adresse_maison,telephone_maison,mail_maison) values (?,?,?,?)";

        PreparedStatement stm;
        try {
            stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, m.getNom_maison());
            stm.setString(2, m.getAdresse_maison());
            stm.setString(3, m.getTelephone_maison());
            stm.setString(4, m.getMail_maison());
         /*   stm.setInt(7, m.getNbr_personne());
            stm.setInt(8, m.getNbr_homme());
            stm.setInt(9, m.getNbr_femme());
            stm.setInt(10, m.getNbr_alzheimer());*/
//            stm.setInt(11, m.getUser().getId());
            int res = stm.executeUpdate();
            ResultSet reset = stm.getGeneratedKeys();
            if (res > 0) {
                System.out.println("Add Done");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerMaison(int id_maison) {
        try {
            String delete = "DELETE FROM maison WHERE id_maison= ? ";
            PreparedStatement st2 = con.prepareStatement(delete);
            st2.setInt(1, id_maison);
            st2.executeUpdate();
            System.out.println("Maison supprimé avec sucée");

        } catch (SQLException ex) {

            System.err.println("SQLException: " + ex.getMessage());
        }

    }

    
        
    public void modifierMaison(Maison m) {
        try {
            String update = "UPDATE maison SET  nom_maison = ? , gouvernerat_maison = ? , "
                    + "telephone_maison = ? , mail_maison = ? ,nbr_personne = ? , nbr_homme = ? , nbr_femme = ? , nbr_alzheimer = ? where id_maison =? ";
            PreparedStatement statement2 = con.prepareStatement(update);
            

            statement2.setString(1, m.getNom_maison());
            statement2.setString(2, m.getAdresse_maison());
            statement2.setString(5, m.getTelephone_maison());
            statement2.setString(6, m.getMail_maison());
            statement2.setInt(7, m.getNbr_personne());
            statement2.setInt(8, m.getNbr_homme());
            statement2.setInt(9, m.getNbr_femme());
            statement2.setInt(10, m.getNbr_alzheimer());
           // statement2.setInt(11,m.getUser().getId());


            statement2.executeUpdate();
            System.out.println("" + m.getNom_maison() + " successfully modified!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("" + m.getNom_maison() + " error modification!!");
        }
    }

    public List<Maison> listMaison() {
        List<Maison> lm = new ArrayList<>();
        try {
            String select = "SELECT  * FROM maison ;";

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Maison m = new Maison();
                m.setId_maison(result.getInt("id_maison"));
                m.setNom_maison(result.getString("nom_maison"));
                m.setAdresse_maison(result.getString("adresse_maison"));
                m.setTelephone_maison(result.getString("telephone_maison"));
                m.setMail_maison(result.getString("mail_maison"));
                m.setNbr_personne(result.getInt("nbr_personne"));
                m.setNbr_homme(result.getInt("nbr_homme"));
                m.setNbr_femme(result.getInt("nbr_femme"));
                m.setNbr_alzheimer(result.getInt("nbr_alzheimer"));
               // m.getUser().setId(result.getInt("id"));

                lm.add(m);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return lm;
    }
    public List<Maison> rechercherMaison(String mail) {

        List<Maison> lm = new ArrayList<Maison>();
        Maison m = new Maison();
        try {
            String select = "SELECT * FROM maison WHERE mail_maison = '" +  mail+ "' ";
            Statement statement1 = con.createStatement();
            ResultSet result = statement1.executeQuery(select);

           while (result.next()) {
               /* m.setId_maison(result.getInt("id_maison"));
                m.setNom_maison(result.getString("nom"));
                m.setAdresse_maison(result.getString("adresse_maison"));
                m.setTelephone_maison(result.getString("telephone_maison"));*/
                m.setMail_maison(result.getString("mail_maison"));
               /* m.setNbr_personne(result.getInt("nbr_personne"));
                m.setNbr_femme(result.getInt("nbr_femme"));
                m.setNbr_alzheimer(result.getInt("nbr_alzheimer"));*/
                //ls.add(m);

            }
        } catch (SQLException e) {
            System.err.println( e.getMessage());
           // System.err.println("SQLSTATE: " + e.getSQLState());
            //System.err.println("VnedorError: " + e.getErrorCode());
        }
        return lm;

    }
        public int nbr_personneMaison(Maison m){
int lm=0;
        try {
            String select = "SELECT  count(*) FROM resident where id_maison= '"+m.getId_maison()+"'";
            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMaison.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                    return lm ;

    }
        
    
    }

    

