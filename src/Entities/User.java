/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author poste
 */
public class User {

    private int id;
    private String nom;
    private String prenom;
    private String username;
    private Date date_naissance;
    private String email;
    private String password;
    private String adresse;
    private int phone;
    private String Roles;
    private String image;
    private String profession;
    private String statut;

    public User() {
    }

    public User(int id, String nom, String prenom, String username, Date date_naissance, String email, String password, String adresse, int phone, String Roles, String image, String profession) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.date_naissance = date_naissance;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.phone = phone;
        this.Roles = Roles;
        this.image = image;
        this.profession = profession;
    }

    public User(String nom, String prenom, String username, Date date_naissance, String email, String password, String adresse, int phone, String Roles, String image, String profession) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.date_naissance = date_naissance;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.phone = phone;
        this.Roles = Roles;
        this.image = image;
        this.profession = profession;
    }

    public User(String nom, String prenom, String email, int phone, String Roles, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.Roles = Roles;
        this.statut = statut;
    }

    public User(int id, String nom, String prenom, String username, Date date_naissance, String email, String password, String adresse, int phone, String Roles, String image, String profession, String statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.date_naissance = date_naissance;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.phone = phone;
        this.Roles = Roles;
        this.image = image;
        this.profession = profession;
        this.statut = statut;
    }

    public User(String nom, String prenom, String username, Date date_naissance, String email, String password, String adresse, int phone, String Roles, String image, String profession, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.date_naissance = date_naissance;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.phone = phone;
        this.Roles = Roles;
        this.image = image;
        this.profession = profession;
        this.statut = statut;
    }

    public User(int id, String nom, String prenom, String Roles, String statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.Roles = Roles;
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", date_naissance=" + date_naissance + ", email=" + email + ", password=" + password + ", adresse=" + adresse + ", phone=" + phone + ", Roles=" + Roles + ", image=" + image + ", profession=" + profession + ", statut=" + statut + '}';
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String Roles) {
        this.Roles = Roles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}
