/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CompanypersonDB;
import domain.Company;
import domain.CompanyPerson;
import domain.Person;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Chels
 */
public class CompanypersonService {
    
    /**
     *
     * @param personID
     * @param email
     * @throws Exception
     */
    public void update(CompanyPerson personID, String email) throws Exception {
        CompanypersonDB compPerDB = new CompanypersonDB();
        CompanyPerson compPersEdit = compPerDB.get(personID.getCompanyPersonID());
        compPersEdit.setEmail(email);
        compPerDB.update(compPersEdit);
    }
        
    /**
     *
     * @param userAdded
     * @param email
     * @param isEmployeeActive
     * @param companyID
     * @param personID
     * @return
     * @throws Exception
     */
    public CompanyPerson insert(Integer userAdded, String email, boolean isEmployeeActive, Company companyID, Person personID) throws Exception {
        CompanypersonDB addDB = new CompanypersonDB();
        
        Date dateAdded = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate = formatter.format(dateAdded);
        /*Formats the created date*/
        dateAdded = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);

        CompanyPerson add = new CompanyPerson(dateAdded, userAdded, email, isEmployeeActive, companyID, personID);
        addDB.insert(add);
        return add;
    }
        
    /**
     *
     * @param personID
     * @param email
     * @throws Exception
     */
    public void link(CompanyPerson personID, String email) throws Exception {
        CompanypersonDB compPerDB = new CompanypersonDB();
        CompanyPerson compPersEdit = compPerDB.get(personID.getCompanyPersonID());
        compPersEdit.setEmail(email);
        compPerDB.update(compPersEdit);
    }
}
