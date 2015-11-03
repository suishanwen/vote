package com.vote.api;

import com.vote.domain.entity.Background;
import com.vote.domain.entity.BackgroundOld;
import com.vote.domain.entity.Project;
import com.vote.domain.facade.BackgroundFacade;
import com.vote.domain.vo.BackgroundVo;
import com.vote.domain.vo.ProjectInfoVo;
import com.vote.domain.util.OnException;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/background")
public class BackgroundResource {
    private BackgroundFacade backgroundFacade;

    @Inject
    public BackgroundResource(BackgroundFacade backgroundFacade) {
        this.backgroundFacade = backgroundFacade;
    }

    @POST
    @Path("/get")
    @OnException("SearchProjectBackgroundInformationFail")
    @Produces(MediaType.APPLICATION_JSON)
    public ProjectInfoVo getBackground(String projectName){
        return backgroundFacade.getBackground(projectName);
    }



    @POST
    @Path("/new")
    @OnException("CreateNewBackgroundFail")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getBackgroundVo(ProjectInfoVo projectInfoVo){
         return backgroundFacade.newBackground(projectInfoVo);
    }



    @POST
    @Path("/update")
    @OnException("UpdateProjectBackgroundInformationFail")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getBackground(ProjectInfoVo projectInfoVo){
        return backgroundFacade.updateBackground(projectInfoVo);
    }


     @POST
     @OnException("SearchProjectBackgroundVoInformationFail")
     @Produces(MediaType.APPLICATION_JSON)
     public BackgroundVo getBackgroundVo(String projectName){
        return backgroundFacade.getBackgroundVo(projectName);
    }

    @POST
    @Path("/history")
    @OnException("SearchProjectHistoryBackgroundVoInformationFail")
    @Produces(MediaType.APPLICATION_JSON)
    public BackgroundVo getHistoryBackgroundVo(BackgroundOld backgroundOld){
        return backgroundFacade.getHistoryBackgroundVo(backgroundOld.getProjectName(),backgroundOld.getBackgroundNo());
    }

}
