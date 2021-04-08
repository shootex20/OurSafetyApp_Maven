/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import domain.TypeLibrary;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 *
 * @author 813017
 */
public class TypeLibraryDB {
    
        public TypeLibrary get(int type_ID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            TypeLibrary type = em.find(TypeLibrary.class, type_ID);
            return type;
        } finally { 
            em.close();
        }
    }

     public List<TypeLibrary> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<TypeLibrary> comp = em.createNamedQuery("TypeLibrary.findAll", TypeLibrary.class).getResultList();
             return comp;
    
        } finally {
            em.close();
        }
        
    }
}
