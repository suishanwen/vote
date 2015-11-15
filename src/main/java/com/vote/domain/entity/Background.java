package com.vote.domain.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Background {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer backgroundId;
    private String projectName;
    private Integer backgroundNo;
    private Long totalRequirement;
    private Integer limitWorker;
    private String notice;


    public Background() {
    }

    public Background(String projectName, Integer backgroundNo, Long totalRequirement, Integer limitWorker, String notice) {
        this.projectName = projectName;
        this.backgroundNo = backgroundNo;
        this.totalRequirement = totalRequirement;
        this.limitWorker = limitWorker;
        this.notice = notice;
    }

    public Integer getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(Integer backgroundId) {
        this.backgroundId = backgroundId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getBackgroundNo() {
        return backgroundNo;
    }

    public void setBackgroundNo(Integer backgroundNo) {
        this.backgroundNo = backgroundNo;
    }

    public Long getTotalRequirement() {
        return totalRequirement;
    }

    public void setTotalRequirement(Long totalRequirement) {
        this.totalRequirement = totalRequirement;
    }

    public Integer getLimitWorker() {
        return limitWorker;
    }

    public void setLimitWorker(Integer limitWorker) {
        this.limitWorker = limitWorker;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
