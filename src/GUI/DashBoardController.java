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
import static GUI.AcceuilClientController.maisonP;
//import static GUI.AcceuilClientController.prestationMap;
import Services.ServiceFicheMedicale;
import Services.ServicePrestationSante;
import Services.ServiceResident;
import Services.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class DashBoardController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Button btn_disconnect;
    @FXML
    private ImageView img_disconnect;
    @FXML
    private JFXDrawer drawer;
    public static Pane prestation;
    @FXML
    private Label nomResidentInFiche;
    @FXML
    private Label prenomResidentInFiche;
    @FXML
    private Label tempLevel;
    @FXML
    private Label sucLevel;
    @FXML
    private TableView<?> tabviewResidentToShowFiles;
    @FXML
    private TableColumn<?, ?> nomResidentInTableView;
    @FXML
    private TableColumn<?, ?> prenomResidentInTableView;
    @FXML
    private TableColumn<?, ?> ageResidentInTableView;
    @FXML
    private TableColumn<?, ?> maisonResidentInTableView;
    @FXML
    private Pane addFichePane;
    @FXML
    private JFXComboBox<Integer> ficheId;
    @FXML
    private Label dateInscriptionInFiche;
    @FXML
    private Label RoleUserInFiche;
    @FXML
    private TextArea medicaments;
    @FXML
    private Label nomUserInFiche;
    @FXML
    private Label prenomUserInFiche;
    @FXML
    private Label dateDerModif;
    @FXML
    private Label tensionLevel;
    @FXML
    private Label taille;
    @FXML
    private Label poids;
    @FXML
    private Label groupeSanguin;
    @FXML
    private TextArea remarqueFiche;
     @FXML
    private Button btn_supprimer;
    @FXML
    private TextField researchResidentTextField;
    @FXML
    private Pane prestationAdmin;
    
    public static Pane prestationAdminP;
    
    @FXML
    private TableView<User> tableuser;
    @FXML
    private TableColumn<?, ?> nom_c;
    @FXML
    private TableColumn<?, ?> prenom_c;
    @FXML
    private TableColumn<?, ?> phone_c;
    @FXML
    private TableColumn<?, ?> email_c;
    @FXML
    private TableColumn<?, ?> roles_c;
    @FXML
    private TableColumn<?, ?> status_c;
    @FXML
    private JFXButton sus_btn;
    @FXML
    private TextField chercher_txt;
    @FXML
    private JFXButton act_btn;
    @FXML
    private Pane accueilAdmin;
    @FXML
    private Pane profileAdmin;
    
    public static Pane profileAdminP ;
    public static Pane accueilAdminP;

    /**
     * Initializes the controller class.
     */
    
    // public static Map<Integer,List<Fiche_medicale>> prestationMap = new HashMap<>();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        /****************ahmed ***************************/
        initializeSidePane();
       
        AcceuilClientController.initializePrestationDataMap();
        initializeResidents();
        /**************************************************/
        
        /******************chahir****************************/
        nom_c.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_c.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        phone_c.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email_c.setCellValueFactory(new PropertyValueFactory<>("email"));
        roles_c.setCellValueFactory(new PropertyValueFactory<>("roles"));
        status_c.setCellValueFactory(new PropertyValueFactory<>("statut"));
        ServiceUser su = new ServiceUser();
        ArrayList arraylistU = (ArrayList) su.afficherUserAdmin();
        ObservableList<User> obsU = FXCollections.observableArrayList(arraylistU);

        tableuser.setItems(obsU);

        try {

            if (su.get_user_status_invalid()) {

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Il ya des nouveau comptes invalide a vérifié ");

                tray.showAndDismiss(Duration.seconds(4));
                Search();
            } else {
                System.out.println("");
            }

        } catch (SQLException ex) {
            System.out.println("");
        }

        Search();
        /******************************************************/
        
        
 
    }  
    
    /******************************************************ahmed ***************************************************/

    @FXML
    private void disconnect(ActionEvent event) throws IOException {
        
           Parent page = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void disconnect(MouseEvent event) {
    }


    @FXML
    private void supprimerFiche(ActionEvent event) {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Suppression");
         alert.setHeaderText("Voulez-vous vraiment supprimer cette fiche ?");
         
         Optional<ButtonType> result = alert.showAndWait();
         
         if(result.get() == ButtonType.OK){
                    int idfiche =  ficheId.getSelectionModel().selectedItemProperty().getValue();
               Resident resident = (Resident) tabviewResidentToShowFiles.getSelectionModel().selectedItemProperty().getValue();
               ServiceFicheMedicale sfm = new ServiceFicheMedicale();
               sfm.supprimerFicheMedicale(idfiche);

               ServicePrestationSante sps = new ServicePrestationSante();
               sps.supprimerPrestationByIdFile(idfiche);

               //reinitialize map
               AcceuilClientController.initializePrestationDataMap();
               //reinitialize display
               addFichePane.setVisible(true);
               btn_supprimer.setDisable(true);
               fillFileIdListe(resident);
               ficheId.getSelectionModel().select(-1);     
               tabviewResidentToShowFiles.getSelectionModel().select(-1);
         }
         else System.out.println("no");
        
        
    }
public void initializeSidePane(){
    
        prestationAdminP = prestationAdmin;
        accueilAdminP = accueilAdmin;
        profileAdminP = profileAdmin;
        drawer.open();
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanel.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(1);
        transition.play();
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
        
     
    
}
    
    public void initializeResidents(){
    
          addFichePane.setVisible(true);//hide the file data
    
         fillResidentTabView();//fill the table view on the first time
    
         ServiceResident sr = new ServiceResident();
         
         ArrayList <Resident> lister = (ArrayList <Resident>) sr.listResidentT();
     
         
         //add listner on the researchResidentTextField
          researchResidentTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (tabviewResidentToShowFiles.isVisible() ) {
                if (newValue.isEmpty()) {//if the researchTextField is empty fill the table with all residents
                    
                    

                    ObservableList obs = FXCollections.observableArrayList(lister);
                    tabviewResidentToShowFiles.setItems(obs);
                    nomResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("nom_resident"));
                    prenomResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("prenom_resident"));
                    ageResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("age_resident"));

                    maisonResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("nom_maison"));

                } else {//if not fill the table with residents written in the researchResidentTextField
                    ArrayList<Resident> sortedListResident = new ArrayList<Resident>();

                    lister.stream().forEach((j) -> {

                        if (j.getNom_resident().toString().contains(newValue) || j.getPrenom_resident().toString().contains(newValue)) {
                            sortedListResident.add(j);
                        }

                    });

                    
                    ObservableList obs = FXCollections.observableArrayList(sortedListResident);
                     tabviewResidentToShowFiles.setItems(obs);

                     nomResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("nom_resident"));
                    prenomResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("prenom_resident"));
                    ageResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("age_resident"));

                    maisonResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("nom_maison"));

                    sortedListResident.clear();
                }
            }
          });
         
      
      
        
      // lister.stream().forEach( (j) -> System.out.println(j));
         
       /////
     /* tabview.getSelectionModel().selectFirst();     
  fillFileIdListe(( Resident) tabview.getSelectionModel().selectedItemProperty().getValue());
*/
       ficheId.setDisable(true);
        btn_supprimer.setDisable(true);
        
        tabviewResidentToShowFiles.getSelectionModel().selectedItemProperty().addListener(
                (residentObservable, residentOldValue, residentValue) -> {
                    Resident r = (Resident) residentValue;
                    
                    if(!AcceuilClientController.prestationMap.containsKey(r.getId_resident())){//if the resident dont have a file 
                        ficheId.setDisable(true);
                    }
                    else ficheId.setDisable(false);
                        
                    addFichePane.setVisible(true);//hide the file data
                    btn_supprimer.setDisable(true);//disable delete button befor selecting a file id 
                    fillFileIdListe((Resident) residentValue);
                    
                        ficheId.getSelectionModel().selectedItemProperty().addListener(
                           (fileobservable, fileoldValue, fileValue) -> {

                      showFileData( (int) fileValue, (Resident) residentValue);
                      addFichePane.setVisible(false);
                      btn_supprimer.setDisable(false);
                      });

        });
        
        
         
       
       
    
}
    public void fillResidentTabView(){
     ServiceResident sr = new ServiceResident();
         
         ArrayList <Resident> lister = (ArrayList <Resident>) sr.listResidentT();
             
         ObservableList obs = FXCollections.observableArrayList(lister);
         tabviewResidentToShowFiles.setItems(obs);
         //tabview.setItems(obm);
         nomResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("nom_resident"));
         prenomResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("prenom_resident"));
         ageResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("age_resident"));
         
         maisonResidentInTableView.setCellValueFactory(new PropertyValueFactory<>("nom_maison"));
}
/*public static void initializePrestationDataMap(){
    
    ServicePrestationSante sps = new ServicePrestationSante();
    
    List<Prestation_sante> liste_prestation = sps.listPrestation();
   // liste_prestation.stream().forEach( (j) -> System.out.println(j));
    AcceuilClientController.prestationMap.clear();
    liste_prestation.stream().forEach( (j) -> {
            int id = j.getId_resident();
            String nom = j.getNom_resident();
            String prenom = j.getPrenom_resident();
            
            Resident resident = new Resident();
            List<Fiche_medicale> listefiche = sps.getListFicheByIdResdient(id);
            
            resident.setId_resident(id);
            resident.setNom_resident(nom);
            resident.setPrenom_resident(prenom);
            
           //System.out.println("while filling the map :"+listefiche);
            
           AcceuilClientController.prestationMap.put(id, listefiche);
            //listefiche.stream().forEach((f)-> System.out.println(f));
    });
     // prestationMap.keySet().stream().forEach(System.out::println);
     System.out.println("map reinitilized !!");
    
}*/
    
