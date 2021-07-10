/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Config.ConnexionDB;
import Entities.Action;
import Entities.Demande;
import Entities.Don;
import Entities.Evenement;
import Entities.Fiche_medicale;
import Entities.Maison;
import Entities.Prestation_sante;
import Entities.Resident;
import Entities.User;
import Services.GenererFichePDF;
import Services.ServiceAction;
import Services.ServiceDemande;
import Services.ServiceDon;
import Services.ServiceEvenement;
import Services.ServiceFicheMedicale;
import Services.ServiceMaison;
import Services.ServicePrestationSante;
import Services.ServiceResident;
import Services.ServiceUser;
import Services.SmtpAuthenticator;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author YAFET
 */
public class AcceuilClientController implements Initializable {

    @FXML
    private Button btn_disconnect;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Pane maison;
    

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Pane accueil;
    @FXML
    private Pane event;
    @FXML
    private Pane profile;
    @FXML
    private Pane action;
    @FXML
    private Pane Dons;
    @FXML
    private ImageView img_disconnect;
    @FXML
    private Pane prestation;
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
    private Label nomResidentInFiche;
    @FXML
    private Label prenomResidentInFiche;
    private Label dateFiche;
    @FXML
    private Label tempLevel;
    @FXML
    private Label sucLevel;
    
    @FXML
    private Button btn_creerFiche;
    @FXML
    private Pane addFichePane;
    @FXML
    private Label dateInscriptionInFiche;
    @FXML
    private Label RoleUserInFiche;
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
    private TextArea medicaments;
    
     public static  Map<Integer,List<Fiche_medicale>> prestationMap = new HashMap<>();
    @FXML
    private Button modifyButton;
    @FXML
    private Pane fichePane;
    @FXML
    private ImageView refreshFile;
    @FXML
    private TextField researchResidentTextField;
    @FXML
    private TextField idnom;
    @FXML
    private TextField idadresse;
    @FXML
    private TextField idheure;
    @FXML
    private TextField iddescription;
    @FXML
    private DatePicker iddated;
    @FXML
    private DatePicker iddatef;
    @FXML
    private TableView<?> idtable;
    @FXML
    private TableColumn<?, ?> iddatedd;
    @FXML
    private TableColumn<?, ?> iddateff;
    @FXML
    private TableColumn<?, ?> idheuree;
    @FXML
    private TableColumn<?, ?> idnomm;
    @FXML
    private TableColumn<?, ?> idadressee;
    @FXML
    private TableColumn<?, ?> iddescriptionn;
    @FXML
    private Button idsupprimer;
    @FXML
    private Button idmodifier;
    @FXML
    private TextField idrechercherEvent;
    @FXML
    private TextField idrechercheradresse;
    @FXML
    private Button idenregistrer;
    @FXML
    private TextField description_action;
    @FXML
    private Button ajouter_action;
    @FXML
    private DatePicker debut;
    @FXML
    private DatePicker fin;
    @FXML
    private TableView<Action> idtable_action;
    @FXML
    private TableColumn<?, ?> idType;
    @FXML
    private TableColumn<?, ?> idDebut;
    @FXML
    private TableColumn<?, ?> idFin;
    @FXML
    private TableColumn<?, ?> idDescription;
    @FXML
    private Button supprimer_action;
    @FXML
    private ChoiceBox<String> type_picker;
    @FXML
    private Button modifier;
      MediaPlayer mediaplayer;
    MediaPlayer mediaplayer1;
    MediaPlayer mediaplayer2;
     MediaPlayer mediaplayer3;
    @FXML
    private Pane video;
    @FXML
    private MediaView mv;
    @FXML
    private Button btn_play;
    @FXML
    private Button btn_stop;
    @FXML
    private MediaView mv1;
    @FXML
    private Button play1;
    @FXML
    private Button stop1;
    @FXML
    private Button play2;
    @FXML
    private Button stop2;
    @FXML
    private MediaView mv2;
    @FXML
    private MediaView mv3;
    @FXML
    private Button play3;
    @FXML
    private Button stop3;
    
      ObservableList options = FXCollections.observableArrayList();

    ObservableList<String> listCategories = FXCollections
            .observableArrayList("medicaments", "vetements", "argant", "nourriture");

    ObservableList<String> listCategoriesDon = FXCollections
            .observableArrayList("medicament", "Vetement", "nourriture", "argant");
    @FXML
    private TextField description_demande;
    @FXML
    private Button btn_ajouter;
    @FXML
    private ComboBox liste_categorie;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private TableView<?> tab_view;
    @FXML
    private TableColumn<?, ?> tab_categorie;
    @FXML
    private TableColumn<?, ?> tab_quantite;
    @FXML
    private TableColumn<?, ?> tab_descrip;
    @FXML
    private ComboBox nom_maison;
    @FXML
    private Spinner spinner_quantite_demande;
    @FXML
    private Label label_description;
    @FXML
    private Pane affecteDon;
    @FXML
    private TableView tab_view1;
    @FXML
    private TableColumn<?, ?> tab_categorie1;
    @FXML
    private TableColumn<?, ?> tab_quantite1;
    @FXML
    private TableColumn<?, ?> tab_descrip1;
    @FXML
    private TableColumn<?, ?> id_demande;
    @FXML
    private TextField descrip_don;
    @FXML
    private Button ajoutdon;
    @FXML
    private TextField demande;
    @FXML
    private BarChart<?, ?> stat_dons;
    @FXML
    private NumberAxis y_quantite;
    @FXML
    private CategoryAxis x_categorie;
    @FXML
    private ComboBox categorie_don;
    @FXML
    private TextField chercher_demande;
    @FXML
    private Spinner quantite_spinner;
    @FXML
    private Button btn_chercher;
    
