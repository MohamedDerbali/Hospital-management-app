/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * FXML Controller class
 *
 * @author poste
 */
public class MdpOublierController implements Initializable {

    @FXML
    private JFXButton send_btn;
    @FXML
    private TextField email_txt;
    @FXML
    private JFXButton annuler_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sendAction(ActionEvent event) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.user", "chahir.1819@gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/pidev", "root", "");
            String query = "select password from user where email LIKE '" + email_txt.getText() + "'";
            PreparedStatement statmnt = con.prepareStatement(query);
            ResultSet result = statmnt.executeQuery();
            if (result.next()) {
                
                String fetchedPassword = result.getString("password");
                Session session = Session.getDefaultInstance(props, null);
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                message.setText("Your password is " + fetchedPassword);
                message.setSubject("Mot de passe pour votre compte");
                message.setFrom(new InternetAddress("chahir.1819@gmail.com"));
                message.addRecipient(RecipientType.TO, new InternetAddress(email_txt.getText().trim()));
                message.saveChanges();
                javax.mail.Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", "chahir.1819@gmail.com", "629485137.CHAHIR.19");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Votre mot de passe a été envoyé");

                alert.showAndWait();

            }else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Adresse e-mail introuvable");

            alert.showAndWait();
                
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Adresse e-mail introuvable");

            alert.showAndWait();

        }
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

    @FXML
    private void LoginActionKey(KeyEvent event) {
    }

}
