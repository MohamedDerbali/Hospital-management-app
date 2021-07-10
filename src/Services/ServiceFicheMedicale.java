/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.ConnexionDB;
import Entities.Centre_quine;
import Entities.Fiche_medicale;
import Entities.Maison;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmed
 */
public class ServiceFicheMedicale {
    
     Connection con;
    public ServiceFicheMedicale() {
         con = ConnexionDB.getInstance().getCon();
         
    }
    
    public void ajouterFicheMedicale(Fiche_medicale fm) {

        String sql = "INSERT INTO fiche_medicale (date_creation_fiche,date_dermodif_fiche,remarques_fiche,"
                + "niveau_temp,niveau_sec,niveau_tension,groupe_sanguin,"
                + "medicaments,taille_resident,poids_resident) "
                + "values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm;
        try {
            stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            stm.setDate(1, (Date) fm.getDate__creation_fich());
            stm.setDate(2, (Date) fm.getDermodif_fich());
            stm.setString(3, fm.getRemarques_fiche());
            stm.setFloat(4,  fm.getNiveauTemp());
            stm.setFloat(5, fm.getNiveauSuc());
            stm.setFloat(6, fm.getNiveauTension());
            stm.setString(7, fm.getGroupeSanguin());
            stm.setString(8, fm.getMedicaments());
            stm.setFloat(9, fm.getTaille_resident());
            stm.setFloat(10, fm.getPoids_resident());

            int res = stm.executeUpdate();
            ResultSet reset = stm.getGeneratedKeys();
            if (res > 0) {
                System.out.println("Add Done");
                //JOptionPane.showMessageDialog(null, "Fiche ajoutée");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Fiche ajoutée");
                alert.show();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public void supprimerFicheMedicale(int id) {
        try {
            String delete = "DELETE FROM fiche_medicale WHERE id_fiche= ? ";
            PreparedStatement st2 = con.prepareStatement(delete);
            st2.setInt(1, id);
            st2.executeUpdate();
           /*  int res = st2.executeUpdate();
            ResultSet reset = st2.getGeneratedKeys();
            if (res > 0) {
                System.out.println("Fiche supprimée");
                                JOptionPane.showMessageDialog(null, "Fiche supprimée");

            }*/ //JOptionPane.showMessageDialog(null, "Fiche supprimée");
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Information");
         alert.setHeaderText("Fiche supprimée");
         alert.show();

        } catch (SQLException ex) {

            System.err.println("SQLException: " + ex.getMessage());
        }

    }
     
    public void modifierFicheMedicale(Fiche_medicale fm) {
        try {
            String update = "UPDATE fiche_medicale SET  date_creation_fiche = ? , date_dermodif_fiche = ? , remarques_fiche = ?"
                    + " ,niveau_temp = ?, niveau_sec= ?, niveau_tension = ?, groupe_sanguin = ?, medicaments = ?"
                    + ", taille_resident = ? , poids_resident = ?  where id_fiche = ?";
            PreparedStatement statement2 = con.prepareStatement(update);
  
            statement2.setDate(1, (Date) fm.getDate__creation_fich());
            statement2.setDate(2, (Date) fm.getDermodif_fich());
            statement2.setString(3, fm.getRemarques_fiche());
            statement2.setFloat(4, fm.getNiveauTemp());
            statement2.setFloat(5, fm.getNiveauSuc());
            statement2.setFloat(6, fm.getNiveauTension());
            statement2.setString(7, fm.getGroupeSanguin());
            statement2.setString(8, fm.getMedicaments());
            statement2.setFloat(9, fm.getTaille_resident());
            statement2.setFloat(10, fm.getPoids_resident());
            statement2.setInt(11, fm.getId_fich());
       
            

            statement2.executeUpdate();
            System.out.println(" success modification!");
            // JOptionPane.showMessageDialog(null, "Fiche modifiée avec succés");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Information");
         alert.setHeaderText("Fiche modifiée");
         alert.show();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        
        
    }
     
   public Fiche_medicale getFicheDataById (int id){
        List<Fiche_medicale> lfm = new ArrayList<>();
        Fiche_medicale fm = new Fiche_medicale();
        try {
            String select = "SELECT  * FROM fiche_medicale where id_fiche = "+id;

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);
            

            while (result.next()) {       
              
                   fm.setId_fich(result.getInt("id_fiche"));
                fm.setDate__creation_fich(result.getDate("date_creation_fiche"));
                fm.setDermodif_fich(result.getDate("date_dermodif_fiche"));
                fm.setRemarques_fiche(result.getString("remarques_fiche"));
                fm.setNiveauTemp(result.getFloat("niveau_temp"));
                fm.setNiveauSuc(result.getFloat("niveau_sec"));
                fm.setNiveauTension(result.getFloat("niveau_tension"));
                fm.setGroupeSanguin(result.getString("groupe_sanguin"));
                fm.setMedicaments(result.getString("medicaments"));
                fm.setTaille_resident(result.getFloat("taille_resident"));
                fm.setPoids_resident(result.getFloat("poids_resident"));

            }
            lfm.stream().forEach((j) -> {
           System.out.println(j);
   });
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
         return fm;
   }
     
     
     
      public List<Fiche_medicale> listFiche() {
        List<Fiche_medicale> lfm = new ArrayList<>();
        try {
            String select = "SELECT  * FROM fiche_medicale ;";

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Fiche_medicale fm = new Fiche_medicale();                
                
                fm.setId_fich(result.getInt("id_fiche"));
                fm.setDate__creation_fich(result.getDate("date_creation_fiche"));
                fm.setDermodif_fich(result.getDate("date_dermodif_fiche"));
                fm.setRemarques_fiche(result.getString("remarques_fiche"));
                fm.setNiveauTemp(result.getFloat("niveau_temp"));
                fm.setNiveauSuc(result.getFloat("niveau_sec"));
                fm.setNiveauTension(result.getFloat("niveau_tension"));
                fm.setGroupeSanguin(result.getString("groupe_sanguin"));
                fm.setMedicaments(result.getString("medicaments"));
                fm.setTaille_resident(result.getFloat("taille_resident"));
                fm.setPoids_resident(result.getFloat("poids_resident"));

                
               
              
                lfm.add(fm);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
         
        }
        return lfm;
    }
}