     private final String username= "yafet.shil@esprit.tn" ;
private final String password = "alnbinvkmdtmyoox" ;
private final int initialValue=50;
   SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 100, initialValue);
    @FXML
    private TextField nom_maison1;
    @FXML
    private TextField adresse_maison;
    @FXML
    private TextField telephone_maison;
    @FXML
    private TextField mail_maison;
    @FXML
    private TableView<?> table_maison;
    @FXML
    private TableColumn<?, ?> cnom_maison;
    @FXML
    private TableColumn<?, ?> cadresse_maison;
    @FXML
    private TableColumn<?, ?> ctelephone_maison;
    @FXML
    private TableColumn<?, ?> cmail_maison;
    @FXML
    private TableColumn<?, ?> cpersonne_maison;
    @FXML
    private TableColumn<?, ?> chomme_maison;
    @FXML
    private TableColumn<?, ?> cfemme_maison;
    @FXML
    private TableColumn<?, ?> calzheimer_maison;
    @FXML
    private ImageView adresse_error;
    @FXML
    private Pane resident;
       

    @FXML
    private Label nomResident;
    @FXML
    private Label prenomResident;
    @FXML
    private Label date;
    @FXML
    private Button btn_annuler;
    @FXML
    private TextField nom_resident;
    @FXML
    private TextField prenom_resident;
    @FXML
    private Spinner<Integer> age_resident = new Spinner<Integer>();
    @FXML
    private TextField maladie_resident;
    @FXML
    private ComboBox<?> sexe_resident;
    @FXML
    private ComboBox<?> alzheimer_resident;
    @FXML
    private DatePicker date_ajout;
    @FXML
    private Label date1;
    @FXML
    private TableView<?> table_resident;
    @FXML
    private TableColumn<?, ?> cnom_resident;
    @FXML
    private TableColumn<?, ?> cprenom_resident;
    @FXML
    private TableColumn<?, ?> cage_resident;
    @FXML
    private TableColumn<?, ?> cdate_resident;
    @FXML
    private TableColumn<?, ?> csexe_resident;
    @FXML
    private TableColumn<?, ?> calzheimer_resident;
    @FXML
    private TableColumn<?, ?> cmaladie_resident;
    @FXML
    private Text unom_txt;
    @FXML
    private Text uprenom_txt;
    @FXML
    private Text uemail_txt;
    @FXML
    private Text uadresse_txt;
    @FXML
    private Text uphone_txt;
    @FXML
    private PasswordField mdp_txf;
    @FXML
    private TextField uemail_txf;
    @FXML
    private TextField uadresse_txf;
    @FXML
    private TextField uphone_txf;
    @FXML
    private Pane hideModifPane;
    
    
     public User entredUser;
    
      public static Pane accueilP;
    public static Pane eventP;
    public static Pane profileP;
    public static Pane actionP;
   public static Pane DonsP;
    public static Pane prestationP;
    public static Pane affecteDonP;
    public static Pane videoP;
     public static Pane residentP;
         public static Pane maisonP;
    



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
      /******ahmed : prestation santé *************/  
        initializePrestationDataMap();
        this.initializeSidePane();
         this.initializeResidents();
      /********************************************/
      
      /*****sabrine : evenement *****************/
      
              ServiceEvenement se = new ServiceEvenement();
        ArrayList arraylist = (ArrayList) se.listEvenement();
        ObservableList obs = FXCollections.observableArrayList(arraylist);

        idtable.setItems(obs);

        iddatedd.setCellValueFactory(new PropertyValueFactory<>("date_d_evenement"));
        iddateff.setCellValueFactory(new PropertyValueFactory<>("date_f_evenement"));
        idheuree.setCellValueFactory(new PropertyValueFactory<>("heure_evenement"));
        idnomm.setCellValueFactory(new PropertyValueFactory<>("nom_evenement"));
        idadressee.setCellValueFactory(new PropertyValueFactory<>("adresse_evenement"));
        iddescriptionn.setCellValueFactory(new PropertyValueFactory<>("description_evenement"));
      /******************************************/
      
      /**************ghada : action *************/
       
      ServiceAction ms = new ServiceAction();
        ArrayList arraylistG = (ArrayList) ms.listAction();
        
        ObservableList obsG = FXCollections.observableArrayList(arraylistG);
        idtable_action.setItems(obsG);
        idType.setCellValueFactory(new PropertyValueFactory<>("type_action"));
        idDebut.setCellValueFactory(new PropertyValueFactory<>("date_d_action"));
        idFin.setCellValueFactory(new PropertyValueFactory<>("date_f_action"));
        idDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));

      
        type_picker.getItems().add("santé");
        type_picker.getItems().add("Loisirs");
        type_picker.getItems().add("Divertissement");

        String VUrl = "file:/C:/Users/ahmed/Desktop/vid/documentaire.mp4";

        Media media = new Media(VUrl);
        mediaplayer = new MediaPlayer(media);
        mv.setFitHeight(200);
        mv.setFitWidth(400);
        mv.setMediaPlayer(mediaplayer);
        String VUrl1 = "file:/C:/Users/ahmed/Desktop/vid/actualité.mp4";
        Media media1 = new Media(VUrl1);
        mediaplayer1 = new MediaPlayer(media1);
        mv1.setFitHeight(200);
        mv1.setFitWidth(400);
        mv1.setMediaPlayer(mediaplayer1);
        String VUrl2 = "file:/C:/Users/ahmed/Desktop/vid/art.mp4";
        Media media2 = new Media(VUrl2);
        mediaplayer2 = new MediaPlayer(media2);
        mv2.setFitHeight(200);
        mv2.setFitWidth(400);
        mv2.setMediaPlayer(mediaplayer2);
        String VUrl3 = "file:/C:/Users/ahmed/Desktop/vid/bio.mp4";
        Media media3 = new Media(VUrl3);
        mediaplayer3 = new MediaPlayer(media3);
        mv3.setFitHeight(200);
        mv3.setFitWidth(400);
        mv3.setMediaPlayer(mediaplayer3);
      /**********************************************************/
      
      /***********************wassim : don **********************/
       ConnexionDB.getInstance();
        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://localhost:3306/pidev";

        // Quantite Vetement
        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            //options= FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            //ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM maison;");
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT quantite_don FROM don WHERE categorie_don='Vetement'");

            while (rs.next()) {
                int QuantiteVetement = rs.getInt("quantite_don");
                System.out.println(QuantiteVetement);
                XYChart.Series set1 = new XYChart.Series<>();
                set1.getData().add(new XYChart.Data("vetement", QuantiteVetement));

                stat_dons.getData().addAll(set1);

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);

        }
        //Quantite nourriture
        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            //options= FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            //ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM maison;");
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT quantite_don FROM don WHERE categorie_don='nourriture'");

            while (rs.next()) {
                int QuantiteNourriture = rs.getInt("quantite_don");
                System.out.println(QuantiteNourriture);
                XYChart.Series set1 = new XYChart.Series<>();
                set1.getData().add(new XYChart.Data("Nourriture", QuantiteNourriture));

                stat_dons.getData().addAll(set1);

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);

        }
        //Quantite Medicament
        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            //options= FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            //ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM maison;");
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT quantite_don FROM don WHERE categorie_don='medicament'");

            while (rs.next()) {
                int QuantiteMedicament = rs.getInt("quantite_don");
                System.out.println(QuantiteMedicament);
                XYChart.Series set1 = new XYChart.Series<>();
                set1.getData().add(new XYChart.Data("Medicament", QuantiteMedicament));

                stat_dons.getData().addAll(set1);

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);

        }

        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            options = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM maison;");
            while (rs.next()) {
                //get string from db,whichever way 
                String s = rs.getString("nom_maison");
                options.add(s);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        nom_maison.setItems(null);

        nom_maison.setItems(options);

       
        liste_categorie.setValue("medicaments");
        liste_categorie.setItems(listCategories);

        categorie_don.setValue("Vetement");
        categorie_don.setItems(listCategoriesDon);
        SpinnerValueFactory<Integer> i = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 1000, 10);
        this.quantite_spinner.setValueFactory(i);
        this.spinner_quantite_demande.setValueFactory(i);
        // TODO
        ServiceDemande sd = new ServiceDemande();
        ArrayList arraylistDon = (ArrayList) sd.listeDemande3();
        label_description.setText("");

        //observabale list pour mettre la liste 
        ObservableList obsDons = FXCollections.observableArrayList(arraylistDon);
        tab_view.setItems(obsDons);
        //tab_nom.setCellValueFactory(new PropertyValueFactory<>("tab_nom"));
        //tab_prenom.setCellValueFactory(new PropertyValueFactory<>("tab_prenom"));
        tab_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie_demande"));
        tab_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite_demande"));
        tab_descrip.setCellValueFactory(new PropertyValueFactory<>("description_demande"));

        // TODO
        ObservableList options = FXCollections.observableArrayList();

        ServiceDemande sdd = new ServiceDemande();
        ArrayList arraylist1 = (ArrayList) sdd.listeDemande2();

        //observabale list pour mettre la liste 
        ObservableList obss = FXCollections.observableArrayList(arraylist1);
        tab_view1.setItems(obss);

        //tab_nom.setCellValueFactory(new PropertyValueFactory<>("tab_nom"));
        //tab_prenom.setCellValueFactory(new PropertyValueFactory<>("tab_prenom"));
        // tab_Nom_maision.setCellValueFactory(new PropertyValueFactory("id_maison"));
        tab_categorie1.setCellValueFactory(new PropertyValueFactory<>("categorie_demande"));
        tab_quantite1.setCellValueFactory(new PropertyValueFactory<>("quantite_demande"));
        tab_descrip1.setCellValueFactory(new PropertyValueFactory<>("description_demande"));
        id_demande.setCellValueFactory(new PropertyValueFactory<>("id_demande"));

        cetCallValueFromTableToTextField();
        cetCallValueFromTableToTextField2();
        
        //test description value
      /**********************************************************/
      
       /***********************yafet : maison ***************/
       
    // TODO
        /* rootP = root;
        acceuilP = accueil;
        profileP = profile;
        eventP = event;
        maisonP = maison;
        residentP=resident;
        actionP=actionn;
        donP=Dons;*/
        maisonP = maison;
        residentP=resident;
        String [] p = {"Tunis","Le Bardo","Le Kram","La Goulette","Carthage","Sidi Bou Said","La Marsa","Sidi Hassine","Ariana","La Soukra","Raoued","Kalâat el-Andalous","Sidi Thabet","Ettadhamen-Mnihla","Ben Arous","El Mourouj","Hammam Lif","Hammam Chott","Bou Mhel el-Bassatine","Ezzahra","Radès","Mégrine","Mohamedia-Fouchana","Mornag","Khalidia","Manouba","Den Den","Douar Hicher","Oued Ellil","Mornaguia","Borj El Amri","Djedeida","Tebourba","El Battan","Nabeul","Dar Chaabane","Béni Khiar","El Maâmoura","Somâa","Korba","Tazerka","Menzel Temime","Menzel Horr","El Mida","Kelibia","Azmour","Hammam Ghezèze","Dar Allouch","El Haouaria","Takelsa","Soliman","Korbous","Menzel Bouzelfa","Béni Khalled","Zaouiet Djedidi","Grombalia","Bou Argoub","Hammamet","Zaghouan","Zriba","Bir Mcherga","Djebel Oust","El Fahs","Nadhour","Bizerte","Sejnane","Mateur","Menzel Bourguiba","Tinja","Ghar al Milh","Aousja","Menzel Jemil","Menzel Abderrahmane","El Alia","Ras Jebel","Metline","Raf Raf","Béja","El Maâgoula","Zahret Medien","Nefza","Téboursouk","Testour","Goubellat","Majaz al Bab","Jendouba","Bou Salem","Tabarka","Aïn Draham","Fernana","Beni M'Tir","Ghardimaou","Oued Melliz","Kef","Nebeur","Touiref","Sakiet Sidi Youssef","Tajerouine","Menzel Salem","Kalaat es Senam","Kalâat Khasba","Jérissa","El Ksour","Dahmani","Sers","Siliana","Bou Arada","Gaâfour","El Krib","Sidi Bou Rouis","Maktar","Rouhia","Kesra","Bargou","El Aroussa","Sousse","Ksibet Thrayet","Ezzouhour","Zaouiet Sousse","Hammam Sousse","Akouda","Kalâa Kebira","Sidi Bou Ali","Hergla","Enfidha","Bouficha","Sidi El Hani","M'saken","Kalâa Seghira","Messaadine","Kondar","Monastir","Khniss","Ouerdanin","Sahline Moôtmar","Sidi Ameur","Zéramdine","Beni Hassen","Ghenada","Jemmal","Menzel Kamel","Zaouiet Kontoch","Bembla-Mnara","Menzel Ennour","El Masdour","Moknine","Sidi Bennour","Menzel Farsi","Amiret El Fhoul","Amiret Touazra","Amiret El Hojjaj","Cherahil","Bekalta","Téboulba","Ksar Hellal","Ksibet El Mediouni","Benen Bodher","Touza","Sayada","Lemta","Bouhjar","Menzel Hayet","Mahdia","Rejiche","Bou Merdes","Ouled Chamekh","Chorbane","Hebira","Essouassi","El Djem","Kerker","Chebba","Melloulèche","Sidi Alouane","Ksour Essef","El Bradâa","Sfax","Sakiet Ezzit","Chihia","Sakiet Eddaïer","Gremda","El Ain","Thyna","Agareb","Jebiniana","El Hencha","Menzel Chaker","Ghraïba","Bir Ali Ben Khélifa","Skhira","Mahares","Kerkennah","Kairouan","Chebika","Sbikha","Oueslatia","Aïn Djeloula","Haffouz","Alaâ","Hajeb El Ayoun","Nasrallah","Menzel Mehiri","Echrarda","Bou Hajla","Kasserine","Sbeitla","Sbiba","Jedelienne","Thala","Haïdra","Foussana","Fériana","Thélepte","Magel Bel Abbès","Sidi Bouzid","Jilma","Cebalet","Bir El Hafey","Sidi Ali Ben Aoun","Menzel Bouzaiane","Meknassy","Mezzouna","Regueb","Ouled Haffouz","Gabès","Chenini Nahal","Ghannouch","Métouia","Oudhref","El Hamma","Matmata","Nouvelle Matmata","Mareth","Zarat","Mednine","Beni Khedache","Ben Gardane","Zarzis","Houmt El Souk","Midoun","Ajim","Tataouine","Bir Lahmar","Ghomrassen","Dehiba","Remada","Gafsa","El Ksar","Moularès","Redeyef","Métlaoui","Mdhila","El Guettar","Sened","Tozeur","Degache","Hamet Jerid","Nafta","Tamerza","Kebili","Djemna","Douz","El Golâa","Souk Lahad"	

};
TextFields.bindAutoCompletion(adresse_maison, p);
Maison m=new Maison();


