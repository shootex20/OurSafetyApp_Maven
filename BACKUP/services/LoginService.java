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

/**
 *
 * @author 844817
 */
public class LoginService {

    private LoginDB loginDB;
    private CompanyDB companyDB;

    /**
     *
     */
    public LoginService() {
        loginDB = new LoginDB();
    }

    /**
     *
     * @param userID
     * @return
     * @throws Exception
     */
    public Logins get(Integer userID) throws Exception {
        return loginDB.get(userID);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public List<Logins> getAll() throws Exception {
        return loginDB.getAll();
    }

    /**
     *
     * @param dateAdded
     * @param username
     * @param password
     * @param companyID
     * @param isActive
     * @param isAdmin
     * @return
     * @throws Exception
     */
    public int insert(Date dateAdded, String username, String password, Company companyID, Character isActive, Character isAdmin) throws Exception {
        Logins user = new Logins(dateAdded, username, password, companyID, isActive, isAdmin);
        return loginDB.insert(user);
    }

    /**
     *
     * @param userID
     * @param username
     * @throws Exception
     */
    public void delete(Logins userID, String username) throws Exception {
      Logins person = userID;
      person.setUsername(username);
        loginDB.delete(person);  
        
    }

    /**
     *
     * @param categoryID
     * @return
     * @throws Exception
     */
    public Company getCompanyID(int categoryID) throws Exception {
        return companyDB.get(categoryID);

    }

    /**
     *
     * @param userName
     * @param tempPassword
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