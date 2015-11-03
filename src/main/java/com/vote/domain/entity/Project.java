package com.vote.domain.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@SequenceGenerator(name = "SEQ_Project",allocationSize=1,initialValue=1, sequenceName = "SEQUENCE_Project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="SEQ_Project")
    @Column(name="project_id")
    private Integer projectId;
    @Column(unique = true)
    private String projectName;
    private String admin;
    private Integer state;


    public Project() {
    }

    public Project(String projectName, String admin, Integer state) {
        this.projectName = projectName;
        this.admin = admin;
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}