//affichage maison
    ServiceMaison sm = new ServiceMaison();
        ArrayList arraylistM = (ArrayList) sm.listMaison();
        ObservableList obsM = FXCollections.observableArrayList(arraylistM);
        table_maison.setItems(obsM);
        cnom_maison.setCellValueFactory(new PropertyValueFactory<>("nom_maison"));
        cadresse_maison.setCellValueFactory(new PropertyValueFactory<>("adresse_maison"));
                ctelephone_maison.setCellValueFactory(new PropertyValueFactory<>("telephone_maison"));
                cmail_maison.setCellValueFactory(new PropertyValueFactory<>("mail_maison"));
               // cpersonne_maison.setCellValueFactory(new PropertyValueFactory<>("nbr_personne"));
              //  chomme_maison.setCellValueFactory(new PropertyValueFactory<>("nbr_personne"));

//affichage resident
ServiceResident sr = new ServiceResident();
        ArrayList arraylist1R = (ArrayList) sr.listResident(m);
        ObservableList obs1R = FXCollections.observableArrayList(arraylist1R);
        table_resident.setItems(obs1R);
        cnom_resident.setCellValueFactory(new PropertyValueFactory<>("nom_resident"));
        cprenom_resident.setCellValueFactory(new PropertyValueFactory<>("prenom_resident"));
                cage_resident.setCellValueFactory(new PropertyValueFactory<>("age_resident"));
                cdate_resident.setCellValueFactory(new PropertyValueFactory<>("date_resident"));
                            csexe_resident.setCellValueFactory(new PropertyValueFactory<>("sexe_resident"));
                    calzheimer_resident.setCellValueFactory(new PropertyValueFactory<>("alzheimer_resident"));
                cmaladie_resident.setCellValueFactory(new PropertyValueFactory<>("maladie_resident"));

        
      
        //initialize list sexe
        ObservableList sexe = FXCollections.observableArrayList( 
 "Homme", "Femme");
