/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import domain.ItemClass;



/**
 *
 * @author 813017
 */
public class ItemClassDB {


 
    public ItemClass get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            ItemClass item = em.find(ItemClass.class, id);
            return item;
        } finally { 
            em.close();
        }
    }
    
         public List<ItemClass> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<ItemClass> item = em.createNamedQuery("ItemClass.findAll", ItemClass.class).getResultList();
             return item;
    
        } finally {
            em.close();
        }
         }
}
