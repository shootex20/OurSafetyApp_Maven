/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import domain.Company;
import javax.persistence.metamodel.SingularAttribute;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDB.
 *
 * @author 813017, 844817
 */
public class CompanyDB {

    /**
     * Get a single company by their id.
     *
     * @param companyID The unique username.
     * @return A Company object if found, null otherwise.
     * @throws Exception the exception
     */
    public Company get(int companyID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Company comp = em.find(Company.class, companyID);
            return comp;
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
    public List<Company> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Company> comp = em.createNamedQuery("Company.findAll", Company.class).getResultList();
            return comp;

        } finally {
            em.close();
        }

    }

    /**
     * Insert.
     *
     * @param comp the comp
     * @return the int
     * @throws Exception the exception
     */
    public int insert(Company comp) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {

            trans.begin();
            em.persist(comp);
            trans.commit();

        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
            return 1;
        }
    }

    /**
     * Update.
     *
     * @param comp the comp
     * @return the int
     * @throws Exception the exception
     */
    public int update(Company comp) throws Exception {
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
            return 1;
        }

    }

    /**
     * Delete.
     *
     * @param comp the comp
     * @return the int
     * @throws Exception the exception
     */
    public int delete(Company comp) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();
            em.remove(em.merge(comp));
            trans.commit();

        } catch (Exception ex) {
            trans.rollback();

        } finally {
            em.close();
            return 1;
        }

    }

    /**
     * Gets the.
     *
     * @param companyID the company ID
     * @return the company
     */
    public Company get(SingularAttribute<Company, Integer> companyID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Gets the.
     *
     * @param companyID the company ID
     * @return the company
     */
    public Company get(Company companyID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}