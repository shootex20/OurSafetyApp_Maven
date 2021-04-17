package services;

import dataaccess.UserDB;
import domain.Logins;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountService.
 *
 * @author 844817
 */
public class AccountService {

    /** The user DB. */
    UserDB userDB = new UserDB();
    
    /** The ps. */
    PasswordStorage ps = new PasswordStorage();

    /**
     * Login.
     *
     * @param username the username
     * @param password the password
     * @return the logins
     */
    public Logins login(String username, String password) {

        try {
            Logins user = userDB.getUser(username);

            if ((password.equals(user.getPassword()) && user.getUsername().equals("admin")) || (password.equals(user.getPassword()) && user.getUsername().equals("manager2")) || (password.equals(user.getPassword()) && user.getUsername().equals("oursafetyapplication@gmail.com"))) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", username);
                return user;

            } else if (ps.verifyPassword(password, user.getPassword()) == true) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", username);
                return user;
            }
        } catch (Exception e) {
        }
        return null;
    }

}