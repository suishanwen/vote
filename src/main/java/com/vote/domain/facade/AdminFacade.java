package com.vote.domain.facade;


import com.google.inject.persist.Transactional;
import com.vote.domain.entity.Admin;

import java.util.List;

public class AdminFacade extends BaseFacade{

    @Transactional
    public void addAdmin(Admin admin){
        Admin a=new Admin();
        a.setAdmin(admin.getAdmin());
        a.setPassword(admin.getPassword());
        a.setState("1");
        entityManager.persist(a);
    }

    @Transactional
    public void deleteAdmin(Integer adminId){
        Admin p=entityManager.find(Admin.class,adminId);
        entityManager.remove(p);
    }

    @Transactional
    public void updateAdmin(Admin admin){
        Admin a=entityManager.find(Admin.class,admin.getAdminId());
        a.setAdmin(admin.getAdmin());
        a.setPassword(admin.getPassword());
        entityManager.persist(a);
    }


    public List<Admin> getAllAdmin(){
        return entityManager.createQuery("select a from Admin a").getResultList();
    }

    public Admin loginAdmin(Admin admin){
        try {
            Admin a=(Admin)entityManager.createQuery("select a from Admin a where a.admin=:admin and a.password=:password")
                    .setParameter("admin", admin.getAdmin())
                    .setParameter("password", admin.getPassword())
                    .getSingleResult();
            return a;
        }catch (Exception e){
            System.out.println("UserTryLoginFailed");
            return null;
        }
    }
}
