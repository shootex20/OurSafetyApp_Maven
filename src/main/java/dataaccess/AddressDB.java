/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.Address;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;



// TODO: Auto-generated Javadoc
/**
 * The Class AddressDB.
 *
 * @author Chelsey Coughlin
 */
public class AddressDB {
    
    /**
     * Gets the.
     *
     * @param address_ID the address ID
     * @return the address
     * @throws Exception the exception
     */
    public Address get(int address_ID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Address add = em.find(Address.class, address_ID);
            return add;
        } finally { 
            em.close();
        }
    }
    
    /**
     * Insert.
     *
     * @param add the add
     * @return the address
     * @throws Exception the exception
     */
    public Address insert(Address add) throws Exception {
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

    /**
     * Update.
     *
     * @param address the address
     * @throws Exception the exception
     */
    public void update(Address address) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(address);
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
     * @param add the add
     * @throws Exception the exception
     */
    public void delete(Address add) throws Exception {
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
