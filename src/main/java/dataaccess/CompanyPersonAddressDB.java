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


// TODO: Auto-generated Javadoc
/**
 * The Class CompanyPersonAddressDB.
 *
 * @author Chelsey Coughlin
 */
public class CompanyPersonAddressDB {
    
    /**
     * Instantiates a new company person address DB.
     */
    public CompanyPersonAddressDB()
    {
        
    }
    
    /**
     * Gets the all.
     *
     * @param companyper_ID the companyper ID
     * @return the all
     * @throws Exception the exception
     */
    public List<CompanyPersonAddress> getAll(int companyper_ID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CompanyPerson companyPer = em.find(CompanyPerson.class, companyper_ID);
            return companyPer.getCompanyPersonAddressList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Gets the.
     *
     * @param compPerson the comp person
     * @return the company person address
     * @throws Exception the exception
     */
    public CompanyPersonAddress get(CompanyPerson compPerson) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
               return em.createNamedQuery("CompanyPersonAddress.findBycompanyPerson_ID", CompanyPersonAddress.class).setParameter("companyPerson_ID", compPerson).getSingleResult();
        } finally { 
            em.close();
        }
    }
        
    /**
     * Gets the id.
     *
     * @param address_ID the address ID
     * @return the id
     * @throws Exception the exception
     */
    public CompanyPersonAddress getID(int address_ID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            CompanyPersonAddress add = em.find(CompanyPersonAddress.class, address_ID);
            return add;
        } finally { 
            em.close();
        }
    }
        
    /**
     * Update.
     *
     * @param comp the comp
     * @throws Exception the exception
     */
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
    
    /**
     * Insert.
     *
     * @param add the add
     * @return the company person address
     * @throws Exception the exception
     */
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
        
    /**
     * Update person.
     *
     * @param add the add
     * @throws Exception the exception
     */
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
