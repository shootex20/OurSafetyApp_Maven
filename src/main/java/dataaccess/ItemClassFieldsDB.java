/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import domain.ItemClassFields;



/**
 *
 * @author 813017
 */
public class ItemClassFieldsDB {
    
    public ItemClassFields get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            ItemClassFields item = em.find(ItemClassFields.class, id);
            return item;
        } finally { 
            em.close();
        }
    }
    
        
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
