/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.Manual;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

// TODO: Auto-generated Javadoc
/**
 * The Class ManualDB.
 *
 * @author 813033
 */
public class ManualDB {
    
    /**
     * Gets the.
     *
     * @param id the id
     * @return the manual
     * @throws Exception the exception
     */
    public Manual get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Manual manual = em.find(Manual.class, id);
            return manual;
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
    public List<Manual> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Manual> manual = em.createNamedQuery("Manual.findAll", Manual.class).getResultList();
             return manual;
    
        } finally {
            em.close();
        }
    }
    
    /**
     * Insert.
     *
     * @param manual the manual
     * @throws Exception the exception
     */
    public void insert(Manual manual) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(manual);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Delete.
     *
     * @param manual the manual
     * @throws Exception the exception
     */
    public void delete(Manual manual) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();  
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(manual));            
            trans.commit();
        } catch(Exception ex){
            trans.rollback();
        } finally {
            em.close();
        }
    }
            
    /**
     * Update.
     *
     * @param manual the manual
     * @throws Exception the exception
     */
    public void update(Manual manual) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(manual);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
