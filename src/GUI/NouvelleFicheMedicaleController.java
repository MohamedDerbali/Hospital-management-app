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
import Entities.User;
import static GUI.AcceuilClientController.prestationMap;
import Services.ServiceFicheMedicale;
import Services.ServicePrestationSante;
import com.jfoenix.controls.JFXComboBox;
import com.sun.org.apache.regexp.internal.REUtil;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ahmed
 */
public class NouvelleFicheMedicaleController implements Initializable {

    @FXML
    private Button btn_creer;
  /*  public Label nomResident;
    public Label prenomResident;
    public TextArea description;*/
    @FXML
    private JFXComboBox<Float> niveauTemp;
    @FXML
    private JFXComboBox<Float> niveauSuc;
    
  
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

    public static Resident resident;
      //   public  Map<Integer,List<Fiche_medicale>> prestationMap ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        System.out.println("intialized");    
    
   
        for (double d = 35.0; d<=42; d +=0.1) {//
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
        
   /*    niveauTemp.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    
                    
                            niveauSuc.getSelectionModel().selectedIndexProperty().addListener(
                        (observable2, oldValue2, newValue2) -> {
                            
                            btn_creer.setDisable(false);
                });

                    //btn_creer.setDisable(false);
        });
       */

    }


    @FXML
    public void creerFiche(ActionEvent event) {
         //  System.out.println(prestationMap.containsKey(resident.getId_resident()));
         
         if(niveauTemp.getSelectionModel().selectedIndexProperty().getValue()==-1 ||
            niveauSuc.getSelectionModel().selectedIndexProperty().getValue()==-1 ||
            niveauTension.getSelectionModel().selectedIndexProperty().getValue()==-1 ||
            GroupeSanguin.getSelectionModel().selectedIndexProperty().getValue()==-1 ||
            taille.getSelectionModel().selectedIndexProperty().getValue()==-1 ||
            poids.getSelectionModel().selectedIndexProperty().getValue()==-1){
            
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Certains champs ne sont pas remplis !!");
                        alert.show();
         }
         else{
             fileCreationMethod(event);
         }
      
    }
    
    public void fileCreationMethod(ActionEvent event){
        
         boolean test = false;
         Alert alert = new Alert(AlertType.CONFIRMATION);
         alert.setTitle("Création");
         alert.setHeaderText("Voulez-vous vraiment créér cette fiche ?");
         
         Optional<ButtonType> result = alert.showAndWait();
         
         if(result.get() == ButtonType.OK){
                                Fiche_medicale fiche = new Fiche_medicale();
                    ServiceFicheMedicale sfm = new ServiceFicheMedicale();


                    fiche.setDate__creation_fich(AcceuilClientController.convertUtilToSql());
                    fiche.setDermodif_fich(AcceuilClientController.convertUtilToSql());
                    fiche.setRemarques_fiche(remarqueFiche.getText());
                    fiche.setNiveauTemp(niveauTemp.getSelectionModel().getSelectedItem());
                    fiche.setNiveauSuc(niveauSuc.getSelectionModel().getSelectedItem());
                    fiche.setNiveauTension(niveauTension.getSelectionModel().getSelectedItem());
                    fiche.setGroupeSanguin(GroupeSanguin.getSelectionModel().getSelectedItem());
                    fiche.setMedicaments(medicaments.getText());
                    fiche.setTaille_resident(taille.getSelectionModel().getSelectedItem());
                    fiche.setPoids_resident(poids.getSelectionModel().getSelectedItem());

                  sfm.ajouterFicheMedicale(fiche);

                  ///ajouter une ligne dans la table prestations

                       Prestation_sante ps = new Prestation_sante();

                     ServicePrestationSante sps = new ServicePrestationSante();

                  //recuperer les données de la derniere fiche ajoutée
                      fiche = sfm.listFiche().get(sfm.listFiche().size()-1);

                  if(AcceuilClientController.prestationMap.containsKey(resident.getId_resident())){//if the resident already have a file

                     //docter name (file writer)
                    Prestation_sante prestation = sps.getPrestationRowByIdResident(resident.getId_resident());
                    User user = sps.getUserData(prestation.getId_user());

                    ps.setId_user(user.getId());
                    ps.setId_fiche(fiche.getId_fich());
                    ps.setId_resident( resident.getId_resident());
                    ps.setNom_resident( resident.getNom_resident());
                    ps.setNom_user(ConnexionDB.connectedUser.getNom());
                    ps.setPrenom_user(ConnexionDB.connectedUser.getPrenom());
                    ps.setPrenom_resident( resident.getPrenom_resident());
                    ps.setMedicaments(fiche.getMedicaments());
                    ps.setDate(AcceuilClientController.convertUtilToSql());
                  }
                  else{


                      ps.setId_user(ConnexionDB.connectedUser.getId());
                    ps.setId_fiche(fiche.getId_fich());
                    ps.setId_resident( resident.getId_resident());
                    ps.setNom_resident( resident.getNom_resident());
                    ps.setNom_user(ConnexionDB.connectedUser.getNom());
                    ps.setPrenom_user(ConnexionDB.connectedUser.getPrenom());
                    ps.setPrenom_resident( resident.getPrenom_resident());
                    ps.setMedicaments(fiche.getMedicaments());
                    ps.setDate(AcceuilClientController.convertUtilToSql());
                     // System.out.println("in else block");
                  }

                   // System.out.println(ps);
                    sps.ajouterPrestation(ps);
                    AcceuilClientController.initializePrestationDataMap();//initialiser la map apres modifications
                    AcceuilClientController acc = new AcceuilClientController();//reinitialiser les donnees de la fiche à afficher
                    acc.showResidentFicheData(resident);

                     test = true;
         }
            
    else System.out.println("no");
       
         if(test)
             annuler(event);
    }


    @FXML
    private void annuler(ActionEvent event) {
      //  System.out.println("herreeee "+resident);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
    }

    
    public void initializeData(Resident r/*, Map<Integer,List<Fiche_medicale>> prestationMap*/){
        
     //this.prestationMap = prestationMap;
        
        this.resident = r;
      
                   
       
        nomResidentInFiche.setText(resident.getNom_resident());
       prenomResidentInFiche.setText(resident.getPrenom_resident());
          RoleUser.setText(ConnexionDB.connectedUser.getProfession()+" :");
          nomUserInFiche.setText(ConnexionDB.connectedUser.getNom());
          prenomResidentInFiche.setText(ConnexionDB.connectedUser.getPrenom());
        currDate.setText(LocalDate.now().toString());
        
        //testing if the resident already have other file
        if(AcceuilClientController.prestationMap.containsKey(resident.getId_resident())){
             List<Fiche_medicale> listefiche = prestationMap.get(resident.getId_resident()); //recuperer liste des fiches selon id resident selectionné
            Fiche_medicale f = listefiche.get(listefiche.size()-1);//recuperer la derniere fiche du resident selectionné
            //////
            taille.getSelectionModel().select(f.getTaille_resident());
            poids.getSelectionModel().select(f.getPoids_resident());
            GroupeSanguin.getSelectionModel().select(f.getGroupeSanguin());
        }

        
        
    }


  
  
    
}
