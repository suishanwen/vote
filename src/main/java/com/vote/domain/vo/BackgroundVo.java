package com.vote.domain.vo;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlTransient
public class BackgroundVo {
    private Long totalRequirement;
    private Integer backgroundNo;
    private Integer limitWorker;
    private Long finishNum;
    private Integer participate;
    private Integer activeParticipate;
    private String notice;
    private List<RecordVo> recordVoList;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Integer getActiveParticipate() {
        return activeParticipate;
    }

    public void setActiveParticipate(Integer activeParticipate) {
        this.activeParticipate = activeParticipate;
    }

    public Long getTotalRequirement() {
        return totalRequirement;
    }

    public void setTotalRequirement(Long totalRequirement) {
        this.totalRequirement = totalRequirement;
    }

    public Integer getBackgroundNo() {
        return backgroundNo;
    }

    public void setBackgroundNo(Integer backgroundNo) {
        this.backgroundNo = backgroundNo;
    }

    public Integer getLimitWorker() {
        return limitWorker;
    }

    public void setLimitWorker(Integer limitWorker) {
        this.limitWorker = limitWorker;
    }

    public Long getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Long finishNum) {
        this.finishNum = finishNum;
    }

    public Integer getParticipate() {
        return participate;
    }

    public void setParticipate(Integer participate) {
        this.participate = participate;
    }

    public List<RecordVo> getRecordVoList() {
        return recordVoList;
    }

    public void setRecordVoList(List<RecordVo> recordVoList) {
        this.recordVoList = recordVoList;
    }

    public BackgroundVo() {

    }

    public BackgroundVo(Long totalRequirement, Integer backgroundNo, Integer limitWorker, Long finishNum, Integer participate, Integer activeParticipate, String notice, List<RecordVo> recordVoList) {
        this.totalRequirement = totalRequirement;
        this.backgroundNo = backgroundNo;
        this.limitWorker = limitWorker;
        this.finishNum = finishNum;
        this.participate = participate;
        this.activeParticipate = activeParticipate;
        this.notice = notice;
        this.recordVoList = recordVoList;
    }
}
