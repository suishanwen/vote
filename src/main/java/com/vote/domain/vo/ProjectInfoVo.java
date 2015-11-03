package com.vote.domain.vo;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public class ProjectInfoVo {
    private String projectName;
    private Integer backgroundId;
    private Integer backgroundNo;
    private Long totalRequirement;
    private Long finishNum;
    private Integer limitWorker;
    private Integer participate;
    private Integer activeParticipate;
    private String notice;

    public ProjectInfoVo() {
    }

    public ProjectInfoVo(String projectName, Integer backgroundId, Integer backgroundNo, Long totalRequirement, Long finishNum, Integer limitWorker, Integer participate, Integer activeParticipate, String notice) {
        this.projectName = projectName;
        this.backgroundId = backgroundId;
        this.backgroundNo = backgroundNo;
        this.totalRequirement = totalRequirement;
        this.finishNum = finishNum;
        this.limitWorker = limitWorker;
        this.participate = participate;
        this.activeParticipate = activeParticipate;
        this.notice = notice;
    }

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(Integer backgroundId) {
        this.backgroundId = backgroundId;
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

    public Long getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Long finishNum) {
        this.finishNum = finishNum;
    }

    public Integer getLimitWorker() {
        return limitWorker;
    }

    public void setLimitWorker(Integer limitWorker) {
        this.limitWorker = limitWorker;
    }

    public Integer getParticipate() {
        return participate;
    }

    public void setParticipate(Integer participate) {
        this.participate = participate;
    }
}
