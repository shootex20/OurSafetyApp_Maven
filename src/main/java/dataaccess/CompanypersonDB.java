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
import domain.CompanyPerson;
import domain.Person;
import javax.persistence.TypedQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanypersonDB.
 *
 * @author Chelsey Coughlin
 */
public class CompanypersonDB {

    /**
     * Gets the all.
     *
     * @param companyID the company ID
     * @return the all
     * @throws Exception the exception
     */
    public List<CompanyPerson> getAll(Company companyID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        em.clear();
        try {
            Company company = em.find(Company.class, companyID.getCompanyID());
            return company.getCompanypersonList();
        } finally {
            em.close();
        }
    }

    /**
     * Gets the.
     *
     * @param person_ID the person ID
     * @return the company person
     * @throws Exception the exception
     */
    public CompanyPerson get(int person_ID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            CompanyPerson compPer = em.find(CompanyPerson.class, person_ID);
            return compPer;
        } finally {
            em.close();
        }
    }

    /**
     * Gets the by fields.
     *
     * @param company_ID the company ID
     * @param person_ID the person ID
     * @param email the email
     * @return the by fields
     */
    public CompanyPerson getByFields(int company_ID, int person_ID, String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CompanyPerson compPer = em.createNamedQuery("CompanyPerson.findByFields", CompanyPerson.class).setParameter("company_ID", company_ID).setParameter("person_ID", person_ID).setParameter("email", email).getSingleResult();
            return compPer;
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
    public void update(CompanyPerson comp) throws Exception {
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
     * @return the company person
     * @throws Exception the exception
     */
    public CompanyPerson insert(CompanyPerson add) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            Company comp = add.getCompanyID();
            comp.getCompanypersonList().add(add);
            trans.begin();
            em.persist(add);
            em.merge(comp);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
            return add;
        }
    }


    /*Does not work.*/
 /*
        public void delete(Companyperson person) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            Company user = person.getCompanyID();
            user.getCompanypersonList().remove(person);
            trans.begin();
            em.remove(em.merge(person));
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
     */
}
