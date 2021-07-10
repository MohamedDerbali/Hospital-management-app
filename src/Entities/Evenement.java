/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author acer
 */
public class Evenement {

    private int id_evenement;
    private Date date_d_evenement;
    private Date date_f_evenement;
    private String heure_evenement;
    private String nom_evenement;
    private String adresse_evenement;
    private String description_evenement;

    public Evenement() {
    }

    public Evenement(Date date_d_evenement, Date date_f_evenement, String heure_evenement, String nom_evenement, String adresse_evenement, String description_evenement) {
        this.date_d_evenement = date_d_evenement;
        this.date_f_evenement = date_f_evenement;
        this.heure_evenement = heure_evenement;
        this.nom_evenement = nom_evenement;
        this.adresse_evenement = adresse_evenement;
        this.description_evenement = description_evenement;
    }

    public Evenement(int id_evenement, Date date_d_evenement, Date date_f_evenement, String heure_evenement, String nom_evenement, String adresse_evenement, String description_evenement) {
        this.id_evenement = id_evenement;
        this.date_d_evenement = date_d_evenement;
        this.date_f_evenement = date_f_evenement;
        this.heure_evenement = heure_evenement;
        this.nom_evenement = nom_evenement;
        this.adresse_evenement = adresse_evenement;
        this.description_evenement = description_evenement;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public Date getDate_d_evenement() {
        return date_d_evenement;
    }

    public Date getDate_f_evenement() {
        return date_f_evenement;
    }

    public String getHeure_evenement() {
        return heure_evenement;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public String getAdresse_evenement() {
        return adresse_evenement;
    }

    public String getDescription_evenement() {
        return description_evenement;
    }

    public void setDescription_evenement(String description_evenement) {
        this.description_evenement = description_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setDate_d_evenement(Date date_d_evenement) {
        this.date_d_evenement = date_d_evenement;
    }

    public void setDate_f_evenement(Date date_f_evenement) {
        this.date_f_evenement = date_f_evenement;
    }

    public void setHeure_evenement(String heure_evenement) {
        this.heure_evenement = heure_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public void setAdresse_evenement(String adresse_evenement) {
        this.adresse_evenement = adresse_evenement;
    }

}
