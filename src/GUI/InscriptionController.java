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
import com.jfoenix.controls.JFXComboBox;
import static com.sun.webkit.graphics.WCImage.getImage;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author poste
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField prenom_txt;
    @FXML
    private PasswordField mdp_txt;
    @FXML
    private ImageView userImage;
    @FXML
    private TextField nom_txt;
    @FXML
    private TextField email_txt;
    @FXML
    private DatePicker birthDate_txt;
    @FXML
    private TextField phone_txt;
    @FXML
    private TextField adress_txt;
    @FXML
    private JFXButton iscrire_btn;
    @FXML
    private JFXButton annuler_btn;

    @FXML
    private TextField userrname_txt;
    @FXML
    private ChoiceBox<String> rolePicker;

    public User entredUser;

    @FXML
    private TextField profession_txt;
    @FXML
    private ImageView email_imgw;
    @FXML
    private ImageView phone_imgw;

    public String photo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rolePicker.getItems().add("Responsable MR");
        rolePicker.getItems().add("Responsable Club/Association");
      //  rolePicker.getItems().add("Responsable association");
        rolePicker.getItems().add("Bénévole");

        email_txt.textProperty().addListener((observable, oldValue, newValue) -> {

            if (isValid(newValue) == false) {

                email_imgw.setVisible(true);
            } else if (newValue.length() == 0) {
                email_imgw.setVisible(false);

            } else {
                email_imgw.setVisible(false);

            }

        });

        phone_txt.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!(newValue.matches("[0-9]+") && newValue.length() == 8)) {
                phone_imgw.setVisible(true);

            } else if (newValue.length() == 0) {
                phone_imgw.setVisible(true);

            } else {
                phone_imgw.setVisible(false);

            }

        });

    }

    @FXML
    private void ProfileImagePickerAction(MouseEvent event) throws MalformedURLException {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.PNG", "*.JPG", "*gif"));
        File file = fc.showOpenDialog(null);
        String path = file.getPath();
        Image image = new Image(file.toURI().toString());
        userImage.setImage(image);
        photo = path;
    }

    @FXML
    private void InscriptionAction(ActionEvent event) throws IOException {

        if (email_txt.getText().isEmpty()) {
            email_txt.setStyle("-fx-background-color: #" + "ff7d9f");

        }

        if (nom_txt.getText().isEmpty()) {
            nom_txt.setStyle("-fx-background-color: #" + "ff7d9f");

        }
        if (prenom_txt.getText().isEmpty()) {
            prenom_txt.setStyle("-fx-background-color: #" + "ff7d9f");

        }
        if (mdp_txt.getText().isEmpty()) {
            mdp_txt.setStyle("-fx-background-color: #" + "ff7d9f");

        }
        if (adress_txt.getText().isEmpty()) {
            adress_txt.setStyle("-fx-background-color: #" + "ff7d9f");

        }

        if (birthDate_txt.getValue() == null) {
            birthDate_txt.setStyle("-fx-background-color: #" + "ff7d9f");

        }
        if (userrname_txt.getText().isEmpty()) {
            userrname_txt.setStyle("-fx-background-color: #" + "ff7d9f");

        }

        if (phone_txt.getText().isEmpty()) {
            phone_txt.setStyle("-fx-background-color: #" + "ff7d9f");

        }
        if (profession_txt.getText().isEmpty()) {
            profession_txt.setStyle("-fx-background-color: #" + "ff7d9f");

        } else {

            ServiceUser su = new ServiceUser();
            AddEntredUser();
            su.ajouterUser(entredUser);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Iscription");
            alert.setContentText("Inscription effectuée avec succès");
            alert.setHeaderText(null);
            alert.showAndWait();
            Parent page = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();

        }
    }

    public void AddEntredUser() {

        entredUser = new User();
        entredUser.setUsername(userrname_txt.getText());
        entredUser.setNom(nom_txt.getText());
        entredUser.setPrenom(prenom_txt.getText());
        entredUser.setDate_naissance(java.sql.Date.valueOf(birthDate_txt.getValue()));
        entredUser.setEmail(email_txt.getText());
        entredUser.setPassword(mdp_txt.getText());
        entredUser.setAdresse(adress_txt.getText());    
        entredUser.setPhone(Integer.parseInt(phone_txt.getText()));
        entredUser.setRoles(rolePicker.getValue());
        entredUser.setProfession(profession_txt.getText());
        entredUser.setImage(photo);

    }

    @FXML
    private void AnnulerAction(ActionEvent event) throws IOException {

        Parent page = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();

    }

    public boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }
}
