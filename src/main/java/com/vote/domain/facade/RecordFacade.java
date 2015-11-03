package com.vote.domain.facade;

import com.vote.domain.entity.Background;
import com.vote.domain.entity.Record;
import com.vote.domain.vo.DownloadVo;
import com.vote.domain.vo.UploadVo;
import com.google.inject.persist.Transactional;

import javax.persistence.NoResultException;
import java.util.Date;

public class RecordFacade extends BaseFacade {

    @Transactional
    public DownloadVo uploadRecord(UploadVo uploadVo){
        Background background=(Background)entityManager.createQuery("select b from Background b where b.projectName=:projectName")
                .setParameter("projectName",uploadVo.getProjectName()).getSingleResult();
        Record record;
        if(uploadVo.getBackgroundNo()==background.getBackgroundNo()) {
            if (uploadVo.getRecordId() == null) {
                String sql = "select r from Record r where " +
                        "r.backgroundNo=:backgroundNo " +
                        "and r.workerId=:workerId " +
                        "and r.projectName=:projectName";
                try {
                    record = (Record) entityManager.createQuery(sql).setParameter("projectName", background.getProjectName())
                            .setParameter("workerId", uploadVo.getWorkerId())
                            .setParameter("backgroundNo", background.getBackgroundNo()).getSingleResult();
                    record.setSuccNum(record.getSuccNum() + uploadVo.getSuccNum());
                } catch (NoResultException e) {
                    record = new Record();
                    record.setSuccNum(uploadVo.getSuccNum());
                    record.setBackgroundNo(uploadVo.getBackgroundNo());
                    record.setProjectName(uploadVo.getProjectName());
                    record.setWorkerId(uploadVo.getWorkerId());
                }
            } else {
                record = entityManager.find(Record.class, uploadVo.getRecordId());
                record.setSuccNum(record.getSuccNum() + uploadVo.getSuccNum());
            }
        }else{
            record= new Record();
            record.setSuccNum(uploadVo.getSuccNum());
            record.setBackgroundNo(background.getBackgroundNo());
            record.setProjectName(uploadVo.getProjectName());
            record.setWorkerId(uploadVo.getWorkerId());
        }
        record.setUploadTime(new Date());
        entityManager.persist(record);
        String finishNum=entityManager.createQuery("select sum(r.succNum) from Record r where r.projectName=:projectName and r.backgroundNo=:backgroundNo")
                .setParameter("projectName", record.getProjectName())
                .setParameter("backgroundNo", record.getBackgroundNo())
                .getSingleResult().toString();
        DownloadVo downloadVo=new DownloadVo();
        downloadVo.setTotalRequirement(background.getTotalRequirement());
        downloadVo.setBackgroundNo(background.getBackgroundNo());
        downloadVo.setLimitWorker(background.getLimitWorker());
        downloadVo.setFinishNum(Long.parseLong(finishNum));
        downloadVo.setRecordId(record.getRecordId());
        downloadVo.setSuccNum(record.getSuccNum());

        return  downloadVo;

    }

    @Transactional
    public void deletedRecord(Long recordId){
        Record r = entityManager.find(Record.class, recordId);
        entityManager.remove(r);
    }

}
