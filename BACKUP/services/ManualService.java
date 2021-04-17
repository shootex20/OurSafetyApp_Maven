/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ManualDB;
import domain.Manual;
import domain.TypeLibrary;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 809968
 */
public class ManualService {
    ManualDB manualDB;
    
    /**
     *
     */
    public ManualService(){
        manualDB = new ManualDB();
    }
    
    /**
     *
     * @param manualID
     * @return
     * @throws Exception
     */
    public Manual get(int manualID) throws Exception{
        return manualDB.get(manualID);
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public List<Manual> getAll() throws Exception {
        return manualDB.getAll();
    }
      
    /**
     *
     * @param manualID
     * @param dateAdded
     * @param userAdded
     * @param typeLibraryID
     * @param title
     * @param intention
     * @param content
     * @throws Exception
     */
    public void update(int manualID, Date dateAdded, int userAdded,TypeLibrary typeLibraryID, String title, String intention, String content) throws Exception{
         Manual manual =  manualDB.get(manualID);
         manual.setDateAdded(dateAdded);
         manual.setUserAdded(userAdded);
         manual.setTypeLibraryID(typeLibraryID);
         manual.setTitle(title);
         manual.setContent(content);
         manual.setIntention(intention);   
         
         manualDB.update(manual);
      }
      
    /**
     *
     * @param manualID
     * @throws Exception
     */
    public void delete(int manualID) throws Exception {
        Manual deleteManual = manualDB.get(manualID);
        manualDB.delete(deleteManual);
    }
      
    /**
     *
     * @param dateAdded
     * @param userAdded
     * @param typeLibraryID
     * @param title
     * @param intention
     * @param content
     * @throws Exception
     */
    public void insert(Date dateAdded, int userAdded,TypeLibrary typeLibraryID, String title, String intention, String content) throws Exception {  
        
        Manual manual = new Manual();
        manual.setDateAdded(dateAdded);
        manual.setUserAdded(userAdded);
        manual.setTypeLibraryID(typeLibraryID);
        manual.setTitle(title);
        manual.setContent(content);
        manual.setIntention(intention);   
        
        manualDB.insert(manual);
    }
}
