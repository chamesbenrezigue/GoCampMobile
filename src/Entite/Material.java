/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;

/**
 *
 * @author chaima
 */
public class Material {
    private int id;
    private String name;
    private String description;
    private float prix;
    private int quantity;
    private int nbrmatrres;
    private boolean availability;
    private String image;
    private Date updatedAt; 

    public Material(int id, String name, String description, float prix, int quantity, int nbrmatrres, boolean availability, String image, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prix = prix;
        this.quantity = quantity;
        this.nbrmatrres = nbrmatrres;
        this.availability = availability;
        this.image = image;
        this.updatedAt = updatedAt;
    }

    public Material(String name, String description, float prix, int quantity, int nbrmatrres, boolean availability, String image) {
        this.name = name;
        this.description = description;
        this.prix = prix;
        this.quantity = quantity;
        this.nbrmatrres = nbrmatrres;
        this.availability = availability;
        this.image = image;
    }

    public Material() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNbrmatrres() {
        return nbrmatrres;
    }

    public void setNbrmatrres(int nbrmatrres) {
        this.nbrmatrres = nbrmatrres;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", name=" + name + ", description=" + description + ", prix=" + prix + ", quantity=" + quantity + ", nbrmatrres=" + nbrmatrres + ", availability=" + availability + ", image=" + image + ", updatedAt=" + updatedAt + '}';
    }
    
    
    
}
