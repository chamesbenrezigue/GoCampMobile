/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;


/**
 *
 * @author PC
 */
public class Event {
    private Integer id;
    private String titre;
    private String type;
    private String image;
    private String resevation;
    private Date start;
    private Date end;
    private Double prix;
    private String description;
    

    public Event(Integer id, String titre,String description, String type, String image, String resevation, Date start, Date end, Double prix) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.image = image;
        this.resevation = resevation;
        this.start = start;
        this.end = end;
        this.prix = prix;
        this.description = description;
    }

    public Event() {
    }
    

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResevation() {
        return resevation;
    }

    public void setResevation(String resevation) {
        this.resevation = resevation;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", titre=" + titre + ", type=" + type + ", image=" + image + ", resevation=" + resevation + ", start=" + start + ", end=" + end + ", prix=" + prix + ", description=" + description + '}';
    }

    
    
    
    
    
    
    
}
