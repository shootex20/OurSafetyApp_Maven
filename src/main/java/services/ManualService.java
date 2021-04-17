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

// TODO: Auto-generated Javadoc
/**
 * The Class ManualService.
 *
 * @author 809968
 */
public class ManualService {
    
    /** The manual DB. */
    ManualDB manualDB;
    
    /**
     * Instantiates a new manual service.
     */
    public ManualService(){
        manualDB = new ManualDB();
    }
    
    /**
     * Gets the.
     *
     * @param manualID the manual ID
     * @return the manual
     * @throws Exception the exception
     */
    public Manual get(int manualID) throws Exception{
        return manualDB.get(manualID);
    }
    
    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public List<Manual> getAll() throws Exception {
        return manualDB.getAll();
    }
      
    /**
     * Update.
     *
     * @param manualID the manual ID
     * @param dateAdded the date added
     * @param userAdded the user added
     * @param typeLibraryID the type library ID
     * @param title the title
     * @param intention the intention
     * @param content the content
     * @throws Exception the exception
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
     * Delete.
     *
     * @param manualID the manual ID
     * @throws Exception the exception
     */
    public void delete(int manualID) throws Exception {
        Manual deleteManual = manualDB.get(manualID);
        manualDB.delete(deleteManual);
    }
      
    /**
     * Insert.
     *
     * @param dateAdded the date added
     * @param userAdded the user added
     * @param typeLibraryID the type library ID
     * @param title the title
     * @param intention the intention
     * @param content the content
     * @throws Exception the exception
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
