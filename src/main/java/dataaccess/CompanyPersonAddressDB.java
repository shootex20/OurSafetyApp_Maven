/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.Address;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.CompanyPerson;
import domain.CompanyPersonAddress;
import javax.persistence.Query;


/**
 *
 * @author Chelsey Coughlin
 */
public class CompanyPersonAddressDB {
    
    public CompanyPersonAddressDB()
    {
        
    }
    
    public List<CompanyPersonAddress> getAll(int companyper_ID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CompanyPerson companyPer = em.find(CompanyPerson.class, companyper_ID);
            return companyPer.getCompanyPersonAddressList();
        } finally {
            em.close();
        }
    }
    
        public CompanyPersonAddress get(CompanyPerson compPerson) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
               return em.createNamedQuery("CompanyPersonAddress.findBycompanyPerson_ID", CompanyPersonAddress.class).setParameter("companyPerson_ID", compPerson).getSingleResult();
        } finally { 
            em.close();
        }
    }
        
    public CompanyPersonAddress getID(int address_ID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            CompanyPersonAddress add = em.find(CompanyPersonAddress.class, address_ID);
            return add;
        } finally { 
            em.close();
        }
    }
        
    public void update(CompanyPersonAddress comp) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
           em.merge(comp);
           trans.commit();
           
        } catch (Exception ex) {
            trans.rollback();
        } finally {
           em.close();

        }
        
    }
    
        public CompanyPersonAddress insert(CompanyPersonAddress add) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            Address address = add.getAddressID();
            address.getCompanypersonaddressList().add(add);
            trans.begin();
            em.persist(add);
            em.merge(address);
            trans.commit();
        }catch (Exception ex) {
            trans.rollback();
        }finally {
            em.close();
            return add;
        }
    }
        
        public void updatePerson (CompanyPersonAddress add) throws Exception
        {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
            try
            {
            CompanyPerson cperson = add.getCompanyPersonID();
            cperson.getCompanyPersonAddressList().add(add);
            trans.begin();
            em.persist(add);
            em.merge(cperson);
            trans.commit();         
        }catch (Exception ex) {
            trans.rollback();
        }finally {
            em.close();
        }
        } 
  
}
