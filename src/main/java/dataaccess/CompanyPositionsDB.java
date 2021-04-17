/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.Company;
import domain.CompanyPerson;
import domain.CompanyPositions;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


// TODO: Auto-generated Javadoc
/**
 * The Class CompanyPositionsDB.
 *
 * @author 813017, 844817
 */
public class CompanyPositionsDB {

    
    
    
    /**
     * Get a single company by their id.
     *
     * @param companyID The unique username.
     * @return A Company object if found, null otherwise.
     * @throws Exception the exception
     */
    
    
    public CompanyPositions getAllCompanyPos(Company companyID ) throws Exception {
     EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            CompanyPositions comp = em.find(CompanyPositions.class, companyID);
            return comp;
        } finally {
            em.close();

        }
    }
    
    /**
     * Gets the id.
     *
     * @param posID the pos ID
     * @return the id
     * @throws Exception the exception
     */
    public CompanyPositions getID(int posID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            CompanyPositions add = em.find(CompanyPositions.class, posID);
            return add;
        } finally { 
            em.close();
        }
    }
    
    /**
     * Gets the.
     *
     * @param compPos the comp pos
     * @return the company positions
     * @throws Exception the exception
     */
    public CompanyPositions get(int compPos) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            CompanyPositions pos = em.find(CompanyPositions.class, compPos);
            return pos;
        } finally { 
            em.close();
        }
    }
    
    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public List<CompanyPositions> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<CompanyPositions> comp = em.createNamedQuery("Companypositions.findAll", CompanyPositions.class).getResultList();
             return comp;
    
        } finally {
            em.close();
        }
        
    }
     
    /**
     * Update.
     *
     * @param pos the pos
     * @throws Exception the exception
     */
    public void update(CompanyPositions pos) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(pos);
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
     * @return the company positions
     * @throws Exception the exception
     */
    public CompanyPositions insert(CompanyPositions add) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            CompanyPerson person = add.getCompanyPersonID();
            person.getCompanyPositionsList().add(add);
            trans.begin();
            em.persist(add);
            em.merge(person);
            trans.commit();
        }catch (Exception ex) {
            trans.rollback();
        }finally {
            em.close();
            return add;
        }
    }
  
}