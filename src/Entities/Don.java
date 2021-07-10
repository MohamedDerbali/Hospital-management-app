/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.Button;

/**
 *
 * @author Admin
 */
public class Don {

    public Don() {
    }
    
    
    
    private int id_don;
    private String categori_don;
    private int Quantite_don;
    private String description_don;
    private int id_user;
    private int id_demande;
    private Button btn_affect;

    public Don(String categori_don, int Quantite_don, String description_don, int id_user, int id_demande) {
        this.categori_don = categori_don;
        this.Quantite_don = Quantite_don;
        this.description_don = description_don;
        this.id_user = id_user;
        this.id_demande = id_demande;
    }

    public Don(String categori_don, int Quantite_don, String description_don, int id_demande, Button btn_affect) {
        this.categori_don = categori_don;
        this.Quantite_don = Quantite_don;
        this.description_don = description_don;
        this.id_demande = id_demande;
        this.btn_affect = btn_affect;
    }

    public Don(String categori_don, int Quantite_don, String description_don, int id_user) {
        this.categori_don = categori_don;
        this.Quantite_don = Quantite_don;
        this.description_don = description_don;
        this.id_user = id_user;
    }

    public Don(String categori_don, int Quantite_don, String description_don) {
        this.categori_don = categori_don;
        this.Quantite_don = Quantite_don;
        this.description_don = description_don;
    }
    

    public int getId_don() {
        return id_don;
    }

    public void setId_don(int id_don) {
        this.id_don = id_don;
    }

    public String getCategori_don() {
        return categori_don;
    }

    public void setCategori_don(String categori_don) {
        this.categori_don = categori_don;
    }

    public int getQuantite_don() {
        return Quantite_don;
    }

    public void setQuantite_don(int Quantite_don) {
        this.Quantite_don = Quantite_don;
    }

    public String getDescription_don() {
        return description_don;
    }

    public void setDescription_don(String description_don) {
        this.description_don = description_don;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public Button getBtn_affect() {
        return btn_affect;
    }

    public void setBtn_affect(Button btn_affect) {
        this.btn_affect = btn_affect;
    }

    
    
    @Override
    public String toString() {
        return "Don{" + "id_don=" + id_don + ", categori_don=" + categori_don + ", Quantite_don=" + Quantite_don + ", description_don=" + description_don + ", id_user=" + id_user + ", id_demande=" + id_demande + '}';
    }

    
    
    
}
