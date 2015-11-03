package com.vote.domain.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@SequenceGenerator(name = "SEQ_Admin",allocationSize=1,initialValue=1, sequenceName = "SEQUENCE_Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="SEQ_Admin")
    private Integer adminId;
    private String admin;
    private String password;
    private String state;

    public Admin() {
    }

    public Admin(String admin, String password) {
        this.admin = admin;
        this.password = password;
    }

    public Admin(String admin, String password, String state) {
        this.admin = admin;
        this.password = password;
        this.state = state;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
