package com.vote.api;

import com.vote.domain.entity.Project;
import com.vote.domain.facade.ProjectFacade;
import com.vote.domain.util.OnException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/project")
public class ProjectResource {
    private ProjectFacade projectFacade;
    @Inject
    public ProjectResource(ProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    @POST
    @OnException("GetProjectListFail")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectByAdmin(String admin){
        return projectFacade.getProjectByAdmin(admin);
    }

    @POST
    @Path("add")
    @OnException("AddProjectFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void addProject(Project project){
        projectFacade.addProject(project);
    }

    @POST
    @Path("update")
    @OnException("UpdateProjectFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateProject(Project project){
        projectFacade.updateProject(project);
    }

    @POST
    @Path("delete")
    @OnException("DeleteProjectFail")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProject(Integer projectId){
        projectFacade.deleteProject(projectId);
    }


}
