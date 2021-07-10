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
public class Action {
    
      private int id_action;
    private String type_action;
    private Date date_d_action;
    private Date date_f_action;
    private String Description;
    private User user;
    
    
    

    public Action() {
    }

    public Action(String type_action, Date date_d_action, Date date_f_action, String Description, User user) {
        this.type_action = type_action;
        this.date_d_action = date_d_action;
        this.date_f_action = date_f_action;
        this.Description = Description;
        this.user = user;
    }
    
    

    public Action(int id_action, String type_action, Date date_d_action, Date date_f_action, String Description, User user) {
        this.id_action = id_action;
        this.type_action = type_action;
        this.date_d_action = date_d_action;
        this.date_f_action = date_f_action;
        this.Description = Description;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Action{" + "id_action=" + id_action + ", type_action=" + type_action + ", date_d_action=" + date_d_action + ", date_f_action=" + date_f_action + ", Description=" + Description + ", user=" + user + '}';
    }
    
    

    public int getId_action() {
        return id_action;
    }

    public Action(String type_action, Date date_d_action, Date date_f_action, String Description) {
        this.type_action = type_action;
        this.date_d_action = date_d_action;
        this.date_f_action = date_f_action;
        this.Description = Description;
    }

    public void setId_action(int id_action) {
        this.id_action = id_action;
    }

    public String getType_action() {
        return type_action;
    }

    public void setType_action(String type_action) {
        this.type_action = type_action;
    }

    public Date getDate_d_action() {
        return date_d_action;
    }

    public void setDate_d_action(Date date_d_action) {
        this.date_d_action = date_d_action;
    }

    public Date getDate_f_action() {
        return date_f_action;
    }

    public void setDate_f_action(Date date_f_action) {
        this.date_f_action = date_f_action;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
   
   

   

   
    
    
    
}
