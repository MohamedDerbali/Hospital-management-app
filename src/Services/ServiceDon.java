/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.ConnexionDB;
import Entities.Demande;
import Entities.Don;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ServiceDon {
    Connection con;

    public ServiceDon() {
        con = ConnexionDB.getInstance().getCon();

    }

     //Ajouter demande
    
    public  void ajouterDon(Don d){
        try{
            
            String requete = "insert into don (categorie_don,quantite_don,description_don,id_user,id_demande) values ('"
                              +d.getCategori_don()+"','"
                              +d.getQuantite_don()+"','"
                              +d.getDescription_don()+"','"
                              +d.getId_user()+"','"
                              +d.getId_demande()+"')";
            
             Statement st = con.createStatement();
             st.executeUpdate(requete);
             System.out.println("Don ajoutée avec sucée");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public Don getcategoreDonVetement(){
         
         Don m = new Don();
          try{
            
            String req ="SELECT categorie_don from don where categorie_don = 'vetement' ";
            Statement st = con.createStatement();
            ResultSet result =  st.executeQuery(req);
            
            
            while (result.next()){
                
               m.setId_don(result.getInt("id_don"));
                m.setCategori_don(result.getString("categorie_don"));
                m.setQuantite_don(result.getInt("quantite_don"));
                m.setDescription_don(result.getString("description_don"));
                m.setId_user(result.getInt("id_user"));
                m.setId_demande(result.getInt("id_demande"));
               
                
                System.out.println("aaaaaaaa");
                
                
            }
            
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return m;
     }   
     //Supprimer dons
    
     /*  public void supprimerDon(int id)  {
           try{
               
           
        String requete = "DELETE FROM don WHERE id_don = ?";
              PreparedStatement preparedStmt = con.prepareStatement(requete);
              preparedStmt.setInt(1,id);
              preparedStmt.execute(); 
              System.out.println("Don supprimé avec sucée");

 
       }
          catch(SQLException e){
            System.out.println(e.getMessage());
        }
       }
           
           //Modifier demande
       
       public void modifierDemande(int id_don, String categori_don, int quantite_don,String descr_don){
        try {
            String update = "UPDATE don SET categorie_don = ? , "
                    + "quantite_don = ? , description_don = ? WHERE id_don = ? ";
            PreparedStatement st2 = con.prepareStatement(update);
            st2.setString(1,categori_don);      
            st2.setInt(2,quantite_don);
            st2.setString(3,descr_don);
            st2.setInt(4,id_don);


            st2.executeUpdate();
            System.out.println("Modification don avec succé");

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
       //Lister demandes
    
    public List<Don> listeDon(){
        
        List<Don> myList =  new ArrayList<>();
        try{
            
            String req = "select * from don ";
            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(req);
            
            
            while (rs.next()){
                
                Don d = new Don();
                d.setId_don(rs.getInt(1));
                System.out.println("addichage id "+d.getId_don());
                
                d.setCategori_don(rs.getString(2));
               // System.out.println("------------"+rs.getString(4));
                System.out.println("affichage categorie "+d.getCategori_don());
                
                
                d.setQuantite_don(rs.getInt(3));
                System.out.println("affichae quantite "+d.getQuantite_don());
                d.setDescription_don(rs.getString(4));
                System.out.println("affichage description "+d.getDescription_don());
                d.setUser(null);
                d.setDemande(null);
                
               // p.setNom(rs.getString(2));
               // p.setPrenom(rs.getString(3));
                
                // myList.add(p);
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
       */
    
}
