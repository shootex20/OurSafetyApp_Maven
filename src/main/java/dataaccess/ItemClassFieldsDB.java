/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import domain.ItemClassFields;



// TODO: Auto-generated Javadoc
/**
 * The Class ItemClassFieldsDB.
 *
 * @author 813017
 */
public class ItemClassFieldsDB {
    
    /**
     * Gets the.
     *
     * @param id the id
     * @return the item class fields
     * @throws Exception the exception
     */
    public ItemClassFields get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            ItemClassFields item = em.find(ItemClassFields.class, id);
            return item;
        } finally { 
            em.close();
        }
    }
    
    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public List<ItemClassFields> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<ItemClassFields> item = em.createNamedQuery("ItemClassFields.findAll", ItemClassFields.class).getResultList();
             return item;
    
        } finally {
            em.close();
        }
    }
   
}
