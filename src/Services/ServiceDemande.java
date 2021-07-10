/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.ConnexionDB;
import Entities.Demande;
import Entities.Maison;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ServiceDemande {
    Connection con;

    public ServiceDemande() {
        con = ConnexionDB.getInstance().getCon();

    }

     //Ajouter demande
    
    public  void ajouterDemande(Demande d){
       try{
            
            String requete = "insert into demande (id_maison,categorie_demande,quantite_demande,description_demande,id_user) values ('"
                              +d.getId_maison()+"','"
                              +d.getCategorie_demande()+"','"
                              +d.getQuantite_demande()+"','"
                              +d.getDescription_demande()+"','"   
                              +d.getId_user()+"')";
            
             Statement st = con.createStatement();
             st.executeUpdate(requete);
             System.out.println("Demande ajoutée avec sucée");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
        //Lister demandes
    
    public List<Demande> listeDemande(){
        
        List<Demande> myList =  new ArrayList<>();
        try{
            
            String req = "SELECT  * FROM demande,maison,user where "
                    + "demande.id_maison = maison.id_maison and "
                    + "demande.id_user = user.id ";
           
            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(req);
            
            while (rs.next()){
                

                
                Demande d = new Demande();
                d.setId_demande(rs.getInt(rs.getInt("id_demande")));
                d.setId_maison((rs.getInt("id_maison")));
                d.setCategorie_demande(rs.getString("categorie_demande"));
                d.setQuantite_demande(rs.getInt("quantite_demande"));
                d.setDescription_demande(rs.getString("quantite_demande"));
                d.setId_user((rs.getInt("id_user")));
                            System.out.println("tessssssssssssst");

                
                
                 myList.add(d);
            }
            
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return myList;
        
    }
     //Supprimer dons
    
       public void supprimerDemande(int id)  {
           try{
               
           
        String requete = "DELETE FROM demande WHERE id_demande = ?";
              PreparedStatement preparedStmt = con.prepareStatement(requete);
              preparedStmt.setInt(1,id);
              preparedStmt.execute(); 
              System.out.println("Demande supprimé avec sucée");

 
       }
          catch(SQLException e){
            System.out.println(e.getMessage());
        }
       }
           
           //Modifier demande
       
       public void modifierDemande(int id_demande, String categori_demande, int quantite_demande,String descr_demande){
                try {
            String update = "UPDATE demande SET categorie_demande = ? , "
                    + "quantite_demande = ? , description_demande = ? WHERE id_demande = ? ";
            PreparedStatement st2 = con.prepareStatement(update);
            st2.setString(1,categori_demande);      
            st2.setInt(2,quantite_demande);
            st2.setString(3,descr_demande);
            st2.setInt(4,id_demande);


            st2.executeUpdate();
            System.out.println("Modification demande avec succé");

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
      
    }
       
       public List<Maison> getMaisonData(){

           List<Maison>  m=new ArrayList<>();
           
           
           try{
               String req = "select * from maison";
               Statement statement1 = con.createStatement();
               ResultSet rs = statement1.executeQuery(req);
               while (rs.next()) {
          
          
           Maison p = new Maison();
                p.setNom_maison(rs.getString(2));
               // p.setMaison
               
               // p.setEtat_demande(rs.getString(7));
                
                m.add(p);

          
               }
           }
         catch(SQLException ex)
        {
            System.out.println("getuserData : "+ex.getMessage());
        }
        
         return m;
          
          
           }
       
       public User getUserData(int id){
        User u = new User();
        
        try{
         String req = "select * from user where id="+id;
         Statement statement1 = con.createStatement();
         
          ResultSet result = statement1.executeQuery(req);
          
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
        public List<Demande> listeDemande2(){
        
        List<Demande> myList =  new ArrayList<>();
        try{
            
            String req = "select * from demande";
            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(req);
            
            
            while (rs.next()){
                
                Demande p = new Demande();
                p.setId_demande(rs.getInt(1));
               // p.setMaison
                p.setCategorie_demande(rs.getString(3));
                p.setQuantite_demande(rs.getInt(4));
                p.setDescription_demande(rs.getString(5));
                //p.setUser(null);
               // p.setEtat_demande(rs.getString(7));
                
                myList.add(p);
            }
            
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return myList;
        
    }
        public List<Demande> listeDemande3(){
        
        List<Demande> myList =  new ArrayList<>();
        try{
            
            String req = "select * from demande";
            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(req);
            
            
            while (rs.next()){
                
                Demande p = new Demande();
                p.setId_demande(rs.getInt(1));
                p.setId_maison(rs.getInt(2));
                p.setCategorie_demande(rs.getString(3));
                p.setQuantite_demande(rs.getInt(4));
                p.setDescription_demande(rs.getString(5));
                //p.setUser(null);
               // p.setEtat_demande(rs.getString(7));
                
                myList.add(p);
            }
            
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return myList;
        
    }
     public Maison getMaisonDataByNom(String nom){
         
         Maison m = new Maison();
          try{
            
            String req = "select * from maison where nom_maison = '"+nom+"'";
            Statement st = con.createStatement();
            ResultSet result =  st.executeQuery(req);
            
            
            while (result.next()){
                
               m.setId_maison(result.getInt("id_maison"));
                m.setNom_maison(result.getString("nom_maison"));
               // m.setGouvernerat_maison(result.getString("gouvernerat_maison"));
               // m.setVille_maison(result.getString("ville_maison"));
                m.setAdresse_maison(result.getString("adresse_maison"));
                m.setTelephone_maison(result.getString("telephone_maison"));
                m.setMail_maison(result.getString("mail_maison"));
                m.setNbr_personne(result.getInt("nbr_personne"));
                m.setNbr_homme(result.getInt("nbr_homme"));
                m.setNbr_femme(result.getInt("nbr_femme"));
                m.setNbr_alzheimer(result.getInt("nbr_alzheimer"));
                
                
            }
            
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return m;
     }
     
     public ObservableList<Demande> chercherDemande(String name) throws SQLException{
         ObservableList<Demande> d=FXCollections.observableArrayList();
         
             try{
            String req = "select * from demande where description_demande = '"+name+"'";
            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(req);
            while (rs.next()){
                
                Demande p = new Demande();
                p.setId_demande(rs.getInt(1));
               // p.setMaison
                p.setCategorie_demande(rs.getString(3));
                p.setQuantite_demande(rs.getInt(4));
                p.setDescription_demande(rs.getString(5));
                //p.setUser(null);
               // p.setEtat_demande(rs.getString(7));
                
                d.add(p);
            }
            
            
             }
               catch(SQLException e){
            System.out.println(e.getMessage());
        }
             return d;
                 
             
        
             
     
  
  
            
        
    }

       
}
       
      

    
        
        
    
    
    

