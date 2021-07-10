/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Config.ConnexionDB;
import Entities.Fiche_medicale;
import Entities.Prestation_sante;
import Entities.Resident;
import Services.ServiceFicheMedicale;
import Services.ServicePrestationSante;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class FicheMedicaleController implements Initializable {

    
    public static Button btn_creer2;
    private Button btn_modif;

    @FXML
    private JFXComboBox<Float> niveauTemp;
   // private JFXComboBox<Float> niveauSec;
     
  
    @FXML
    private Label RoleUser;
    @FXML
    private Label nomUserInFiche;
    @FXML
    private Label prenomUserInFiche;
    @FXML
    private Label nomResidentInFiche;
    @FXML
    private Label prenomResidentInFiche;
    @FXML
    private JFXComboBox<Float> taille;
    @FXML
    private JFXComboBox<Float> niveauSuc;
    @FXML
    private JFXComboBox<Float> niveauTension;
    @FXML
    private JFXComboBox<String> GroupeSanguin;
    @FXML
    private JFXComboBox<Float> poids;
    @FXML
    private Label dateInscription;
    @FXML
    private TextArea medicaments;
    @FXML
    private TextArea remarqueFiche;
    @FXML
    private Label currDate;
    @FXML
    private Label nomMaisonInFiche;

    /**
     * Initializes the controller class.
     */
    
      public Resident resident;
   //  public  Map<Integer,List<Fiche_medicale>> prestationMap ;
    public Fiche_medicale fiche;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
      
        for (double d = 35.0; d<=42.0; d +=0.1) {//
     niveauTemp.getItems().add((float)d);
        }
        
        for (double d = 0.70; d<=1.40; d +=0.01) {//
      niveauSuc.getItems().add((float)d);
        }
        
        for (double d = 10.8; d<=25; d +=0.1) {
            niveauTension.getItems().add((float)d);
        }
         for (double d = 150.0; d<=190.0; d ++) {//
            taille.getItems().add((float)d);
        }
         
         for (double d = 50.0; d<=120.0; d +=0.1) {//
            poids.getItems().add((float)d);
        }

        
        GroupeSanguin.getItems().add("A+");
        GroupeSanguin.getItems().add("A-");
        GroupeSanguin.getItems().add("B+");
        GroupeSanguin.getItems().add("B-");
        GroupeSanguin.getItems().add("AB+");
        GroupeSanguin.getItems().add("AB-");
        GroupeSanguin.getItems().add("O+");
        GroupeSanguin.getItems().add("O-");
        
        //listener on the first and the second combobox
        
       niveauTemp.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    
                   
                            niveauSuc.getSelectionModel().selectedIndexProperty().addListener(
                        (observable2, oldValue2, newValue2) -> {
                            
                            btn_modif.setDisable(false);
                });

                    //btn_creer.setDisable(false);
        });
        
        
    }    




    @FXML
    private void modifierFiche(ActionEvent event) {
        
        boolean test = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Modification");
         alert.setHeaderText("Voulez-vous vraiment modifier cette fiche ?");
         
         Optional<ButtonType> result = alert.showAndWait();
         
         if(result.get() == ButtonType.OK){
             
                        Fiche_medicale fm = new Fiche_medicale();
                         ServiceFicheMedicale sfm = new ServiceFicheMedicale();
                      System.out.println(fiche);
                          fiche.setDermodif_fich(AcceuilClientController.convertUtilToSql());
                          fiche.setRemarques_fiche(remarqueFiche.getText());
                          fiche.setNiveauTemp(niveauTemp.getSelectionModel().getSelectedItem());
                          fiche.setNiveauSuc(niveauSuc.getSelectionModel().getSelectedItem());
                          fiche.setNiveauTension(niveauTension.getSelectionModel().getSelectedItem());
                          fiche.setGroupeSanguin(GroupeSanguin.getSelectionModel().getSelectedItem());
                          fiche.setMedicaments(medicaments.getText());
                          fiche.setTaille_resident(taille.getSelectionModel().getSelectedItem());
                          fiche.setPoids_resident(poids.getSelectionModel().getSelectedItem());

                        System.out.println(fiche); 
                          sfm.modifierFicheMedicale(fiche);

                    ///modifier la table prestation
                    ServicePrestationSante sps = new ServicePrestationSante();
                    Prestation_sante prestation = sps.getPrestationRowByIdFiche(fiche.getId_fich());
                    prestation.setMedicaments(fiche.getMedicaments());

                    sps.modifierPrestation(prestation);


                  AcceuilClientController.initializePrestationDataMap();//initialiser la map apres modifications
                  AcceuilClientController acc = new AcceuilClientController();//reinitialiser les donnees de la fiche à afficher
                  acc.showResidentFicheData(resident);
                  
                  test = true;

                     
         }
         else System.out.println("no");
        
         //if(test == true)
            annuler(event);
         
         System.out.println(test);
                
        
    }
    
      @FXML
    private void annuler(ActionEvent event) {
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
    }

    void initializeData(Resident r/*, Map<Integer,List<Fiche_medicale>> prestationMap*/) {
        
         // AcceuilClientController.prestationMap = prestationMap;
        this.resident = r;
         Fiche_medicale f = new Fiche_medicale();
          List<Fiche_medicale> listefiche = AcceuilClientController.prestationMap.get(resident.getId_resident()); //recuperer liste des fiches selon id resident selectionné
      f = listefiche.get(listefiche.size()-1);//recuperer la derniere fiche du resident selectionné
     
          this.fiche=f;
          System.out.println("efezfze");
        
        nomResidentInFiche.setText(resident.getNom_resident());
        prenomResidentInFiche.setText(resident.getPrenom_resident());
        nomMaisonInFiche.setText(resident.getNom_maison());
         medicaments.setText(f.getMedicaments());
        remarqueFiche.setText(f.getRemarques_fiche());
        //dateInscription.setText(resident.getDate());
        
        currDate.setText(LocalDate.now().toString());
         GroupeSanguin.getSelectionModel().select(f.getGroupeSanguin());
         taille.getSelectionModel().select(f.getTaille_resident());
        poids.getSelectionModel().select(f.getPoids_resident()); 
        niveauSuc.getSelectionModel().select(f.getNiveauSuc());
        niveauTemp.getSelectionModel().select(f.getNiveauTemp());
        niveauTension.getSelectionModel().select(f.getNiveauTension());
        RoleUser.setText(ConnexionDB.connectedUser.getProfession());
       
        
        
    }



  


    
}
