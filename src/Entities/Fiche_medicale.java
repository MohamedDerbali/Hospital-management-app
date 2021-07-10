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
public class Fiche_medicale {
    private int id_fich;
    private Date date__creation_fich;
    private Date dermodif_fich;
    private float niveauTemp;
    private float niveauSuc;
    private float niveauTension;
    private String remarques_fiche;
    private String groupeSanguin;
    private String medicaments;
    private float taille_resident;
    private float poids_resident;

    public Fiche_medicale(Date date__creation_fich, Date dermodif_fich, float niveauSuc, float niveauTension, String remarques_fiche, String groupeSanguin, float taille_resident, float poids_resident) {
        this.date__creation_fich = date__creation_fich;
        this.dermodif_fich = dermodif_fich;
        this.niveauSuc = niveauSuc;
        this.niveauTension = niveauTension;
        this.remarques_fiche = remarques_fiche;
        this.groupeSanguin = groupeSanguin;
        this.taille_resident = taille_resident;
        this.poids_resident = poids_resident;
    }

    public Fiche_medicale() {

    }

    
    
    public int getId_fich() {
        return id_fich;
    }

    public void setId_fich(int id_fich) {
        this.id_fich = id_fich;
    }

    public Date getDate__creation_fich() {
        return date__creation_fich;
    }

    public void setDate__creation_fich(Date date__creation_fich) {
        this.date__creation_fich = date__creation_fich;
    }

    public Date getDermodif_fich() {
        return dermodif_fich;
    }

    public void setDermodif_fich(Date dermodif_fich) {
        this.dermodif_fich = dermodif_fich;
    }

    public float getNiveauTemp() {
        return niveauTemp;
    }

    public void setNiveauTemp(float niveauTemp) {
        this.niveauTemp = niveauTemp;
    }

    public float getNiveauSuc() {
        return niveauSuc;
    }

    public void setNiveauSuc(float niveauSuc) {
        this.niveauSuc = niveauSuc;
    }

    public float getNiveauTension() {
        return niveauTension;
    }

    public void setNiveauTension(float niveauTension) {
        this.niveauTension = niveauTension;
    }

    public String getRemarques_fiche() {
        return remarques_fiche;
    }

    public void setRemarques_fiche(String remarques_fiche) {
        this.remarques_fiche = remarques_fiche;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public String getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(String medicaments) {
        this.medicaments = medicaments;
    }

    public float getTaille_resident() {
        return taille_resident;
    }

    public void setTaille_resident(float taille_resident) {
        this.taille_resident = taille_resident;
    }

    public float getPoids_resident() {
        return poids_resident;
    }

    public void setPoids_resident(float poids_resident) {
        this.poids_resident = poids_resident;
    }

    @Override
    public String toString() {
        return "Fiche_medicale{" + "id_fich=" + id_fich + ", date__creation_fich=" + date__creation_fich + ", dermodif_fich=" + dermodif_fich + ", niveauTemp=" + niveauTemp + ", niveauSuc=" + niveauSuc + ", niveauTension=" + niveauTension + ", remarques_fiche=" + remarques_fiche + ", groupeSanguin=" + groupeSanguin + ", medicaments=" + medicaments + ", taille_resident=" + taille_resident + ", poids_resident=" + poids_resident + '}';
    }

    
    
}