sexe_resident.setItems(sexe);
//initialize liste alzheimer
ObservableList alzheimer = FXCollections.observableArrayList( 
 "Oui", "Non");
alzheimer_resident.setItems(alzheimer);
// initialize spinner age
 
        age_resident.setValueFactory(valueFactory);
                date_ajout.setValue(LocalDate.now());
                
    /******************************************************/
    
    /****************** chahir : user ************************/
    
         hideModifPane.setVisible(true);
        try {
            profilePaneInit();
            /***********************************************************/
            
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    
/************************************** ahmed : prestation santé *************************************************/
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
    private void modifierFiche(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FicheMedicale.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        //*****send data to FicheController******//
    FicheMedicaleController fc = loader.getController();
       
    fc.initializeData((Resident) tabviewResidentToShowFiles.getSelectionModel().selectedItemProperty().getValue()/*,prestationMap*/);
           fichePane.setVisible(true);//show the file picture insted of file information
           refreshFile.setVisible(true);//hide refresh button

           

    //***************************************************//



    }


    @FXML
    private void creerFiche(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("NouvelleFicheMedicale.fxml"));
  try{
       
        Stage stage = new Stage();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        

  }catch(IOException ex){
      System.err.println(ex.getMessage());
  }
  //send data to nouvelleFicheController
    NouvelleFicheMedicaleController nfc = loader.getController();

    //nfc.testdata(( Resident) tabview.getSelectionModel().selectedItemProperty().getValue());
    nfc.initializeData((Resident) tabviewResidentToShowFiles.getSelectionModel().selectedItemProperty().getValue()/*,prestationMap*/);
         fichePane.setVisible(true);//show the file picture insted of file information
         refreshFile.setVisible(true);//hide refresh button

  
 
    }
    
    @FXML
    private void supprimerFiche(ActionEvent event) {
        //get id fiche to delete from the id resedent
   
  
   ServiceFicheMedicale fms = new ServiceFicheMedicale();
   Resident resident = ( Resident) tabviewResidentToShowFiles.getSelectionModel().selectedItemProperty().getValue();
   List<Fiche_medicale> listefiche = prestationMap.get(resident.getId_resident()); //recuperer liste des fiches selon id resident selectionné
     Fiche_medicale f = listefiche.get(listefiche.size()-1);//recuperer la derniere fiche du resident selectionné
     //////
   
   fms.supprimerFicheMedicale(f.getId_fich());
   
   /*if(getIdFicheParResident(r, lfm)!=0){
       fms.supprimerFicheMedicale(getIdFicheParResident(r, lfm));
   }
   else
            System.out.println("probleme d'ajout !!!!!");
   */
  
        
        
    }
    
    
    @FXML
    private void exporterFiches(ActionEvent event) throws IOException, SQLException {
        
        
        Resident resident = ( Resident) tabviewResidentToShowFiles.getSelectionModel().selectedItemProperty().getValue();
        GenererFichePDF gf = new GenererFichePDF();
        gf.pdf(prestationMap,resident.getId_resident());
    }
    
    
    
    @FXML
    private void refreshFileData(MouseEvent event) {
        
        Resident resident = (Resident) tabviewResidentToShowFiles.getSelectionModel().selectedItemProperty().getValue();
        showResidentFicheData(resident);
    }
