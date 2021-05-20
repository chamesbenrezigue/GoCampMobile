/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author PC
 */
public class Reservation {
    private Integer id;
    private String nom;
        private String prenom;
    private String approuve;
    private String nbrplace;
        private String event;

    public Reservation(Integer id, String nom, String prenom, String approuve, String nbrplace, String event) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.approuve = approuve;
        this.nbrplace = nbrplace;
        this.event = event;
       
    }

    public Reservation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getApprouve() {
        return approuve;
    }

    public void setApprouve(String approve) {
        this.approuve = approve;
    }

    public String getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(String nbrplace) {
        this.nbrplace = nbrplace;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", approve=" + approuve + ", nbrplace=" + nbrplace + ", event=" + event + '}';
    }
        


    
}
