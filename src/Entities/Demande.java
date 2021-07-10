/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Admin
 */
public class Demande {
    

    private int id_demande;
    private int id_maison;
    private String categorie_demande;
    private int quantite_demande;
    private String description_demande;
    private int id_user;

    public Demande() {
    }
    

    public Demande(int id_maison, String categorie_demande, int quantite_demande, String description_demande, int id_user) {
        this.id_maison = id_maison;
        this.categorie_demande = categorie_demande;
        this.quantite_demande = quantite_demande;
        this.description_demande = description_demande;
        this.id_user = id_user;
    }

    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public int getId_maison() {
        return id_maison;
    }

    public void setId_maison(int id_maison) {
        this.id_maison = id_maison;
    }

    public String getCategorie_demande() {
        return categorie_demande;
    }

    public void setCategorie_demande(String categorie_demande) {
        this.categorie_demande = categorie_demande;
    }

    public int getQuantite_demande() {
        return quantite_demande;
    }

    public void setQuantite_demande(int quantite_demande) {
        this.quantite_demande = quantite_demande;
    }

    public String getDescription_demande() {
        return description_demande;
    }

    public void setDescription_demande(String description_demande) {
        this.description_demande = description_demande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Demande{" + "id_demande=" + id_demande + ", id_maison=" + id_maison + ", categorie_demande=" + categorie_demande + ", quantite_demande=" + quantite_demande + ", description_demande=" + description_demande + ", id_user=" + id_user + '}';
    }
   

    
    

   
}
