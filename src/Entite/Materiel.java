/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author rekik
 */
public class Materiel {

    private int id;
    private String Nom;
    private float Prix;
    private String Description;
    private String Photo;
    private String idclient;
    private String comments;

    public Materiel(int id, String Nom, float Prix, String Description, String Photo, String idclient, String comments) {
        this.id = id;
        this.Nom = Nom;
        this.Prix = Prix;
        this.Description = Description;
        this.Photo = Photo;
        this.idclient = idclient;
        this.comments = comments;
    }

    public Materiel(int id, String Nom, float Prix, String Description, String Photo) {
        this.id = id;
        this.Nom = Nom;
        this.Prix = Prix;
        this.Description = Description;
        this.Photo = Photo;
    }

    public Materiel(String Nom, float Prix, String Description, String Photo) {
        this.Nom = Nom;
        this.Prix = Prix;
        this.Description = Description;
        this.Photo = Photo;
    }

    public Materiel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Materiel{" + "id=" + id + ", Nom=" + Nom + ", Prix=" + Prix + ", Description=" + Description + ", Photo=" + Photo + ", idclient=" + idclient + ", comments=" + comments + '}';
    }

}
