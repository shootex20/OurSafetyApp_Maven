/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CompanyPersonPhoneDB;
import domain.CompanyPerson;
import domain.CompanyPersonPhone;
import domain.Phone;
import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyPersonPhoneService.
 *
 * @author Chels
 */
public class CompanyPersonPhoneService {
    
    /**
     * Insert.
     *
     * @param userAdded the user added
     * @param companyPersonID the company person ID
     * @param phoneID the phone ID
     * @return the company person phone
     * @throws Exception the exception
     */
    public CompanyPersonPhone insert(Integer userAdded, CompanyPerson companyPersonID, Phone phoneID) throws Exception {
        CompanyPersonPhoneDB addDB = new CompanyPersonPhoneDB();
        
        Date dateAdded = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate = formatter.format(dateAdded);
        /*Formats the created date*/
        dateAdded = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);

        CompanyPersonPhone add = new CompanyPersonPhone(dateAdded, userAdded, companyPersonID, phoneID);
        addDB.insert(add);
        addDB.updatePerson(add);
//        addDB.updatePhone(add);
        return add;
    }
        
    
}