public void fillFileIdListe(Resident resident){
    
    
    ficheId.getItems().clear();
    
       // prestationMap.keySet().stream().forEach(System.out::println);
 List<Fiche_medicale> listefiche = AcceuilClientController.prestationMap.get(resident.getId_resident()); //recuperer liste des fiches selon id resident selectionné
 
        listefiche.stream().forEach((f)->{
                ficheId.getItems().add(f.getId_fich());
        });
        
        //listefiche.stream().forEach((f)-> System.out.println(f));
       // System.out.println("tesssssssssssyt "+listefiche);
        
        
    }
public void showFileData(int id_fiche ,Resident resident){
    
    if(AcceuilClientController.prestationMap.containsKey(resident.getId_resident())){//if the resident already have a file
        addFichePane.setVisible(false);//show the file data pane
         btn_supprimer.setDisable(false);//show the delete  utton
        ServiceFicheMedicale sfm = new ServiceFicheMedicale();
     Fiche_medicale f = sfm.getFicheDataById(id_fiche);
     //////
        
       // System.out.println(f);
     
        nomResidentInFiche.setText(resident.getNom_resident());
        prenomResidentInFiche.setText(resident.getPrenom_resident());
        dateDerModif.setText(f.getDermodif_fich().toString());
        medicaments.setText(f.getMedicaments());
       // medicaments.setDisable(true);
        taille.setText(Float.toString((float) f.getTaille_resident())+" cm");
        poids.setText(Float.toString((float) f.getPoids_resident())+" kg");
        groupeSanguin.setText(f.getGroupeSanguin());
        tempLevel.setText(Float.toString((float) f.getNiveauTemp())+" °");
        sucLevel.setText(Float.toString((float) f.getNiveauSuc())+" g");
        tensionLevel.setText(Float.toString((float) f.getNiveauTension())+" cmHg");//centimètres de mercure
        remarqueFiche.setText(f.getRemarques_fiche());
       // remarqueFiche.setDisable(true);
        
        //docter name (file writer)
         ServicePrestationSante sps = new ServicePrestationSante();
        Prestation_sante ps = sps.getPrestationRowByIdResident(resident.getId_resident());
        User u = sps.getUserData(ps.getId_user());
        
        nomUserInFiche.setText(u.getNom());
        prenomUserInFiche.setText(u.getPrenom());
        RoleUserInFiche.setText(ConnexionDB.connectedUser.getProfession()+" :");
        
    }
       else   {
        System.out.println("noo file !!");
        //addFichePane.setVisible(true);
    }        
}
/*****************************************************************************************************************/

