package com.vote.domain.vo;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public class DownloadVo {
    private Integer backgroundNo;
    private Long totalRequirement;
    private Long finishNum;
    private Integer limitWorker;
    private Long succNum;
    private Long recordId;

    public DownloadVo() {
    }

    public DownloadVo(Integer backgroundNo, Long totalRequirement, Long finishNum, Integer limitWorker, Long succNum, Long recordId) {
        this.backgroundNo = backgroundNo;
        this.totalRequirement = totalRequirement;
        this.finishNum = finishNum;
        this.limitWorker = limitWorker;
        this.succNum = succNum;
        this.recordId = recordId;
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

    public Long getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Long finishNum) {
        this.finishNum = finishNum;
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

    public Long getSuccNum() {
        return succNum;
    }

    public void setSuccNum(Long succNum) {
        this.succNum = succNum;
    }
}
