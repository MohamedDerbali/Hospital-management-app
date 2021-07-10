
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;

/**
 *
 * @author YAFET
 */
public class Maison  {

    private int id_maison;
    private String nom_maison;
    private String adresse_maison;
    private String telephone_maison;
    private String mail_maison;
    private int nbr_personne;
    private int nbr_homme;
    private int nbr_femme;
    private int nbr_alzheimer;
    private User user;

    public Maison() {

    }

    

   /* public Maison(String nom_maison, String adresse_maison, String telephone_maison, String mail_maison, int nbr_personne, int nbr_homme, int nbr_femme, int nbr_alzheimer, User user) {
        this.nom_maison = nom_maison;
        this.adresse_maison = adresse_maison;
       
        this.telephone_maison = telephone_maison;
        this.mail_maison = mail_maison;
        this.nbr_personne = nbr_personne;
        this.nbr_homme = nbr_homme;
        this.nbr_femme = nbr_femme;
        this.nbr_alzheimer = nbr_alzheimer;
        this.user = user;

    }*/

    public Maison(int id_maison, String nom_maison, String adresse_maison, String telephone_maison, String mail_maison) {
        this.id_maison = id_maison;
        this.nom_maison = nom_maison;
        this.adresse_maison = adresse_maison;
        this.telephone_maison = telephone_maison;
        this.mail_maison = mail_maison;
    }

    public Maison(String nom_maison, String adresse_maison, String telephone_maison, String mail_maison, int nbr_personne, int nbr_homme, int nbr_femme, int nbr_alzheimer) {
        this.nom_maison = nom_maison;
        this.adresse_maison = adresse_maison;
        this.telephone_maison = telephone_maison;
        this.mail_maison = mail_maison;
        this.nbr_personne = nbr_personne;
        this.nbr_homme = nbr_homme;
        this.nbr_femme = nbr_femme;
        this.nbr_alzheimer = nbr_alzheimer;
    }
        public Maison(String nom_maison, String adresse_maison, String telephone_maison, String mail_maison) {
        this.nom_maison = nom_maison;
        this.adresse_maison = adresse_maison;
        this.telephone_maison = telephone_maison;
        this.mail_maison = mail_maison;
  
    }

    public int getId_maison() {
        return id_maison;
    }

    public String getNom_maison() {
        return nom_maison;
    }

    public String getAdresse_maison() {
        return adresse_maison;
    }

    public String getTelephone_maison() {
        return telephone_maison;
    }

    public String getMail_maison() {
        return mail_maison;
    }

    public int getNbr_personne() {
        return nbr_personne;
    }

    public int getNbr_homme() {
        return nbr_homme;
    }

    public int getNbr_femme() {
        return nbr_femme;
    }

    public int getNbr_alzheimer() {
        return nbr_alzheimer;
    }

    public void setId_maison(int id_maison) {
        this.id_maison = id_maison;
    }

    public void setNom_maison(String nom_maison) {
        this.nom_maison = nom_maison;
    }

    public void setAdresse_maison(String gouvernerat_maison) {
        this.adresse_maison = gouvernerat_maison;
    }

    public void setTelephone_maison(String telephone_maison) {
        this.telephone_maison = telephone_maison;
    }

    public void setMail_maison(String mail_maison) {
        this.mail_maison = mail_maison;
    }

    public void setNbr_personne(int nbr_personne) {
        this.nbr_personne = nbr_personne;
    }

    public void setNbr_homme(int nbr_homme) {
        this.nbr_homme = nbr_homme;
    }

    public void setNbr_femme(int nbr_femme) {
        this.nbr_femme = nbr_femme;
    }

    public void setNbr_alzheimer(int nbr_alzheimer) {
        this.nbr_alzheimer = nbr_alzheimer;
    }

    public User getUser() {
        return user;
    }
     public void setUser(User user) {
        this.user = user;
    }
    @Override
     public String toString() {
        return "Maison{" + "id=" + id_maison + ", nom=" + nom_maison + ", adresse=" + adresse_maison +  ", mail=" + mail_maison + ", telephone=" + telephone_maison + ", nbre pesonne=" + nbr_personne + ", nbre homme=" + nbr_homme + ", nbr femme=" + nbr_femme + ", nbr alzheimer=" + nbr_alzheimer  + '}';
    }
}
