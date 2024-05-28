/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author Thinh
 */
public class ModelPerson {
    private int id;
    private String fisrt_name; 
    private String last_name;
    private String dob;
    private String office;

    public ModelPerson() {
        
    }

    public ModelPerson(int id, String fisrt_name, String last_name, String dob, String office) {
        this.id = id;
        this.fisrt_name = fisrt_name;
        this.last_name = last_name;
        this.dob = dob;
        this.office = office;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFisrt_name() {
        return fisrt_name;
    }

    public void setFisrt_name(String fisrt_name) {
        this.fisrt_name = fisrt_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getOffice() {
        return office;
    }
    
    public void setOffice(String office) {
        this.office = office;
    }            
}
