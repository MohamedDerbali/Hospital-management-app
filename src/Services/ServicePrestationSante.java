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
import Entities.Prestation_sante;
import Entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmed
 */
public class ServicePrestationSante {
    
    Connection con;
    public ServicePrestationSante() {
         con = ConnexionDB.getInstance().getCon();
         
    }
    
    public void ajouterPrestation(Prestation_sante ps) {

        String sql = "INSERT INTO prestation_sante (id_fiche,id_user,id_resident,nom_resident,prenom_resident,nom_user,prenom_user,medicaments,date) "
                + "values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm;
        try {
            stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            
            stm.setInt(1, ps.getId_fiche());
            stm.setInt(2, ps.getId_user());
            stm.setInt(3, ps.getId_resident());
            stm.setString(4, ps.getNom_resident());
            stm.setString(5, ps.getPrenom_resident());
            stm.setString(6, ps.getNom_user());
            stm.setString(7, ps.getPrenom_user());
            stm.setString(8, ps.getMedicaments());
            stm.setDate(9, ps.getDate());

            int res = stm.executeUpdate();
            ResultSet reset = stm.getGeneratedKeys();
            if (res > 0) {
                System.out.println("Add Prestation Done");
                             //   JOptionPane.showMessageDialog(null, "Prestation ajoutée");
                                

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    
    public void supprimerPrestationById(int id) {
        try {
            String delete = "DELETE FROM prestation_sante WHERE id_prestation= ? ";
            PreparedStatement st2 = con.prepareStatement(delete);
            st2.setInt(1, id);
            st2.executeUpdate();
           
            
            

        } catch (SQLException ex) {

            System.err.println("SQLException: " + ex.getMessage());
        }

    }
    
      public void supprimerPrestationByIdFile(int id) {
        try {
            String delete = "DELETE FROM prestation_sante WHERE id_fiche= ? ";
            PreparedStatement st2 = con.prepareStatement(delete);
            st2.setInt(1, id);
            st2.executeUpdate();
         /*  int res = st2.executeUpdate();
            ResultSet reset = st2.getGeneratedKeys();
            if (res > 0) {
                System.out.println("Prestation supprimée");
                                JOptionPane.showMessageDialog(null, "Prestation supprimée");
                                

            }*///JOptionPane.showMessageDialog(null, "Prestation supprimée");

        } catch (SQLException ex) {

            System.err.println("SQLException: " + ex.getMessage());
        }

    }
    
    
    public void modifierPrestation(Prestation_sante ps) {
        try {
            String update = "UPDATE prestation_sante SET  medicaments = ? where id_prestation = ?";
            PreparedStatement statement2 = con.prepareStatement(update);

           
            statement2.setString(1, ps.getMedicaments());
            statement2.setInt(2, ps.getId_prestation());
  
         //   statement2.executeUpdate();
            
             int res = statement2.executeUpdate();
            if (res > 0) {
            System.out.println(" success modification!");
               // JOptionPane.showMessageDialog(null, "Prestation Modifiée");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        
        
    }
    
    public List<Prestation_sante> listPrestation() {
        List<Prestation_sante> lps = new ArrayList<>();
        try {
            String select = "SELECT  * FROM prestation_sante,resident,fiche_medicale,user where "
                    + "prestation_sante.id_resident = resident.id_resident and "
                    + "prestation_sante.id_fiche = fiche_medicale.id_fiche and "
                    + "prestation_sante.id_user = user.id ;";
           
       

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Prestation_sante ps = new Prestation_sante();
                
                
                ps.setId_prestation(result.getInt("id_prestation"));
                ps.setId_user(result.getInt("id_user"));
                ps.setId_fiche(result.getInt("id_fiche"));
                ps.setId_resident(result.getInt("id_resident"));
                ps.setNom_resident(result.getString("nom_resident"));
                ps.setNom_user(result.getString("nom_user"));
                ps.setPrenom_user(result.getString("prenom_user"));
                ps.setPrenom_resident(result.getString("prenom_resident"));
                ps.setMedicaments(result.getString("medicaments"));
                ps.setDate(result.getDate("date"));
                
                
  
             /*  
                ps.setMedicaments_prestation(result.getString("medicaments_prestation"));
                ps.setCentre_quine(getCentreQuineData(result.getInt("id_centre")));
                ps.setFich_medicale(getFicheData(result.getInt("id_fiche")));
                ps.setUser(getUserData(result.getInt("id")));
                ///
                ps.setResident(null);
            */
              
                lps.add(ps);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lps;
    }
    
    
    
    public List<Fiche_medicale> getListFicheByIdResdient(int id) {
        List<Fiche_medicale> lf = new ArrayList<>();
        
        try {
            String select = "SELECT  * FROM prestation_sante,resident,fiche_medicale,user where "
                    + "prestation_sante.id_resident = resident.id_resident and "
                    + "prestation_sante.id_fiche = fiche_medicale.id_fiche and "
                    + "prestation_sante.id_user = user.id and "
                    + "prestation_sante.id_resident = "+id;
           
           
            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                
               // System.out.println("hereeeee "+result.getInt("id_fiche"));
                lf.add(getFicheById(result.getInt("id_fiche")));
               

            }
        } catch (SQLException ex) {
            System.err.println("at getListFicheByIdResdient " +ex.getMessage());
        }
        return lf;
    }
    
    
    
    public Fiche_medicale getFicheById(int id){
        Fiche_medicale fm = new Fiche_medicale();
        try{
         String req = "select * from fiche_medicale where id_fiche="+id;
         Statement statement1 = con.createStatement();
          
          ResultSet result = statement1.executeQuery(req);
          
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
          
        }catch(SQLException ex)
        {
            System.out.println("at getFicheData : "+ex.getMessage());
        }
        
         return fm;
          
    }
  
    public Prestation_sante getPrestationRowByIdResident(int id){
                        Prestation_sante ps = new Prestation_sante();

         try {
            String select = "SELECT  * FROM prestation_sante,resident,fiche_medicale,user where "
                    + "prestation_sante.id_resident = resident.id_resident and "
                    + "prestation_sante.id_fiche = fiche_medicale.id_fiche and "
                    + "prestation_sante.id_user = user.id  and prestation_sante.id_resident ="+id;
           
      
            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                
                
                ps.setId_prestation(result.getInt("id_prestation"));
                ps.setId_user(result.getInt("id_user"));
                ps.setId_fiche(result.getInt("id_fiche"));
                ps.setId_resident(result.getInt("id_resident"));
                ps.setNom_resident(result.getString("nom_resident"));
                ps.setNom_user(result.getString("nom_user"));
                ps.setPrenom_user(result.getString("prenom_user"));
                ps.setPrenom_resident(result.getString("prenom_resident"));
                ps.setMedicaments(result.getString("medicaments"));
                ps.setDate(result.getDate("date"));
      

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      //   System.out.println("prest data :"+ps);
        return ps;
    }
    
    public Prestation_sante getPrestationRowByIdFiche(int id){
                        Prestation_sante ps = new Prestation_sante();

         try {
            String select = "SELECT  * FROM prestation_sante,resident,fiche_medicale,user where "
                    + "prestation_sante.id_resident = resident.id_resident and "
                    + "prestation_sante.id_fiche = fiche_medicale.id_fiche and "
                    + "prestation_sante.id_user = user.id  and prestation_sante.id_fiche ="+id;
           
      
            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                
                
                ps.setId_prestation(result.getInt("id_prestation"));
                ps.setId_user(result.getInt("id_user"));
                ps.setId_fiche(result.getInt("id_fiche"));
                ps.setId_resident(result.getInt("id_resident"));
                ps.setNom_resident(result.getString("nom_resident"));
                ps.setNom_user(result.getString("nom_user"));
                ps.setPrenom_user(result.getString("prenom_user"));
                ps.setPrenom_resident(result.getString("prenom_resident"));
                ps.setMedicaments(result.getString("medicaments"));
                ps.setDate(result.getDate("date"));
      

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      //   System.out.println("prest data :"+ps);
        return ps;
    }
    
    public User getUserData(int id){
        User u = new User();
        
        try{
         String select = "SELECT  * FROM prestation_sante,resident,fiche_medicale,user where "
                    + "prestation_sante.id_resident = resident.id_resident and "
                    + "prestation_sante.id_fiche = fiche_medicale.id_fiche and "
                    + "prestation_sante.id_user = user.id and "
                    + "user.id = "+id;
         Statement statement1 = con.createStatement();
         
          ResultSet result = statement1.executeQuery(select);
          
           while (result.next()) {
          
          u.setId(result.getInt("id"));
          u.setNom(result.getString("nom"));
          u.setPrenom(result.getString("prenom"));
          u.setUsername(result.getString("username"));
          u.setDate_naissance(result.getDate("datenaissance"));
          u.setEmail(result.getString("email"));
          u.setPassword(result.getString("password"));
          u.setAdresse(result.getString("adresse"));
          u.setPhone(result.getInt("phone"));
          u.setRoles(result.getString("roles"));
          u.setImage(result.getString("image"));
          u.setProfession(result.getString("profession"));
          
           }
          
        }catch(SQLException ex)
        {
            System.out.println("at getUserData : "+ex.getMessage());
        }
         return u;
          
    }
    
}
