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


/**
 *
 * @author Chelsey Coughlin
 */
public class CompanyPersonPhoneDB {

    /**
     *
     * @param companyper_ID
     * @return
     * @throws Exception
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
     *
     * @param compPerson
     * @return
     * @throws Exception
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
     *
     * @param phoneID
     * @return
     * @throws Exception
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
     *
     * @param comp
     * @throws Exception
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
     *
     * @param add
     * @return
     * @throws Exception
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
     *
     * @param add
     * @throws Exception
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
     *
     * @param add
     * @throws Exception
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
