package dataaccess;

import java.sql.ResultSet;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Chels
 */
public class DBUtil {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("OurSafetyPU");

    /**
     *
     * @return
     */
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }

    static void closeResultSet(ResultSet result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
