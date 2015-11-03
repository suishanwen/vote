package com.vote.api;

import com.vote.domain.facade.RecordFacade;
import com.vote.domain.util.OnException;
import com.vote.domain.vo.DownloadVo;
import com.vote.domain.vo.UploadVo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/record")
public class RecordResource {
    private RecordFacade recordFacade;
    @Inject
    public RecordResource(RecordFacade recordFacade) {
        this.recordFacade = recordFacade;
    }

    @POST
    @Path("upload")
    @OnException("UploadRecordFail")
    @Produces(MediaType.APPLICATION_JSON)
    public DownloadVo uploadRecord(UploadVo uploadVo){
        return  recordFacade.uploadRecord(uploadVo);
    }

    @POST
    @Path("delete")
    @OnException("DeleteRecordFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void deletedRecord(Long recordId){
        recordFacade.deletedRecord(recordId);
    }
}