package com.vote.domain.facade;


import com.google.inject.persist.Transactional;
import com.vote.domain.entity.Project;
import com.vote.domain.vo.ProjectInfoVo;

import java.util.List;

public class ProjectFacade extends BaseFacade{

    @Transactional
    public void addProject(Project project){
        Project p=new Project();
        p.setProjectName(project.getProjectName());
        p.setAdmin(project.getAdmin());
        p.setState(project.getState());
        entityManager.persist(p);
    }

    @Transactional
    public void deleteProject(Integer projectId){
        Project p=entityManager.find(Project.class,projectId);
        entityManager.remove(p);
    }

    @Transactional
    public void updateProject(Project project){
        Project p=entityManager.find(Project.class,project.getProjectId());
        p.setProjectName(project.getProjectName());
        p.setAdmin(project.getAdmin());
        p.setState(project.getState());
        entityManager.persist(p);
    }

    public ProjectInfoVo initProject(String projectName){
        String sql="select new com.vote.domain.vo.ProjectInfoVo"+
                "(p.projectId,p.projectName,b.backgroundId,b.backgroundNo,b.totalRequirement,"+
                "sum(r.succNum) as finishNum,"+
                "b.limitWorker,"+
                "count(r.recordId) as participate) "+
                "from Record r,Background b,Project p where "+
                "p.projectName=:projectName "+
                "and b.projectName=p.projectName "+
                "and r.backgroundNo=b.backgroundNo "+
                "and r.projectName=p.projectName";
        ProjectInfoVo projectInfoVo=(ProjectInfoVo)entityManager.createQuery(sql).setParameter("projectName",projectName).getSingleResult();
        return  projectInfoVo;
    }

    public List<Project> getProjectByAdmin(String admin){
        String sql="select p from Project p ";
        if(!admin.equals("suishanwen")){
            sql=sql+"where p.admin=\'"+admin+"\'";
        }
        return entityManager.createQuery(sql).getResultList();
    }
}
