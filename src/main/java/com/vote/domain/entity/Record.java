package com.vote.domain.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@XmlRootElement
@SequenceGenerator(name = "SEQ_Record",allocationSize=1,initialValue=1, sequenceName = "SEQUENCE_Record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="SEQ_Record")
    private Long recordId;
    private String projectName;
    private String workerId;
    private Integer backgroundNo;
    private Long succNum;
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadTime;

    public Record() {
    }

    public Record(String projectName, String workerId, Integer backgroundNo, Long succNum, Date uploadTime) {
        this.projectName = projectName;
        this.workerId = workerId;
        this.backgroundNo = backgroundNo;
        this.succNum = succNum;
        this.uploadTime = uploadTime;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public Integer getBackgroundNo() {
        return backgroundNo;
    }

    public void setBackgroundNo(Integer backgroundNo) {
        this.backgroundNo = backgroundNo;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}

