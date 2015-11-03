package com.vote.domain.vo;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public class UploadVo {
    private String projectName;
    private Integer backgroundNo;
    private Long recordId;
    private String workerId;
    private Long succNum;

    public UploadVo() {
    }

    public UploadVo(String projectName, Integer backgroundNo, Long recordId, String workerId, Long succNum) {
        this.projectName = projectName;
        this.backgroundNo = backgroundNo;
        this.recordId = recordId;
        this.workerId = workerId;
        this.succNum = succNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public Long getSuccNum() {
        return succNum;
    }

    public void setSuccNum(Long succNum) {
        this.succNum = succNum;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Integer getBackgroundNo() {
        return backgroundNo;
    }

    public void setBackgroundNo(Integer backgroundNo) {
        this.backgroundNo = backgroundNo;
    }
}
