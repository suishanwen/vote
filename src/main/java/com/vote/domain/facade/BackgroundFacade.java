package com.vote.domain.facade;

import com.google.inject.persist.Transactional;
import com.vote.domain.entity.Background;
import com.vote.domain.entity.BackgroundOld;
import com.vote.domain.entity.Project;
import com.vote.domain.vo.BackgroundVo;
import com.vote.domain.vo.ProjectInfoVo;
import com.vote.domain.vo.RecordVo;

import javax.persistence.NoResultException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BackgroundFacade extends BaseFacade{
    public BackgroundVo getBackgroundVo(String projectName){
        try {
            Background background = (Background) entityManager.createQuery("select b from Background b where b.projectName=:projectName")
                    .setParameter("projectName", projectName).getSingleResult();
            String sql = "select new com.vote.domain.vo.RecordVo" +
                    "(r.workerId," + "'" + projectName + "'" +
                    " as projectName,r.succNum,r.uploadTime) " +
                    "from Record r where " +
                    "r.projectName=:projectName " +
                    "and r.backgroundNo=:backgroundNo ";
            List<RecordVo> recordVoList = entityManager.createQuery(sql).setParameter("projectName", projectName)
                    .setParameter("backgroundNo", background.getBackgroundNo()).getResultList();
            BackgroundVo backgroundVo = new BackgroundVo();
            backgroundVo.setBackgroundNo(background.getBackgroundNo());
            backgroundVo.setLimitWorker(background.getLimitWorker());
            backgroundVo.setTotalRequirement(background.getTotalRequirement());
            backgroundVo.setNotice(background.getNotice());
            backgroundVo.setRecordVoList(recordVoList);
            return backgroundVo;
        }catch (Exception e){
            return null;
        }
    }

    public BackgroundVo getHistoryBackgroundVo(String projectName,Integer backgroundNo){
        try {
            BackgroundOld backgroundOld = (BackgroundOld) entityManager.createQuery("select b from BackgroundOld b where b.projectName=:projectName and b.backgroundNo=:backgroundNo")
                    .setParameter("projectName", projectName)
                    .setParameter("backgroundNo", backgroundNo)
                    .getSingleResult();
            String sql="select new com.vote.domain.vo.RecordVo"+
                    "(r.workerId,"+"'"+projectName+"'"+
                    " as projectName,r.succNum,r.uploadTime) "+
                    "from Record r where "+
                    "r.projectName=:projectName "+
                    "and r.backgroundNo=:backgroundNo ";
            List<RecordVo> recordVoList=entityManager.createQuery(sql).setParameter("projectName",projectName)
                    .setParameter("backgroundNo", backgroundNo).getResultList();
            BackgroundVo backgroundVo=new BackgroundVo();
            backgroundVo.setBackgroundNo(backgroundNo);
            backgroundVo.setLimitWorker(backgroundOld.getLimitWorker());
            backgroundVo.setTotalRequirement(backgroundOld.getTotalRequirement());
            backgroundVo.setNotice(backgroundOld.getNotice());
            backgroundVo.setRecordVoList(recordVoList);
            return  backgroundVo;
        }catch (Exception e){
            return  null;
        }
    }

    public ProjectInfoVo getBackground(String projectName){
        Background background;
        try {
            background = (Background) entityManager.createQuery("select b from Background b where b.projectName=:projectName")
                    .setParameter("projectName", projectName)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
        ProjectInfoVo projectInfoVo = new ProjectInfoVo();
        Date date=new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, -15);
        date = c.getTime();
        String sql="select sum(r.succNum) from Record r where r.projectName=:projectName and r.backgroundNo=:backgroundNo ";
        Long finishNum=Long.parseLong("0");
        Integer participate=0;
        Integer activeParticipate=0;
        try {
            finishNum=Long.parseLong(entityManager.createQuery(sql).setParameter("projectName",  projectName)
                    .setParameter("backgroundNo",background.getBackgroundNo())
                    .getSingleResult().toString());
            sql="select count (r.recordId) from Record r where r.projectName=:projectName and r.backgroundNo=:backgroundNo ";
            participate=Integer.parseInt(entityManager.createQuery(sql).setParameter("projectName",  projectName)
                    .setParameter("backgroundNo", background.getBackgroundNo())
                    .getSingleResult().toString());
            sql="select count (r.recordId) from Record r where r.projectName=:projectName and r.backgroundNo=:backgroundNo and r.uploadTime>:date";
            activeParticipate=Integer.parseInt(entityManager.createQuery(sql).setParameter("projectName",  projectName)
                    .setParameter("backgroundNo", background.getBackgroundNo())
                    .setParameter("date",date)
                    .getSingleResult().toString());
            }catch (Exception e){}
            projectInfoVo.setBackgroundNo(background.getBackgroundNo());
            projectInfoVo.setProjectName(background.getProjectName());
            projectInfoVo.setTotalRequirement(background.getTotalRequirement());
            projectInfoVo.setBackgroundId(background.getBackgroundId());
            projectInfoVo.setLimitWorker(background.getLimitWorker());
            projectInfoVo.setNotice(background.getNotice());
            projectInfoVo.setParticipate(participate);
            projectInfoVo.setActiveParticipate(activeParticipate);
            projectInfoVo.setFinishNum(finishNum);
        return projectInfoVo;
    }

    @Transactional
    public Integer newBackground(ProjectInfoVo projectInfoVo){
        Background newBackground=new Background();
        newBackground.setBackgroundNo(projectInfoVo.getBackgroundNo());
        newBackground.setProjectName(projectInfoVo.getProjectName());
        newBackground.setLimitWorker(projectInfoVo.getLimitWorker());
        newBackground.setTotalRequirement(projectInfoVo.getTotalRequirement());
        newBackground.setNotice(projectInfoVo.getNotice());
        entityManager.persist(newBackground);
        if (projectInfoVo.getBackgroundId()!=null) {
            Background background = entityManager.find(Background.class, projectInfoVo.getBackgroundId());
            BackgroundOld backgroundOld=new BackgroundOld();
            backgroundOld.setBackgroundId(background.getBackgroundId());
            backgroundOld.setBackgroundNo(background.getBackgroundNo());
            backgroundOld.setLimitWorker(background.getLimitWorker());
            backgroundOld.setProjectName(background.getProjectName());
            backgroundOld.setTotalRequirement(background.getTotalRequirement());
            backgroundOld.setNotice(background.getNotice());
            entityManager.persist(backgroundOld);
            entityManager.remove(background);
        }
        return newBackground.getBackgroundId();
    }


    @Transactional
    public  Integer  updateBackground(ProjectInfoVo projectInfoVo){
        Background background=entityManager.find(Background.class,projectInfoVo.getBackgroundId());
        background.setTotalRequirement(projectInfoVo.getTotalRequirement());
        background.setLimitWorker(projectInfoVo.getLimitWorker());
        background.setNotice(projectInfoVo.getNotice());
        entityManager.persist(background);
        return background.getBackgroundId();
    }
}
