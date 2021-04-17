/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.Logins;
import javax.persistence.EntityManager;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDB.
 *
 * @author 844817
 */
public class UserDB {

    /** The em. */
    EntityManager em = DBUtil.getEmFactory().createEntityManager();

    /**
     * Gets the user.
     *
     * @param username the username
     * @return the user
     */
    public Logins getUser(String username) {

        try {
            Logins user = em.createNamedQuery("Logins.getUser", Logins.class).setParameter("username", username).getSingleResult();
            return user;
        } finally {
            em.close();
        }
    }

    /**
     * Gets the.
     *
     * @param companyID the company ID
     * @return the logins
     */
    public Logins get(int companyID) {

        try {
            Logins user = em.find(Logins.class, companyID);
            return user;
        } finally {
            em.close();
        }
    }

}
