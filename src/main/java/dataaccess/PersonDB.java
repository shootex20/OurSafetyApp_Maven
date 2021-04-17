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
import domain.Person;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonDB.
 *
 * @author Chelsey Coughlin
 */
public class PersonDB {

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public List<Person> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Person> person = em.createNamedQuery("Person.findAll", Person.class).getResultList();
            return person;

        } finally {
            em.close();
        }

    }

    /**
     * Gets the with fields.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param dob the dob
     * @return the with fields
     * @throws Exception the exception
     */
    public Person getWithFields(String firstName, String lastName, String dob) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Person person = em.createNamedQuery("Person.findByFields", Person.class).setParameter("firstName", firstName).setParameter("lastName", lastName).setParameter("dateOfBirth", dob).getSingleResult();
            return person;
        } finally {
            em.close();
        }
    }

    /**
     * Gets the.
     *
     * @param person_ID the person ID
     * @return the person
     * @throws Exception the exception
     */
    public Person get(int person_ID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Person person = em.find(Person.class, person_ID);
            return person;
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
    public void update(Person comp) throws Exception {
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
     * @return the person
     * @throws Exception the exception
     */
    public Person insert(Person add) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            EmergencyContact contact = add.getEmergencyContactID();
            contact.getPersonList().add(add);
            trans.begin();
            em.persist(add);
            em.merge(contact);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
            return add;
        }
    }

}