public static void initializePrestationDataMap(){
    
    
    ServicePrestationSante sps = new ServicePrestationSante();
    
    List<Prestation_sante> liste_prestation = sps.listPrestation();
   // liste_prestation.stream().forEach( (j) -> System.out.println(j));
    prestationMap.clear();
    liste_prestation.stream().forEach( (j) -> {
            int id = j.getId_resident();
            String nom = j.getNom_resident();
            String prenom = j.getPrenom_resident();
            
            Resident resident = new Resident();
            List<Fiche_medicale> listefiche = sps.getListFicheByIdResdient(id);
            
            resident.setId_resident(id);
            resident.setNom_resident(nom);
            resident.setPrenom_resident(prenom);
            
           prestationMap.put(id, listefiche);
            //listefiche.stream().forEach((f)-> System.out.println(f));
    });
      //prestationMap.values().stream().forEach(System.out::println);
     System.out.println("map reinitilized !!");
    
}
public void initializeSidePane(){
    
        eventP = event;
   profileP = profile;
   actionP = action;
   DonsP = Dons;
   prestationP = prestation;
   affecteDonP = affecteDon;
   videoP = video;
   residentP = resident;
   maisonP = maison;
   accueilP = accueil;
        drawer.open();
        
        try {
            //testing user connected role:
            VBox box;
            switch (ConnexionDB.connectedUser.getRoles()){
                case "Admin":
                { box = FXMLLoader.load(getClass().getResource("SidePanel.fxml"));
                     drawer.setSidePane(box);
                        break;}
                case "Responsable MR":
                { box = FXMLLoader.load(getClass().getResource("SidePanelRMaisonClient.fxml"));
                     drawer.setSidePane(box);
                        break;}
                case "Bénévole":
                { box = FXMLLoader.load(getClass().getResource("SidePanelBenevole.fxml"));
                     drawer.setSidePane(box);
                        break;}
                case "Responsable Club/Association":
                { box = FXMLLoader.load(getClass().getResource("SidePanelRAC.fxml"));
                     drawer.setSidePane(box);
                        break;}
                
            }
          /*  VBox box = FXMLLoader.load(getClass().getResource("SidePanel.fxml"));
            drawer.setSidePane(box);*/
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
     
     
     //  tabview.getSelectionModel().selectFirst();

      
       fichePane.setVisible(true);//show the file picture insted of file information
        addFichePane.setVisible(false);//hide add fiche button while the resident has one
        refreshFile.setVisible(false);//hide refresh button
       
       

        //add listner to change data of display fiches       
        tabviewResidentToShowFiles.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    fichePane.setVisible(false);
                    
                   
                    showResidentFicheData( (Resident) newValue);
        });
        
         //add listner to refresh data file in case of adding or updating  
        addFichePane.visibleProperty().addListener((status)->{
                //if an item selected in the tavleview but no inromation displayed(after adding or modifying fila data):
                   if(addFichePane.isVisible() && tabviewResidentToShowFiles.getSelectionModel().getSelectedIndex()!=-1)
                       refreshFile.setVisible(true);
                   if(addFichePane.isVisible() && tabviewResidentToShowFiles.getSelectionModel().getSelectedIndex()==-1)
                       refreshFile.setVisible(false);
                   
        });
       
         
         
    
  /*  lister.stream().forEach((j) -> {
           System.out.println(j);
        });*/
         
    
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

public void showResidentFicheData( Resident resident){
  
        fichePane.setVisible(false);//hide pane to show information data
     //  System.out.println("befor changing display file "+prestationMap.containsKey(resident.getId_resident()));
    if(prestationMap.containsKey(resident.getId_resident())){//if the resident already have a file
        addFichePane.setVisible(false);
          List<Fiche_medicale> listefiche = prestationMap.get(resident.getId_resident()); //recuperer liste des fiches selon id resident selectionné
     Fiche_medicale f = listefiche.get(listefiche.size()-1);//recuperer la derniere fiche du resident selectionné
     //////
        
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
        RoleUserInFiche.setText(u.getProfession()+" :");
        
        //testing on the file wruter:
        if(!u.equals(ConnexionDB.connectedUser)){
            modifyButton.setDisable(true);
        }
        else  modifyButton.setDisable(false);
        
    }
       else   {
        System.out.println("noo file !!");
        addFichePane.setVisible(true);
    }            

}


public static java.sql.Date convertUtilToSql() {
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }


    
    
    /*
 //docter name (file writer)
         ServicePrestationSante sps = new ServicePrestationSante();
        Prestation_sante ps = sps.getPrestationRowByIdResident(resident.getId_resident());
        User u = sps.getUserData(ps.getId_user());
*/

/*********************************************************************************************************************/

/************************************sabrine : evenement **************************************************************/

 @FXML
    private void ajouterEvent(ActionEvent event) throws MessagingException {
        ServiceEvenement se = new ServiceEvenement();
        Date date = new Date(2019, 07, 11);
        Evenement e = new Evenement(Date.valueOf(iddated.getValue()), Date.valueOf(iddatef.getValue()), idheure.getText(), idnom.getText(), idadresse.getText(), iddescription.getText());

        if (idadresse.getText().isEmpty()) {
            idadresse.setStyle("-fx-background-color: #" + "ff7d9f");
        }

        if (idnom.getText().isEmpty()) {
            idnom.setStyle("-fx-background-color: #" + "ff7d9f");
        }
        if (Date.valueOf(iddated.getValue()).after(date)) {
            iddated.setStyle("-fx-background-color: #" + "ff7d9f");

        }
        if (Date.valueOf(iddatef.getValue()).after(date)) {
            iddatef.setStyle("-fx-background-color: #" + "ff7d9f");

        } else {
            se.ajouterEvenement(e);
        }

        //refreshList
        ArrayList arraylist = (ArrayList) se.listEvenement();
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        idtable.setItems(obs);
        List<String> emails = se.listEmail();
        System.out.println(emails);
        for (String email : emails) {
            sendMail("smtp.gmail.com", 587, email, "invitation nouvel evenement", "on vous invite à un nouvel evenement " + e.getNom_evenement()
                    + " qui sera lieu à " + e.getAdresse_evenement() + " le " + e.getDate_d_evenement() + " à " + e.getHeure_evenement(), "chernisabrine32@gmail.com");
        }
        

    }

    public static void sendMail(String host, int port, String recipients,
            String subject, String content, String from) throws AddressException, MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        SmtpAuthenticator authentication = new SmtpAuthenticator();
        javax.mail.Message message = new MimeMessage(Session.getDefaultInstance(props, authentication));

        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }

    @FXML
    private void supprimerEvent(ActionEvent event) {
        ServiceEvenement se = new ServiceEvenement();
        Evenement e = (Evenement) (idtable.getSelectionModel().getSelectedItem());
        se.supprimerEvenement(e.getId_evenement());

        ArrayList arraylist = (ArrayList) se.listEvenement();
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        idtable.setItems(obs);
        JOptionPane.showMessageDialog(null, "Votre evenement est supprimée avec succés!");

    }


    @FXML
    private void modifierEvent(ActionEvent event) {

        ServiceEvenement se = new ServiceEvenement();
        Evenement e = (Evenement) (idtable.getSelectionModel().getSelectedItem());
        System.out.println(e.getId_evenement());

        e.setAdresse_evenement(idadresse.getText());
        e.setNom_evenement(idnom.getText());
        e.setDate_d_evenement(Date.valueOf(iddated.getValue()));
        e.setDate_f_evenement(Date.valueOf(iddatef.getValue()));
        e.setHeure_evenement(idheure.getText());
        e.setDescription_evenement(iddescription.getText());

        se.modifierEvenement(e);
        //refreshList
        ArrayList arraylist = (ArrayList) se.listEvenement();
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        idtable.setItems(obs);
    }

    @FXML
    private void rechercherEvent(ActionEvent event) {
        ServiceEvenement ms = new ServiceEvenement();
        if (idrechercherEvent.getPromptText().equals("Recherche_nom") && !idrechercherEvent.getText().equals("")) {

            ArrayList Liste = (ArrayList) ms.afficherByNom(idrechercherEvent.getText());
            ObservableList obs = FXCollections.observableArrayList(Liste);
            idtable.setItems(obs);

        }
        if (idrechercherEvent.getText().equals("")) {
            ArrayList arraylist = (ArrayList) ms.listEvenement();
            ObservableList obs = FXCollections.observableArrayList(arraylist);
            idtable.setItems(obs);
        }
    }

    @FXML
    private void rechercherEvent1(ActionEvent event) {
        ServiceEvenement ms = new ServiceEvenement();
        if (idrechercheradresse.getPromptText().equals("Recherche_adresse") && !idrechercheradresse.getText().equals("")) {

            ArrayList Liste = (ArrayList) ms.afficherByAdresse(idrechercheradresse.getText());
            ObservableList obs = FXCollections.observableArrayList(Liste);
            idtable.setItems(obs);

        }
        if (idrechercheradresse.getText().equals("")) {
            ArrayList arraylist = (ArrayList) ms.listEvenement();
            ObservableList obs = FXCollections.observableArrayList(arraylist);
            idtable.setItems(obs);
        }
//        
    }
