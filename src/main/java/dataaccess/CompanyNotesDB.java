/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.CompanyNotes;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


// TODO: Auto-generated Javadoc
/**
 * The Class CompanyNotesDB.
 *
 * @author Chelsey Coughlin
 */
public class CompanyNotesDB {
    
    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public List<CompanyNotes> getAll() throws Exception {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
          TypedQuery<CompanyNotes> query = em.createNamedQuery("CompanyNotes.findAll", CompanyNotes.class);
         List<CompanyNotes> results = query.getResultList();
            return results;
    }
   
    /**
     * Gets the.
     *
     * @param compNotesID the comp notes ID
     * @return the company notes
     * @throws Exception the exception
     */
    public CompanyNotes get(int compNotesID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            CompanyNotes add = em.find(CompanyNotes.class, compNotesID);
            return add;
        } finally { 
            em.close();
        }
    }

    /**
     * Insert.
     *
     * @param note the note
     * @throws Exception the exception
     */
    public void insert(CompanyNotes note) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {       
            trans.begin();
            em.persist(note);
            trans.commit();
        }catch (Exception ex) {
            trans.rollback();
        }finally {
            em.close();
        }
    }

    /**
     * Update.
     *
     * @param note the note
     * @throws Exception the exception
     */
    public void update(CompanyNotes note) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(note);
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
     * @param note the note
     * @throws Exception the exception
     */
    public void delete(CompanyNotes note) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();  
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(note));
            trans.commit();
        } catch(Exception ex){
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
