/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.ConnexionDB;
import Entities.Maison;
import Entities.Resident;
import java.sql.Connection;
import java.sql.Date;
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
public class ServiceResident {

    Connection con;
    public ResultSet res;

    public ServiceResident() {
        con = ConnexionDB.getInstance().getCon();

    }

    public List<Maison> getMaisonByResident(Maison m) {
        List<Maison> l=new ArrayList<>();
                PreparedStatement st;

        try{
            String rq="select * from resident where id_maison='"+m.getId_maison()+"'";
            st=(PreparedStatement) con.createStatement();
            res=st.executeQuery(rq);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMaison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    public void ajouterResident(Resident r,Maison m) throws SQLException {
                    String query = "insert into resident (id_maison,nom_resident,prenom_resident,age_resident,sexe_resident,date_resident,alzheimer_resident,maladie_resident,nom_maison) values (?,?,?,?,?,?,?,?,?)";
                    String d = "SELECT nbr_personne FROM maison where id_maison='"+r.getId_maison()+"'";
                    String up=" UPDATE maison SET nbr_personne=nbr_personne+1 WHERE id_maison='"+r.getId_maison()+"'";
                    PreparedStatement st;
        List<Maison> list=new ArrayList<Maison>();  

        try {
            PreparedStatement statement = con.prepareStatement(up);
            PreparedStatement statement1 = con.prepareStatement(d);

            st = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
 ResultSet rs=statement1.executeQuery();  
            st.setInt(1, m.getId_maison());
            st.setString(2, r.getNom_resident());
            st.setString(3, r.getPrenom_resident());
            st.setInt(4, r.getAge_resident());
            st.setString(5, r.getSexe_resident());
            st.setDate(6,(Date) r.getDate_resident());
            st.setString(7, r.getAlzheimer());
            st.setString(8, r.getMaladie());
            st.setString(9, r.getNom_maison());
//statement.setInt(1);
                System.out.println("error1");

            st.executeUpdate();
                            System.out.println("error2");

           // int res = st.executeUpdate();
            //                System.out.println("error3");

            ResultSet reset = st.getGeneratedKeys();
                            System.out.println("error4");

            int rowsUpdated = statement.executeUpdate();
                            System.out.println("error5");


           /* if (res > 0) {
                System.out.println("Add Done");
            }*/
           /* if (rowsUpdated > 0) {
    System.out.println("nbr_personne was updated successfully!");
}*/
            while(rs.next()){  
        int nbr_personne = rs.getInt("nbr_personne");
                //System.out.println(nbr_personne);
            }  
                                            System.out.println("get Done ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
         /*     String d = "SELECT nbr_personne FROM maison where id_maison='"+r.getId_maison()+"'";
Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(d);
            if(result.next()) {
	int nbr = result.getInt(1);
                System.out.println(nbr);
	int nbrf = nbr+1;
                System.out.println(nbr+1);
                    String u="UPDATE maison SET nbr_personne='"+(nbr+1)+"' where id_maison ='"+r.getId_maison()+"'";
                System.out.println("update Done");

			}
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }*/
            
    }
     public void modifierResident(Resident r) {
        try {
            String update = "UPDATE resident SET  nom_resident = ? , prenom_resident = ? , age_resident = ?, sexe_resident = ? ,date_resident = ?, alzheimer_resident = ? , "
                    + "maladie_resident = ?  where id_resident =? ";
            PreparedStatement statement2 = con.prepareStatement(update);

            statement2.setString(1, r.getNom_resident());
            statement2.setString(2, r.getPrenom_resident());
            statement2.setInt(3, r.getAge_resident());
            statement2.setString(4, r.getSexe_resident());
            statement2.setDate(5,(Date) r.getDate_resident());
            statement2.setString(6, r.getAlzheimer());
            statement2.setString(7, r.getMaladie());
          
            statement2.executeUpdate();
            System.out.println("" + r.getPrenom_resident() + r.getNom_resident() + " successfully modified!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("" + r.getPrenom_resident() + r.getNom_resident() + " error modification!!");
        }
    }
        public void supprimerResident(int id_resident) {
            try {
                   String delete = "DELETE FROM resident WHERE id_resident= ?";

            PreparedStatement st2 = con.prepareStatement(delete);
            st2.setInt(1, id_resident);
            st2.executeUpdate();
            System.out.println("Résident supprimé avec sucées");

        } catch (SQLException ex) {

            System.err.println("SQLException: " + ex.getMessage());
        }
        }
    public List<Resident> listResident(Maison m) {
        List<Resident> lr = new ArrayList<>();
        try {
            String select = "SELECT  * FROM resident where id_maison='"+m.getId_maison()+"' ";

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Resident r = new Resident();
               // r.setId_resident(result.getInt("id_resident"));
                r.setNom_resident(result.getString("nom_resident"));
                r.setPrenom_resident(result.getString("prenom_resident"));
                r.setAge_resident(result.getInt("age_resident"));
                r.setSexe_resident(result.getString("sexe_resident"));
                r.setDate_resident(result.getDate("date_resident"));
                r.setAlzheimer_resident(result.getString("alzheimer_resident"));
                r.setMaladie_resident(result.getString("maladie_resident"));
               // m.getUser().setId(result.getInt("id"));

                lr.add(r);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return lr;
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
            public List<Resident> listResidentT() {
        List<Resident> lm = new ArrayList<>();
        try {
            String select = "SELECT  * FROM resident ;";

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Resident r = new Resident();
                r.setId_resident(result.getInt("id_resident"));
                r.setNom_resident(result.getString("nom_resident"));
                r.setPrenom_resident(result.getString("prenom_resident"));
                r.setAge_resident(result.getInt("age_resident"));
                r.setSexe_resident(result.getString("sexe_resident"));
                r.setDate_resident(result.getDate("date_resident"));
                r.setAlzheimer_resident(result.getString("alzheimer_resident"));
                r.setMaladie_resident(result.getString("maladie_resident"));
                r.setNom_maison(result.getString("nom_maison"));
               // m.getUser().setId(result.getInt("id"));

                lm.add(r);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return lm;
    }
            
  public Resident getResidentById(int id){
        
       Resident r = new Resident();
        try {
            String select = "SELECT  * FROM resident where id_resident = "+id;

            Statement statement1 = con.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                
                r.setId_resident(result.getInt("id_resident"));
                r.setNom_resident(result.getString("nom_resident"));
                r.setPrenom_resident(result.getString("prenom_resident"));
                r.setAge_resident(result.getInt("age_resident"));
                r.setAlzheimer_resident(result.getString("alzheimer_resident"));
                r.setMaladie_resident(result.getString("maladie_resident"));
                //m.getUser().setId(result.getInt("id"));
                //r.setMaison(getMaisonData(result.getInt("id_maison")));
                r.setNom_maison(result.getString("nom_maison"));
                
               // System.out.println(getMaisonData(result.getInt("id_maison")));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
    /*
    public Maison getMaisonData(int id){
        Maison c = new Maison();
        
        try{
         String req = "select * from maison where id_maison="+id;
         Statement statement1 = con.createStatement();
         
          ResultSet result = statement1.executeQuery(req);
          
           while (result.next()) {
          
          c.setId(result.getInt("id_maison"));
          c.setNom_maison(result.getString("nom_maison"));
          c.setNbr_alzheimer(0);
          c.setNbr_personne(0);
          c.setUser(null);
          c.setMail_maison(null);
          c.setGouvernerat_maison(null);
          c.setVille_maison(null);
          c.setCodepostal_maison(null);
          c.setTelephone_maison(null);
          c.setNbr_homme(0);
          c.setNbr_femme(0);
          c.setUser(null);
        
          
           }
          
        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        //System.out.println("in getMaisonData "+ c);
         return c;
          
    }*/
            
    }
    
    
    