/**********************************************************************************************************************/

/********************************************ghada : action ***********************************************************/

    @FXML
    private void onClick_btn_stop() {
        mediaplayer.stop();

    }

    @FXML
    private void onClick_btn_play() {
        if (mediaplayer.getStatus() == PLAYING) {
            mediaplayer.stop();
            mediaplayer.play();
        } else {

            mediaplayer.play();
        }

    }

    /*private void disconnect(ActionEvent event) throws IOException {

        Parent page = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }*/

    @FXML
    private void ajouterAction(ActionEvent event) {
        if (Date.valueOf(debut.getValue()).compareTo(Date.valueOf(fin.getValue())) > 0) {
            System.out.println("Date debut is after Date fin");
                            JOptionPane.showMessageDialog(null, "Date debut is after Date fin");

        } else if (Date.valueOf(debut.getValue()).compareTo(Date.valueOf(fin.getValue())) < 0) {
            System.out.println("Date debut is before Date fin");
            ServiceAction sa = new ServiceAction();
            Date date = new Date(1988, 07, 11);

            User u = new User(10, "aaa", "rrr", "azssza", date, "aszsza", "azsasz", "aaa", 555, "aaa", "aaa", "zzz");

            Action a = new Action((String) type_picker.getValue(), Date.valueOf(debut.getValue()), Date.valueOf(fin.getValue()), description_action.getText(), u);
            sa.ajouterAction(a);
            //refreshList
            ArrayList arraylist = (ArrayList) sa.listAction();
            ObservableList obs = FXCollections.observableArrayList(arraylist);
            idtable_action.setItems(obs);
        } else if (Date.valueOf(debut.getValue()).compareTo(Date.valueOf(fin.getValue())) == 0) {
            System.out.println("Date debut is equal to Date fin");
                                        JOptionPane.showMessageDialog(null, "Date debut is equal to Date fin");

        } else {
            System.out.println("How to get here?");
                                                    JOptionPane.showMessageDialog(null, "ERROR DATE");

        }

    }

    @FXML

    private void supprimerAction(ActionEvent event) {
        ServiceAction sa = new ServiceAction();
        Action a = (Action) (idtable_action.getSelectionModel().getSelectedItem());
        sa.supprimerAction(a.getId_action());
        ArrayList arraylist = (ArrayList) sa.listAction();
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        idtable_action.setItems(obs);
        JOptionPane.showMessageDialog(null, "Votre action est supprimée avec succés!");
    }


    @FXML
    private void modifierAction(ActionEvent event) {
        ServiceAction se = new ServiceAction();
        Action a = (Action) idtable_action.getSelectionModel().getSelectedItem();
        System.out.println(a.getId_action());

        a.setType_action((String) type_picker.getValue());

        a.setDate_d_action(Date.valueOf(debut.getValue()));
        a.setDate_f_action(Date.valueOf(fin.getValue()));

        a.setDescription(description_action.getText());

        se.modifierAction(a);
        //refreshList
        ArrayList arraylist = (ArrayList) se.listAction();
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        idtable_action.setItems(obs);
    }

    @FXML
    private void onClick_btn_play1() {
        if (mediaplayer1.getStatus() == PLAYING) {
            mediaplayer1.stop();
            mediaplayer1.play();
        } else {

            mediaplayer1.play();
        }

    }

    @FXML
    private void onClick_btn_stop1() {
        mediaplayer1.stop();
    }

    @FXML
    private void onClick_btn_play2() {
        if (mediaplayer2.getStatus() == PLAYING) {
            mediaplayer2.stop();
            mediaplayer2.play();
        } else {

            mediaplayer2.play();
        }
    }

    @FXML
    private void onClick_btn_stop2() {
        mediaplayer2.stop();
    }

    @FXML
    private void onClick_btn_play3(ActionEvent event) {
        if (mediaplayer3.getStatus() == PLAYING) {
            mediaplayer3.stop();
            mediaplayer3.play();
        } else {

            mediaplayer3.play();
        }
    }

    @FXML
    private void onClick_btn_stop3(ActionEvent event) {
        mediaplayer3.stop();
    }


    
/**********************************************************************************************************************/    
    
