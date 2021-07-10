/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author YAFET
 */
public class Resident extends Maison{
    private int id_resident;
    private Maison maison;
        private int id_maison;
        private String nom_maison ;

    private String nom_resident;
    private String prenom_resident;
    private int age_resident;
    private String sexe_resident;
    private java.sql.Date date_resident;
    private String alzheimer_resident;
    private String maladie_resident;
 
    public Resident(){
        
    }

    public Resident(Maison maison, String nom_resident, String prenom_resident, int age_resident, String sexe_resident, Date date_resident, String alzheimer_resident, String maladie_resident, int id_maison, String nom_maison, String adresse_maison, String telephone_maison, String mail_maison) {
        super(id_maison, nom_maison, adresse_maison, telephone_maison, mail_maison);
        this.maison = maison;
        this.nom_resident = nom_resident;
        this.prenom_resident = prenom_resident;
        this.age_resident = age_resident;
        this.sexe_resident = sexe_resident;
        this.date_resident = date_resident;
        this.alzheimer_resident = alzheimer_resident;
        this.maladie_resident = maladie_resident;
    }
        public Resident(int id_maison, String nom_resident, String prenom_resident, int age_resident, String sexe_resident, Date date_resident, String alzheimer_resident, String maladie_resident) {
        this.id_maison = id_maison;
        this.nom_resident = nom_resident;
        this.prenom_resident = prenom_resident;
        this.age_resident = age_resident;
        this.sexe_resident = sexe_resident;
        this.date_resident = date_resident;
        this.alzheimer_resident = alzheimer_resident;
        this.maladie_resident = maladie_resident;
    }
     public Resident(String nom_resident,String prenom_resident,int age_resident,String sexe_resident, java.sql.Date date_resident,String alzheimer_resident,String maladie_resident){
       
       // this.maison = maison;
        this.nom_resident = nom_resident;
        this.prenom_resident = prenom_resident;
        this.age_resident = age_resident;
        this.sexe_resident = sexe_resident;
        this.date_resident = date_resident;
        this.alzheimer_resident = alzheimer_resident;
        this.maladie_resident = maladie_resident;
    }

  

    public String getNom_maison() {
        return nom_maison;
    }

    public void setNom_maison(String nom_maison) {
        this.nom_maison = nom_maison;
    }

   
     public int getId_resident() {
        return id_resident;
    }

    public String getNom_resident() {
        return nom_resident;
    }

    public String getPrenom_resident() {
        return prenom_resident;
    }
       public String getSexe_resident() {
        return sexe_resident;
    }
    public java.sql.Date getDate_resident() {
        return date_resident;
    }
    public int getAge_resident() {
        return age_resident;
    }

    public String getAlzheimer() {
        return alzheimer_resident;
    }

    public String getMaladie() {
        return maladie_resident;
    }


    public Maison getMaison() {
        return maison;
    }
     public void setMaison(Maison maison) {
        this.maison = maison;
    }

    public void setId_resident(int id_resident) {
        this.id_resident = id_resident;
    }

    public void setNom_resident(String nom_resident) {
        this.nom_resident = nom_resident;
    }

    public void setPrenom_resident(String prenom_resident) {
        this.prenom_resident = prenom_resident;
    }
  public void setSexe_resident(String sexe_resident) {
        this.sexe_resident = sexe_resident;
    }
   public void setDate_resident(java.sql.Date date_resident) {
        this.date_resident = date_resident;
    }
    public void setAge_resident(int age_resident) {
        this.age_resident = age_resident;
    }

    public void setAlzheimer_resident(String alzheimer_resident) {
        this.alzheimer_resident = alzheimer_resident;
    }

    public void setMaladie_resident(String maladie_resident) {
        this.maladie_resident = maladie_resident;
    }

    @Override
    public String toString() {
        return "Resident{" + "id_resident=" + id_resident + ", maison=" + maison + ", id_maison=" + id_maison + ", nom_resident=" + nom_resident + ", prenom_resident=" + prenom_resident + ", age_resident=" + age_resident + ", sexe_resident=" + sexe_resident + ", date_resident=" + date_resident + ", alzheimer_resident=" + alzheimer_resident + ", maladie_resident=" + maladie_resident + '}';
    }
    

}
