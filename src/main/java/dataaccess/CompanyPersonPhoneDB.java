/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import domain.CompanyPerson;
import domain.CompanyPersonPhone;
import domain.Phone;
import javax.persistence.EntityTransaction;
import servlets.EmployeeServlet;


// TODO: Auto-generated Javadoc
/**
 * The Class CompanyPersonPhoneDB.
 *
 * @author Chelsey Coughlin
 */
public class CompanyPersonPhoneDB {

    /**
     * Gets the all.
     *
     * @param companyper_ID the companyper ID
     * @return the all
     * @throws Exception the exception
     */
    public List<CompanyPersonPhone> getAll(int companyper_ID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CompanyPerson companyPer = em.find(CompanyPerson.class, companyper_ID);
            return companyPer.getCompanyPersonPhoneList();
        } finally {
            em.close();
        }
    }
        
    /**
     * Gets the.
     *
     * @param compPerson the comp person
     * @return the company person phone
     * @throws Exception the exception
     */
    public CompanyPersonPhone get(CompanyPerson compPerson) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
             return em.createNamedQuery("CompanyPersonPhone.findBycompanyPerson_ID", CompanyPersonPhone.class).setParameter("companyPerson_ID", compPerson).getSingleResult();
        } finally { 
            em.close();
        }
    }
        
    /**
     * Gets the id.
     *
     * @param phoneID the phone ID
     * @return the id
     * @throws Exception the exception
     */
    public CompanyPersonPhone getID(int phoneID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            CompanyPersonPhone add = em.find(CompanyPersonPhone.class, phoneID);
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
    public void update(CompanyPersonPhone comp) throws Exception {
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
     * @return the company person phone
     * @throws Exception the exception
     */
    public CompanyPersonPhone insert(CompanyPersonPhone add) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            Phone address = add.getPhoneID();
            address.getCompanypersonphoneList().add(add);
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
    public void updatePerson (CompanyPersonPhone add) throws Exception
        {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
            try
            {
                CompanyPerson cPerson = add.getCompanyPersonID();
                cPerson.getCompanyPersonPhoneList().add(add);
                trans.begin();
                em.merge(cPerson);
                trans.commit();        
            }catch (Exception ex) {
                trans.rollback();
            }finally {
                em.close();
            }
        } 
        /*
        public void updatePhone (Companypersonphone add) throws Exception
        {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
            try
            {
            Phone ph = add.getPhoneID();
            ph.getCompanypersonphoneList().add(add);
            trans.begin();
            em.merge(ph);
            trans.commit();       
            }catch (Exception ex) {
                trans.rollback();
                return;
            }finally {
                em.close();
            }
        }
        */

    /**
         * Delete.
         *
         * @param add the add
         * @throws Exception the exception
         */
    public void delete(CompanyPersonPhone add) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();  
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(add);
            trans.commit();
        } catch(Exception ex){
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
