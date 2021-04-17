/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CompanyPersonAddressDB;
import domain.Address;
import domain.CompanyPerson;
import domain.CompanyPersonAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyPersonAddressService.
 *
 * @author Chels
 */
public class CompanyPersonAddressService {
    
    /**
     * Insert.
     *
     * @param userAdded the user added
     * @param addressID the address ID
     * @param companyPersonID the company person ID
     * @return the company person address
     * @throws Exception the exception
     */
    public CompanyPersonAddress insert(Integer userAdded, Address addressID, CompanyPerson companyPersonID) throws Exception {
        CompanyPersonAddressDB addDB = new CompanyPersonAddressDB();
        
        Date dateAdded = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate = formatter.format(dateAdded);
        /*Formats the created date*/
        dateAdded = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);

        CompanyPersonAddress add = new CompanyPersonAddress(dateAdded, userAdded, addressID, companyPersonID);
        addDB.insert(add);
        addDB.updatePerson(add);
        return add;
    }

    /**
     * Link.
     *
     * @param compAddressID the comp address ID
     * @param addressID the address ID
     * @param companyPersonID the company person ID
     * @throws Exception the exception
     */
    public void link (int compAddressID, Address addressID, CompanyPerson companyPersonID) throws Exception {
        CompanyPersonAddressDB addDB = new CompanyPersonAddressDB();
        CompanyPersonAddress addEdit = addDB.getID(compAddressID);
        addEdit.setCompanyPersonID(companyPersonID);
        addEdit.setAddressID(addressID);

        addDB.update(addEdit);
    }
}
