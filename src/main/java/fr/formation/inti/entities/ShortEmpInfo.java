package fr.formation.inti.entities;

import org.springframework.stereotype.Component;

public class ShortEmpInfo {
 
    private Integer empId;
    private String fistName;
    private String lastName;
 
    //
    // Constructor have 3 parameters, will be used in the Hibernate Query.
    //
    public ShortEmpInfo(Integer empId, String firstName, String lastName) {
        this.empId = empId;
        this.fistName = firstName;
        this.lastName = lastName;
    }
 
    public Integer getEmpId() {
        return empId;
    }
 
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
 
    public String getFirstName() {
        return fistName;
    }
 
    public void setFirstName(String firstName) {
        this.fistName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
}