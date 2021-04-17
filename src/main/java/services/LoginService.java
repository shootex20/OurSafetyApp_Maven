/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CompanyDB;
import dataaccess.LoginDB;
import dataaccess.UserDB;
import domain.Company;
import domain.Logins;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginService.
 *
 * @author 844817
 */
public class LoginService {

    /** The login DB. */
    private LoginDB loginDB;
    
    /** The company DB. */
    private CompanyDB companyDB;

    /**
     * Instantiates a new login service.
     */
    public LoginService() {
        loginDB = new LoginDB();
    }

    /**
     * Gets the.
     *
     * @param userID the user ID
     * @return the logins
     * @throws Exception the exception
     */
    public Logins get(Integer userID) throws Exception {
        return loginDB.get(userID);
    }

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public List<Logins> getAll() throws Exception {
        return loginDB.getAll();
    }

    /**
     * Insert.
     *
     * @param dateAdded the date added
     * @param username the username
     * @param password the password
     * @param companyID the company ID
     * @param isActive the is active
     * @param isAdmin the is admin
     * @return the int
     * @throws Exception the exception
     */
    public int insert(Date dateAdded, String username, String password, Company companyID, Character isActive, Character isAdmin) throws Exception {
        Logins user = new Logins(dateAdded, username, password, companyID, isActive, isAdmin);
        return loginDB.insert(user);
    }

    /**
     * Delete.
     *
     * @param userID the user ID
     * @param username the username
     * @throws Exception the exception
     */
    public void delete(Logins userID, String username) throws Exception {
      Logins person = userID;
      person.setUsername(username);
        loginDB.delete(person);  
        
    }

    /**
     * Gets the company ID.
     *
     * @param categoryID the category ID
     * @return the company ID
     * @throws Exception the exception
     */
    public Company getCompanyID(int categoryID) throws Exception {
        return companyDB.get(categoryID);

    }

    /**
     * Update password.
     *
     * @param userName the user name
     * @param tempPassword the temp password
     */
    public void updatePassword(String userName, String tempPassword) {

        UserDB udb = new UserDB();
        Logins user = udb.getUser(userName);

        try {
            String hash = PasswordStorage.createHash(tempPassword);
            user.setPassword(hash);
            loginDB.update(user);
        } catch (PasswordStorage.CannotPerformOperationException ex) {
        } catch (Exception ex) {
        }
    }

}