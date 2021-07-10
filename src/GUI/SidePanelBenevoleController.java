/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Config.ConnexionDB;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class SidePanelBenevoleController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private Label label_nom_user;
    @FXML
    private Label role;
    @FXML
    private JFXButton boutonAccueil;
    @FXML
    private JFXButton boutonEvenement;
    @FXML
    private JFXButton boutonAppelDons;
    @FXML
    private JFXButton boutonAction;
    @FXML
    private JFXButton boutonProfil;
    @FXML
    private JFXButton boutonVideo;
    @FXML
    private JFXButton boutonPrestation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         label_nom_user.setText(ConnexionDB.connectedUser.getPrenom()+" "+ConnexionDB.connectedUser.getNom());
    role.setText(ConnexionDB.connectedUser.getRoles());
    Image img = new Image("file:///"+ConnexionDB.connectedUser.getImage());
    userImage.setImage(img);
    }    
        
    
    @FXML
    private void ProfileImagePickerAction(MouseEvent event) {
    }

    @FXML
    private void changeColor(ActionEvent event) throws IOException {
        
       
        
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch (btn.getId()) {
            //case "Color 1":FXMLDocumentController.rootP.setBottomAnchor("FXMLDocument.fxml");//setStyle("-fx-background-color:#00FF00");
            //   break;
             case "boutonAccueil":
                         AcceuilClientController.eventP.setVisible(false); 
                     AcceuilClientController.actionP.setVisible(false);
                     AcceuilClientController.profileP.setVisible(false);
                         AcceuilClientController.DonsP.setVisible(false);
                         AcceuilClientController.prestationP.setVisible(false);
                         AcceuilClientController.videoP.setVisible(false);
                         AcceuilClientController.affecteDonP.setVisible(false);
                         AcceuilClientController.maisonP.setVisible(false);
                         AcceuilClientController.residentP.setVisible(false);
                         AcceuilClientController.accueilP.setVisible(true);

                break;
            case "boutonEvenement":
                         AcceuilClientController.eventP.setVisible(true); 
                     AcceuilClientController.actionP.setVisible(false);
                     AcceuilClientController.profileP.setVisible(false);
                         AcceuilClientController.DonsP.setVisible(false);
                         AcceuilClientController.prestationP.setVisible(false);
                         AcceuilClientController.videoP.setVisible(false);
                         AcceuilClientController.affecteDonP.setVisible(false);
                         AcceuilClientController.maisonP.setVisible(false);
                         AcceuilClientController.residentP.setVisible(false);
                         AcceuilClientController.accueilP.setVisible(false);

                break;
            case "boutonPrestation":
                               AcceuilClientController.eventP.setVisible(false); 
                     AcceuilClientController.actionP.setVisible(false);
                     AcceuilClientController.profileP.setVisible(false);
                         AcceuilClientController.DonsP.setVisible(false);
                      AcceuilClientController.prestationP.setVisible(true);
                       AcceuilClientController.videoP.setVisible(false);
                         AcceuilClientController.affecteDonP.setVisible(false);
                         AcceuilClientController.maisonP.setVisible(false);
                         AcceuilClientController.residentP.setVisible(false);
                         AcceuilClientController.accueilP.setVisible(false);

                break;
            case "boutonAppelDons":
              AcceuilClientController.eventP.setVisible(false); 
                     AcceuilClientController.actionP.setVisible(false);
                     AcceuilClientController.profileP.setVisible(false);
                         AcceuilClientController.DonsP.setVisible(false);
                         AcceuilClientController.prestationP.setVisible(false);
                          AcceuilClientController.videoP.setVisible(false);
                         AcceuilClientController.affecteDonP.setVisible(true);
                         AcceuilClientController.maisonP.setVisible(false);
                         AcceuilClientController.residentP.setVisible(false);
                         AcceuilClientController.accueilP.setVisible(false);
                 break;
            case "boutonAction":
            AcceuilClientController.eventP.setVisible(false); 
                     AcceuilClientController.actionP.setVisible(true);
                     AcceuilClientController.profileP.setVisible(false);
                         AcceuilClientController.DonsP.setVisible(false);
                         AcceuilClientController.prestationP.setVisible(false);
                          AcceuilClientController.videoP.setVisible(false);
                         AcceuilClientController.affecteDonP.setVisible(false);
                         AcceuilClientController.maisonP.setVisible(false);
                         AcceuilClientController.residentP.setVisible(false);
                         AcceuilClientController.accueilP.setVisible(false);
                break;
            case "boutonVideo":
               AcceuilClientController.eventP.setVisible(false); 
                     AcceuilClientController.actionP.setVisible(false);
                     AcceuilClientController.profileP.setVisible(false);
                         AcceuilClientController.DonsP.setVisible(false);
                          AcceuilClientController.videoP.setVisible(true);
                         AcceuilClientController.affecteDonP.setVisible(false);
                         AcceuilClientController.maisonP.setVisible(false);
                         AcceuilClientController.residentP.setVisible(false);
                         AcceuilClientController.accueilP.setVisible(false);
                 break;
            case "boutonProfil":
                AcceuilClientController.eventP.setVisible(false); 
                     AcceuilClientController.actionP.setVisible(false);
                     AcceuilClientController.profileP.setVisible(true);
                         AcceuilClientController.DonsP.setVisible(false);
                         AcceuilClientController.prestationP.setVisible(false);
                          AcceuilClientController.videoP.setVisible(false);
                         AcceuilClientController.affecteDonP.setVisible(false);
                         AcceuilClientController.maisonP.setVisible(false);
                         AcceuilClientController.residentP.setVisible(false);
                         AcceuilClientController.accueilP.setVisible(false);
                 
                 break;

            
                
        }
    }

}
