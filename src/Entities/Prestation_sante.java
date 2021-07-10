/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author ahmed
 */
public class Prestation_sante {
    private int id_prestation;
    private int id_resident;
    private int id_fiche;
    private int id_user;
    private String nom_resident;
    private String prenom_resident;
    private String nom_user;
    private String prenom_user;
    private String medicaments;
    private Date date;

    public Prestation_sante(int id_resident, int id_fiche, int id_user, String nom_resident, String prenom_resident, String nom_user, String prenom_user, String medicaments, Date date) {
        this.id_resident = id_resident;
        this.id_fiche = id_fiche;
        this.id_user = id_user;
        this.nom_resident = nom_resident;
        this.prenom_resident = prenom_resident;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.medicaments = medicaments;
        this.date = date;
    }

    public Prestation_sante() {
    }

    
    public int getId_prestation() {
        return id_prestation;
    }

    public void setId_prestation(int id_prestation) {
        this.id_prestation = id_prestation;
    }

    public int getId_resident() {
        return id_resident;
    }

    public void setId_resident(int id_resident) {
        this.id_resident = id_resident;
    }

    public int getId_fiche() {
        return id_fiche;
    }

    public void setId_fiche(int id_fiche) {
        this.id_fiche = id_fiche;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom_resident() {
        return nom_resident;
    }

    public void setNom_resident(String nom_resident) {
        this.nom_resident = nom_resident;
    }

    public String getPrenom_resident() {
        return prenom_resident;
    }

    public void setPrenom_resident(String prenom_resident) {
        this.prenom_resident = prenom_resident;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public String getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(String medicaments) {
        this.medicaments = medicaments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Prestation_sante{" + "id_prestation=" + id_prestation + ", id_resident=" + id_resident + ", id_fiche=" + id_fiche + ", id_user=" + id_user + ", nom_resident=" + nom_resident + ", prenom_resident=" + prenom_resident + ", nom_user=" + nom_user + ", prenom_user=" + prenom_user + ", medicaments=" + medicaments + ", date=" + date + '}';
    }
    
    
    

    
    
    
    
}
