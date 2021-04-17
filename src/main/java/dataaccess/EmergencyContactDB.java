/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import domain.EmergencyContact;
import javax.persistence.Query;


// TODO: Auto-generated Javadoc
/**
 * The Class EmergencyContactDB.
 *
 * @author Chelsey Coughlin
 */
public class EmergencyContactDB {
    
    /**
     * Gets the.
     *
     * @param compPerson the comp person
     * @return the emergency contact
     * @throws Exception the exception
     */
    public EmergencyContact get(int compPerson) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            EmergencyContact add = em.find(EmergencyContact.class, compPerson);
            return add;
        } finally { 
            em.close();
        }
    }

    /**
     * Update.
     *
     * @param con the con
     * @throws Exception the exception
     */
    public void update(EmergencyContact con) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(con);
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
     * @return the emergency contact
     * @throws Exception the exception
     */
    public EmergencyContact insert(EmergencyContact add) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(add);
            trans.commit();
        }catch (Exception ex) {
            trans.rollback();
        }finally {
            em.close();
            return add;
        }
    }

}