/********************************************** chahir *********************************************************/


    public void Search() {
        ServiceUser su = new ServiceUser();
        ArrayList arraylist = (ArrayList) su.afficherUserAdmin();
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        tableuser.setItems(obs);

        FilteredList<User> filteredData = new FilteredList<>(obs, p -> true);
        chercher_txt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((User user) -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (user.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.

                } else if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (user.getRoles().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (user.getStatut().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<User> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableuser.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableuser.setItems(sortedData);
    }

    @FXML
    private void suspendUserAction(ActionEvent event) {

        ServiceUser su = new ServiceUser();
        User u = (User) (tableuser.getSelectionModel().getSelectedItem());

        su.suspend_user(u.getId());
        
        ArrayList arraylist = (ArrayList) su.afficherUserAdmin();
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        tableuser.setItems(obs);
        Search();

        
        Search();

    }

    @FXML
    private void activeUserAction(ActionEvent event) throws IOException, NexmoClientException {

        ServiceUser su = new ServiceUser();
        User u = (User) (tableuser.getSelectionModel().getSelectedItem());

        su.active_user(u.getId());
        sendSMS();

        ArrayList arraylist = (ArrayList) su.afficherUserAdmin();
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        tableuser.setItems(obs);
        Search();

        Search();
    }


    public void sendSMS() throws IOException, NexmoClientException {
        
        
        User u = (User) (tableuser.getSelectionModel().getSelectedItem());
        String nbphone = "216"+String.valueOf(u.getPhone());
        
        System.out.println(nbphone);
        NexmoClient client = new NexmoClient.Builder()
                .apiKey("ba8ff2bf")
                .apiSecret("Tux2U0JVKTQyZgGN")
                .build();
        String ch = u.getEmail();
        String messageText = "votre compte :"+ch+"a été activé avec succès";
        TextMessage message = new TextMessage("Happyold", nbphone, messageText);

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
            System.out.println(responseMessage);
        }

    }

/*****************************************************************************************************************/
    
}
