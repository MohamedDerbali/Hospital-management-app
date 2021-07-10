/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.ConnexionDB;
import Entities.Action;
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
public class ServiceAction {

    Connection conn = ConnexionDB.getInstance().getCon();

    public void ajouterAction(Action a) {

        String sql = "INSERT INTO action_benevole (type_action,date_d_action,date_f_action,Description,id_user) values (?,?,?,?,?)";
        PreparedStatement sta;
        try {
            sta = conn.prepareStatement(sql);
            sta.setString(1, a.getType_action());
            sta.setDate(2, (Date) a.getDate_d_action());
            sta.setDate(3, (Date) a.getDate_f_action());
            sta.setString(4, a.getDescription());
            sta.setInt(5, a.getUser().getId());

            sta.executeUpdate();

            System.out.println("Add Done");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerAction(int id_action) {
        try {
            String delete = "DELETE FROM action_benevole WHERE id_action=?";
            PreparedStatement st1 = conn.prepareStatement(delete);
            st1.setInt(1, id_action);
            st1.executeUpdate();
            System.out.println(" supprimee !!!");

        } catch (SQLException ex) {

            System.err.println("SQLException: " + ex.getMessage());
        }

    }

    public void modifierAction(Action a) {
        try {
            String update = "UPDATE action_benevole SET  type_action = ? , date_d_action = ? ,date_f_action=? , Description=? where id_action=?";
            PreparedStatement statement2 = conn.prepareStatement(update);

            statement2.setString(1, a.getType_action());
            statement2.setDate(2, a.getDate_d_action());
            statement2.setDate(3, a.getDate_f_action());

            statement2.setString(4, a.getDescription());
            statement2.setInt(5, a.getId_action());

            statement2.executeUpdate();
            System.out.println("" + a.getType_action() + " successfully modified!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("" + a.getType_action() + " error modification!!");
        }
    }

    public List<Action> listAction() {
        List<Action> la = new ArrayList<>();
        try {
            String select = "SELECT  * FROM action_benevole ;";

            Statement statement1 = conn.createStatement();

            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Action a = new Action();
                a.setId_action(result.getInt("id_action"));
                a.setType_action(result.getString("type_action"));
                a.setDate_d_action(result.getDate("date_d_action"));
                a.setDate_f_action(result.getDate("date_f_action"));
                a.setDescription(result.getString("Description"));

                la.add(a);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return la;
    }

}
