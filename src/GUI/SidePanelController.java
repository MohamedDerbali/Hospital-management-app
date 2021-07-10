/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
//import tray.notification.TrayNotification;
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
import javafx.util.Duration;
import javax.swing.JOptionPane;
import sun.java2d.pipe.hw.AccelDeviceEventListener;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class SidePanelController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private Label label_nom_user;
    @FXML
    private Label role;
    @FXML
    private JFXButton boutonAccueil;
    @FXML
    private JFXButton boutonMaison;
    @FXML
    private JFXButton boutonPrestation;
    @FXML
    private JFXButton boutonEvenement;
    @FXML
    private JFXButton boutonAppelDons;
    @FXML
    private JFXButton boutonAction;
    @FXML
    private JFXButton boutonProfil;

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
            case "boutonAccueil":
                         
                         DashBoardController.prestationAdminP.setVisible(false);
                         DashBoardController.accueilAdminP.setVisible(true);
                         DashBoardController.profileAdminP.setVisible(false);

                break;
          
            case "boutonPrestation":
                               
                         DashBoardController.prestationAdminP.setVisible(true);
                         DashBoardController.accueilAdminP.setVisible(false);
                         DashBoardController.profileAdminP.setVisible(false);

                break;
            
            case "boutonProfil":
                
                         DashBoardController.prestationAdminP.setVisible(false);
                         DashBoardController.accueilAdminP.setVisible(false);
                         DashBoardController.profileAdminP.setVisible(true);
                 
                 break;

                
        }
    }

    
}