/****************************************wassim : don ****************************************************************/

     private void cetCallValueFromTableToTextField() {

        tab_view1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Demande d = (Demande) tab_view1.getItems().get(tab_view1.getSelectionModel().getSelectedIndex());
                descrip_don.setText(d.getDescription_demande());
                //quantite_don.setText(Integer.toString(d.getQuantite_demande()));
                demande.setText(Integer.toString(d.getId_demande()));
                // categorie_don.setValue(d.getCategorie_demande());

                demande.setVisible(false);

            }

        });

    }
    private void cetCallValueFromTableToTextField2() {

        tab_view.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Demande d = (Demande) tab_view.getItems().get(tab_view.getSelectionModel().getSelectedIndex());
                //categorie_don.setText(d.getCategorie_demande());
                description_demande.setText(d.getDescription_demande());
                //quantite_demande.setText(Integer.toString(d.getQuantite_demande()));

                //demande.setText(Integer.toString(d.getId_demande()));
                // demande.setVisible(false);   
            }

        });

    }

  

    @FXML
    private void ajouterDemande(ActionEvent event) {

        ServiceDemande ps = new ServiceDemande();
        //int a = Integer.parseInt(quantite_demande.getText());
        int c = Integer.parseInt(spinner_quantite_demande.getValue().toString());

        //System.out.println("test " +); 
        Maison maison = ps.getMaisonDataByNom((String) nom_maison.getSelectionModel().getSelectedItem());
        // System.out.println(maison.getId_maison());
       // Date date = new Date(1988, 07, 11);
        //User u1 = new User("chahir", "bahri", "chahir18", date, "chahir18esprittn", "1777777", "ariana", 27999888, "admin", "*********", "medecin");
       // User u3 = new User(20, "bbbb", "azsas", "wassim", date, "sssssss", "azsaszsza", "azsazssza", 55, "admin", "*****", "medecin");
        //Maison mm = new Maison(28, "maison1200", "sousse", "msaken", "5020", "58478524", "yafet.shil@esprit.tn", 50, 40, 10, 3);
        //Demande d = new Demande(mm, liste_categorie.getValue().toString(), a, description_demande.getText(), u3);
        Demande d = new Demande(maison.getId_maison(), liste_categorie.getValue().toString(), c, description_demande.getText(),ConnexionDB.connectedUser.getId());
        if (description_demande.getText().trim().isEmpty()){
            //JOptionPane.showMessageDialog(null, "le champ de description est vide", "Error", JOptionPane.ERROR_MESSAGE);
           label_description.setText("la description est vide");
       }
        else{
            ps.ajouterDemande(d);
            System.out.println(d);
            JOptionPane.showMessageDialog(null, "demande ajouté avec succé");
                       label_description.setText("");

        }
        
        description_demande.setText("");
        //quantite_demande.setText("");

        ArrayList arraylist = (ArrayList) ps.listeDemande2();

        ObservableList obs = FXCollections.observableArrayList(arraylist);
        tab_view.setItems(obs);
    }

    @FXML
    private void supprimeDemande(ActionEvent event) {
        ServiceDemande ps = new ServiceDemande();
        Demande d = (Demande) (tab_view.getSelectionModel().getSelectedItem());

        int input = JOptionPane.showConfirmDialog(null, "voulez vous supprimer la demande?");

        if (input == 0) {

            ps.supprimerDemande(d.getId_demande());
            ArrayList arraylist = (ArrayList) ps.listeDemande2();

            ObservableList obs = FXCollections.observableArrayList(arraylist);
            tab_view.setItems(obs);
        }
    }

    @FXML
    private void modiferDemande(ActionEvent event) {
        int a = Integer.parseInt(spinner_quantite_demande.getValue().toString());

        ServiceDemande ps = new ServiceDemande();
        Demande d = (Demande) (tab_view.getSelectionModel().getSelectedItem());
        ps.modifierDemande(d.getId_demande(), liste_categorie.getValue().toString(), a, description_demande.getText());

        ArrayList arraylist = (ArrayList) ps.listeDemande2();

        ObservableList obs = FXCollections.observableArrayList(arraylist);
        tab_view.setItems(obs);

        description_demande.setText("");
        //quantite_demande.setText("");

    }


    @FXML
    private void ajouterDon(ActionEvent event) {
        ServiceDon dn = new ServiceDon();
        ServiceDemande sd = new ServiceDemande();
        //int a = Integer.parseInt(quantite_don.getText());
        int b = Integer.parseInt(demande.getText());
        int c = Integer.parseInt(quantite_spinner.getValue().toString());

        // Date date = new Date(1988, 07, 11);
        //User u1 = new User("chahir", "bahri", "chahir18", date, "chahir18esprittn", "1777777", "ariana", 27999888, "admin", "*********", "medecin");
        // User u3 = new User(11, "bbbb", "azsas", "wassim", date, "sssssss", "azsaszsza", "azsazssza", 55, "admin", "*****", "medecin");
        // Maison mm = new Maison(20, "maison1200", "sousse", "msaken", "5020", "58478524", "yafet.shil@esprit.tn", 50, 40, 10, 3);
        Don d = new Don(categorie_don.getValue().toString(), c, descrip_don.getText(), 1, b);
        dn.ajouterDon(d);
        JOptionPane.showMessageDialog(null, "votre don est affecté avec succé");

        //sd.supprimerDemande(b);
        // Notifications.create()
        // .title("Dons")
        //.text("Affectaion Don")
        //.showConfirm();
    }

    @FXML
    private void ChercherDemande(ActionEvent event) throws SQLException {

        ServiceDemande sd = new ServiceDemande();
        tab_view1.setItems(sd.chercherDemande(chercher_demande.getText()));
    }

