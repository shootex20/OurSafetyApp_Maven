package dataaccess;

import java.sql.ResultSet;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// TODO: Auto-generated Javadoc
/**
 * The Class DBUtil.
 *
 * @author Chels
 */
public class DBUtil {
    
    /** The Constant emf. */
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("OurSafetyPU");

    /**
     * Gets the em factory.
     *
     * @return the em factory
     */
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }

    /**
     * Close result set.
     *
     * @param result the result
     */
    static void closeResultSet(ResultSet result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
