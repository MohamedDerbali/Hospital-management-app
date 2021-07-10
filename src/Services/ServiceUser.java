/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.ConnexionDB;
import Entities.User;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author poste
 */
public class ServiceUser {

    Connection con;

    public ServiceUser() {
        con = ConnexionDB.getInstance().getCon();
    }

    public String getUserRoleByEmail(String email) throws SQLException {
        String userRole = "";
        String requete = ("Select roles from user where email=?");
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            userRole = rs.getString("roles");
        }
        return userRole;
    }

    public Boolean UserLogin(String email, String password) throws SQLException {

        String loginQry = "Select * from user where email=?";
        PreparedStatement ste = con.prepareStatement(loginQry);
        ste.setString(1, email);

        ResultSet rs = ste.executeQuery();

        if (rs.next()) {
            if (password.equals(rs.getString("password"))) {

                System.out.println("ceci est l email " + rs.getString("email") + " *** connection valide***");
                return true;
            } else {
                System.out.println("email " + rs.getString("email") + "est valide mais *** motde passe inncorrect***");

            }

        } else {
            System.out.println("l'email est invalide");
        }

        return false;
    }

    public void ajouterUser(User u) {

        try {
            String requete = "INSERT INTO user (nom,prenom,username,datenaissance,email,password,adresse,phone,roles,image,profession,status) values (?,?,?,?,?,?,?,?,?,?,?,'Invalide')";
            PreparedStatement st = con.prepareStatement(requete);
            st.setString(1, u.getNom());
            st.setString(2, u.getPrenom());
            st.setString(3, u.getUsername());
            st.setDate(4, (Date) u.getDate_naissance());
            st.setString(5, u.getEmail());
            st.setString(6, u.getPassword());
            st.setString(7, u.getAdresse());
            st.setFloat(8, u.getPhone());
            st.setString(9, u.getRoles());
            st.setString(10, u.getImage());
            st.setString(11, u.getProfession());

            st.executeUpdate();
            System.out.println("User ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<User> afficherUser() {
        List<User> myList = new ArrayList<User>();
        try {

            String requete2 = "SELECT * FROM user";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete2);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setUsername(rs.getString(4));
                u.setDate_naissance(rs.getDate(5));
                u.setEmail(rs.getString(6));
                u.setPassword(rs.getString(7));
                u.setAdresse(rs.getString(8));
                u.setPhone(rs.getInt(9));
                u.setRoles(rs.getString(10));
                u.setImage(rs.getString(11));
                u.setProfession(rs.getString(12));

                myList.add(u);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    
    
    public User getUserDataByEmail(String email) {
       User u = new User();
        try {

            String requete2 = "SELECT * FROM user where email='"+email+"';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete2);

            while (rs.next()) {
                
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setUsername(rs.getString("username"));
                u.setDate_naissance(rs.getDate("datenaissance"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setAdresse(rs.getString("adresse"));
                u.setPhone(rs.getInt("phone"));
                u.setRoles(rs.getString("roles"));
                u.setImage(rs.getString("image"));
                u.setProfession(rs.getString("profession"));

                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return u;
    }
    

    public List<User> afficherUserAdmin() {
        List<User> myList = new ArrayList<User>();
        try {

            String requete2 = "SELECT id,nom,prenom,phone,email,roles,status FROM user";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete2);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setPhone(rs.getInt(4));
                u.setEmail(rs.getString(5));
                u.setRoles(rs.getString(6));
                u.setStatut(rs.getString(7));

                myList.add(u);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public void supprimerUser(int id) {
        try {
            String delete = "DELETE FROM user WHERE id= ? ";
            PreparedStatement st2 = con.prepareStatement(delete);
            st2.setInt(1, id);
            st2.execute();
            System.out.println("User supprimé avec sucée");

        } catch (SQLException ex) {

            System.err.println("SQLException: " + ex.getMessage());
        }

    }

    public void modifierUser(User u) {
        try {
            String update = "UPDATE user SET  nom = ? , prenom = ? , username = ? , datenaissance = ? , "
                    + "email = ? , password = ? , adresse = ? , phone = ? , roles = ? , image = ? , profession = ? WHERE id = ? ";
            PreparedStatement st2 = con.prepareStatement(update);
            st2.setString(1, u.getNom());
            st2.setString(2, u.getPrenom());
            st2.setString(3, u.getUsername());
            st2.setDate(4, u.getDate_naissance());
            st2.setString(5, u.getEmail());
            st2.setString(6, u.getPassword());
            st2.setString(7, u.getAdresse());
            st2.setFloat(8, u.getPhone());
            st2.setString(9, u.getRoles());
            st2.setString(10, u.getImage());
            st2.setString(11, u.getProfession());
            st2.setInt(12, u.getId());

            st2.executeUpdate();
            System.out.println("" + u.getNom() + " successfully modified!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("" + u.getNom() + " error modification!!");
        }
    }

    public void active_user(int id) {
        try {
            System.out.println("id----------------->" + id);
            String update = "UPDATE user set status='ACTIF' WHERE id=?";
            PreparedStatement u = con.prepareStatement(update);
            u.setInt(1, id);
            u.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("fail to modify");
        }
    }

    public void suspend_user(int id) {
        try {
            System.out.println("id----------------->" + id);
            String update = "UPDATE user set status='SUSPEND' WHERE id=?";
            PreparedStatement u = con.prepareStatement(update);
            u.setInt(1, id);

            u.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("fail to modify");
        }
    }

    public String get_user_status(String email) throws SQLException {
        String userStatut = "";
        String requete = ("Select status from user where email=?");
        PreparedStatement pst = con.prepareStatement(requete);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            userStatut = rs.getString("status");
        }
        return userStatut;
    }

    public Boolean get_user_status_invalid() throws SQLException {

        String requete = ("Select * from user where status='Invalide'");
        PreparedStatement pst = con.prepareStatement(requete);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {

            return true;

        }
        return false;
    }
    
    
       
    public void UpdateUser(User user) {

        
            
             try {
            String update = "UPDATE user  SET adresse = ? , phone = ?  WHERE email = ? ;";

            PreparedStatement statement2 = con.prepareStatement(update);
            statement2.setString(1, user.getAdresse());
            statement2.setInt(2, user.getPhone());
            statement2.setString(3, user.getEmail());

            statement2.executeUpdate();
        } catch (SQLException e) {
            System.err.println("user " + user.getEmail() + " non modifiee");
            System.out.println(e.getMessage());
        }
        
       
            
            
        
        
       

    }
    
    public void UpdateUser_withpass(User user) {

      
            
            
            try {
            String update = "UPDATE user  SET adresse = ? , phone = ?, password = ?  WHERE email = ? ;";

            PreparedStatement statement2 = con.prepareStatement(update);
            statement2.setString(1, user.getAdresse());
            statement2.setInt(2, user.getPhone());
            statement2.setString(3, user.getPassword());
            statement2.setString(4, user.getEmail());
            statement2.executeUpdate();
        } catch (SQLException e) {
            System.err.println("user " + user.getEmail() + " non modifiee");
            System.out.println(e.getMessage());
        }
            
            
        }
       
        
       
 public User getUserData(int id){
        User u = new User();
        
        try{
         String select = "SELECT  * FROM user where id = "+id;
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
        System.out.println("at getUserData "+u);
         return u;
          
    }
    
       
        
       

    }