/*********************************************** yafet : maison ***************************************************/    


    @FXML
    private void ajouterMaison(ActionEvent event) throws AddressException, MessagingException {
        ServiceMaison sm = new ServiceMaison();
      
  Maison m = new Maison(nom_maison1.getText(), adresse_maison.getText(), telephone_maison.getText(),mail_maison.getText());
String adresse = m.getMail_maison();
/* Sending Mail*/
// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("yafet.shil@esprit.tn"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(adresse));
message.setSubject("HappyOlds");
message.setText("Votre maison a été ajoutée avec succées !");
// Etape 3 : Envoyer le message
Transport.send(message);
                   JOptionPane.showMessageDialog(null, "un mail a été envoyé!");
                                
        sm.ajouterMaison(m);
        ArrayList arraylist = (ArrayList) sm.listMaison();
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        table_maison.setItems(obs);
        nom_maison1.setText("");
                adresse_maison.setText("");
        telephone_maison.setText("");
        mail_maison.setText("");
                 JOptionPane.showMessageDialog(null, "Votre maison est ajoutée avec succés!");
                           
    }

    @FXML
    private void supprimerMaison(ActionEvent event) {
         ServiceMaison sm = new ServiceMaison();
        Maison m = (Maison) (table_maison.getSelectionModel().getSelectedItem());
        sm.supprimerMaison(m.getId_maison());
        ArrayList arraylist = (ArrayList) sm.listMaison();
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        table_maison.setItems(obs);
                 JOptionPane.showMessageDialog(null, "Votre maison a été supprimée avec succés!");

    }

    @FXML
    private void ajouterResident(ActionEvent event) throws SQLException  {
         ServiceResident sr = new ServiceResident();
                  Maison m1=new Maison();

        ArrayList arraylist1 = (ArrayList) sr.listResident(m1);
        ObservableList obs1 = FXCollections.observableArrayList(arraylist1);
        table_resident.setItems(obs1);
        cnom_resident.setCellValueFactory(new PropertyValueFactory<>("nom_resident"));
        cprenom_resident.setCellValueFactory(new PropertyValueFactory<>("prenom_resident"));
                cage_resident.setCellValueFactory(new PropertyValueFactory<>("age_resident"));
                cdate_resident.setCellValueFactory(new PropertyValueFactory<>("date_resident"));
                            csexe_resident.setCellValueFactory(new PropertyValueFactory<>("sexe_resident"));
                    calzheimer_resident.setCellValueFactory(new PropertyValueFactory<>("alzheimer_resident"));
                cmaladie_resident.setCellValueFactory(new PropertyValueFactory<>("maladie_resident"));
        Maison m = (Maison) (table_maison.getSelectionModel().getSelectedItem());
              Resident r = new Resident(m.getId_maison(),nom_resident.getText(), prenom_resident.getText(), age_resident.getValueFactory().getValue(),sexe_resident.getValue().toString(),java.sql.Date.valueOf(date_ajout.getValue()),alzheimer_resident.getValue().toString(),maladie_resident.getText());
             r.setNom_maison(m.getNom_maison());
              sr.ajouterResident(r,m);
              ArrayList arraylist = (ArrayList) sr.listResident(m);
        ObservableList obs = FXCollections.observableArrayList(arraylist);
        table_resident.setItems(obs);
        nom_resident.setText("");
        prenom_resident.setText("");
        age_resident.setValueFactory(null);
        sexe_resident.setValue(null);
        alzheimer_resident.setValue(null);
        maladie_resident.setText("");

TrayNotification tray = new TrayNotification();
        tray.setTitle("Notification");
        tray.setMessage("Resident successfully added !");
        tray.setNotificationType(NotificationType.SUCCESS);
        
        tray.showAndDismiss(Duration.seconds(5));    }
   /* @FXML
    private void annuler(ActionEvent event) {
         AcceuilClientController.acceuilP.setVisible(false);
         AcceuilClientController.maisonP.setVisible(true);
                 AcceuilClientController.actionP.setVisible(true);
         AcceuilClientController.profileP.setVisible(false);
                  AcceuilClientController.donP.setVisible(false);
         AcceuilClientController.residentP.setVisible(false);
                  AcceuilClientController.eventP.setVisible(false); 
    }*/

    @FXML
    private void supprimerResident(ActionEvent event) {
        ServiceResident sr = new ServiceResident();
        Maison m= new Maison();
        Resident r = (Resident) (table_resident.getSelectionModel().getSelectedItem());
        sr.supprimerResident(r.getId_resident());
              ArrayList arraylist2 = (ArrayList) sr.listResident(m);
        ObservableList obs2 = FXCollections.observableArrayList(arraylist2);
        table_resident.setItems(obs2);
                 JOptionPane.showMessageDialog(null, "Votre résident a été supprimée avec succés!");
    }

    @FXML
    private void affectResident(ActionEvent event) {
       //  AcceuilClientController.acceuilP.setVisible(false);
         AcceuilClientController.maisonP.setVisible(false);
            //     AcceuilClientController.actionP.setVisible(false);
        // AcceuilClientController.profileP.setVisible(false);
               //   AcceuilClientController.donP.setVisible(false);
         AcceuilClientController.residentP.setVisible(true);
         
                  ServiceResident sr = new ServiceResident();
                  Maison m1=new Maison();

        ArrayList arraylist1 = (ArrayList) sr.listResident(m1);
        ObservableList obs1 = FXCollections.observableArrayList(arraylist1);
        table_resident.setItems(obs1);
        cnom_resident.setCellValueFactory(new PropertyValueFactory<>("nom_resident"));
        cprenom_resident.setCellValueFactory(new PropertyValueFactory<>("prenom_resident"));
                cage_resident.setCellValueFactory(new PropertyValueFactory<>("age_resident"));
                cdate_resident.setCellValueFactory(new PropertyValueFactory<>("date_resident"));
                            csexe_resident.setCellValueFactory(new PropertyValueFactory<>("sexe_resident"));
                    calzheimer_resident.setCellValueFactory(new PropertyValueFactory<>("alzheimer_resident"));
                cmaladie_resident.setCellValueFactory(new PropertyValueFactory<>("maladie_resident"));

                         Maison m = (Maison) (table_maison.getSelectionModel().getSelectedItem());

    }

    @FXML
    private void annuler(ActionEvent event) {
        
        //  AcceuilClientController.acceuilP.setVisible(false);
         AcceuilClientController.maisonP.setVisible(true);
         //        AcceuilClientController.actionP.setVisible(true);
       //  AcceuilClientController.profileP.setVisible(false);
         //         AcceuilClientController.donP.setVisible(false);
         AcceuilClientController.residentP.setVisible(false);
           //       AcceuilClientController.eventP.setVisible(false); 
    }

 /************************************************chahir : user ************************************************/

    public void profilePaneInit() throws SQLException {

        unom_txt.setText(ConnexionDB.connectedUser.getNom());
        uprenom_txt.setText(ConnexionDB.connectedUser.getPrenom());
        uemail_txt.setText(ConnexionDB.connectedUser.getEmail());
        uadresse_txt.setText(ConnexionDB.connectedUser.getAdresse());
        uphone_txt.setText(Integer.toString(ConnexionDB.connectedUser.getPhone()));

    }

    @FXML
    private void showModificationPane(ActionEvent event) {

        uemail_txf.setText(ConnexionDB.connectedUser.getEmail());
        uadresse_txf.setText(ConnexionDB.connectedUser.getAdresse());
        uphone_txf.setText(Integer.toString(ConnexionDB.connectedUser.getPhone()));
        hideModifPane.setVisible(false);
        uemail_txf.setText(uemail_txt.getText());
        uadresse_txf.setText(uadresse_txt.getText());
        uphone_txf.setText(Integer.toString(Integer.parseInt(uphone_txt.getText())));
    }

    @FXML
    private void modifyUserData(ActionEvent event) throws SQLException {

        ServiceUser su = new ServiceUser();
        if (mdp_txf.getText().isEmpty()) {
            updateEntredUser();

            su.UpdateUser(entredUser);

            hideModifPane.setVisible(true);
            profilePaneInit();

        } else {

            updateEntredUser_withpassword();

            su.UpdateUser_withpass(entredUser);

            hideModifPane.setVisible(true);

        }

        uemail_txt.setText(uemail_txf.getText());
        uadresse_txt.setText(uadresse_txf.getText());
        uphone_txt.setText(Integer.toString(Integer.parseInt(uphone_txf.getText())));

    }

    public void updateEntredUser() {

        entredUser = new User();

        entredUser.setEmail(uemail_txf.getText());

        entredUser.setAdresse(uadresse_txf.getText());
        entredUser.setPhone(Integer.parseInt(uphone_txf.getText()));

    }

    public void updateEntredUser_withpassword() {

        entredUser = new User();

        entredUser.setEmail(uemail_txf.getText());

        entredUser.setAdresse(uadresse_txf.getText());
        entredUser.setPhone(Integer.parseInt(uphone_txf.getText()));
        entredUser.setPassword(mdp_txf.getText());

    }
 /***************************************************************************************************************/
    
}
