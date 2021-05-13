/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;



/**
 *
 * @author chaima
 */
public class ReservationMaterial {
    
    private int id;
    private int user_id, material_id;
    private String  dateStart;
    private String dateEnd;

    public ReservationMaterial(int id, int user_id, int material_id, String  dateStart, String  dateEnd) {
        this.id = id;
        this.user_id = user_id;
        this.material_id = material_id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public ReservationMaterial(String  dateStart, String  dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public ReservationMaterial() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public String  getDateStart() {
        return dateStart;
    }

    public void setDateStart(String  dateStart) {
        this.dateStart = dateStart;
    }

    public String  getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String  dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "ReservationMaterial{" + "id=" + id + ", user_id=" + user_id + ", material_id=" + material_id + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + '}';
    }
    
    
    
}
