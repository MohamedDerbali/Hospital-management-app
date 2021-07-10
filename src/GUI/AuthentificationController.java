/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Config.ConnexionDB;
import Entities.User;
import Services.ServiceUser;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author poste
 */
public class AuthentificationController implements Initializable {

    @FXML
    private AnchorPane loginRootP;
    @FXML
    private JFXButton login_btn;
    @FXML
    private Hyperlink inscrit_lien;
    @FXML
    private TextField email_txt;
    @FXML
    private PasswordField mdp_txt;
    @FXML
    private Hyperlink mdpo_lien;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void LoginAction(ActionEvent event) throws SQLException, IOException {

        ServiceUser su = new ServiceUser();
        ConnexionDB.connectedUser = su.getUserDataByEmail(email_txt.getText());
        System.out.println(ConnexionDB.connectedUser);
        Boolean isConnected = su.UserLogin(email_txt.getText(), mdp_txt.getText());
        String userRole = su.getUserRoleByEmail(email_txt.getText());
        String userStatut = su.get_user_status(email_txt.getText());
        if (email_txt.getText().isEmpty() && mdp_txt.getText().isEmpty()) {
            email_txt.setStyle("-fx-background-color: #" + "ff7d9f");
            mdp_txt.setStyle("-fx-background-color: #" + "ff7d9f");
        } else if (isConnected) {
            if (userRole.equalsIgnoreCase("Admin") && (userStatut.equalsIgnoreCase("ACTIF"))) {

                Parent page = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();
            } else if (!userRole.equalsIgnoreCase("Admin") && userStatut.equalsIgnoreCase("ACTIF")) {

                Parent page = FXMLLoader.load(getClass().getResource("AcceuilClient.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();

            } else if (!userRole.equalsIgnoreCase("Admin") && userStatut.equalsIgnoreCase("SUSPEND")) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Votre compte est Suspendu !!! ");

                alert.showAndWait();

            } else if (!userRole.equalsIgnoreCase("Admin") && userStatut.equalsIgnoreCase("Invalide")) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Votre compte n'est pas encore activer !!! ");

                alert.showAndWait();

            } 
        }else if (!email_txt.getText().isEmpty() && !mdp_txt.getText().isEmpty()) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Votre mot de passe ou bien email est incorrect !!! ");

                alert.showAndWait();

            }
        
    }

    @FXML
    private void InscriptionAction(ActionEvent event) throws IOException {

        Parent page = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void oublierAction(ActionEvent event) throws IOException {

        Parent page = FXMLLoader.load(getClass().getResource("MdpOublier.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

}
