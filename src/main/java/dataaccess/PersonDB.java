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

/**
 *
 * @author Chelsey Coughlin
 */
public class PersonDB {

    /**
     *
     * @return
     * @throws Exception
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
     *
     * @param firstName
     * @param lastName
     * @param dob
     * @return
     * @throws Exception
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
     *
     * @param person_ID
     * @return
     * @throws Exception
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
     *
     * @param comp
     * @throws Exception
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
     *
     * @param add
     * @return
     * @throws Exception
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
