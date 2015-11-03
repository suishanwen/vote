package com.vote.domain.vo;


import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@XmlTransient
public class RecordVo {
    private String workerId;
    private String projectName;
    private Long succNum;
    private Date uploadTime;

    public RecordVo() {
    }

    public RecordVo(String workerId, String projectName, Long succNum, Date uploadTime) {
        this.workerId = workerId;
        this.projectName = projectName;
        this.succNum = succNum;
        this.uploadTime = uploadTime;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getSuccNum() {
        return succNum;
    }

    public void setSuccNum(Long succNum) {
        this.succNum = succNum;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
