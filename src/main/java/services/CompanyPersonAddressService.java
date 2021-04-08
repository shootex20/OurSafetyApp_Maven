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

/**
 *
 * @author Chels
 */
public class CompanyPersonAddressService {
    
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
        public void link (int compAddressID, Address addressID, CompanyPerson companyPersonID) throws Exception {
        CompanyPersonAddressDB addDB = new CompanyPersonAddressDB();
        CompanyPersonAddress addEdit = addDB.getID(compAddressID);
        addEdit.setCompanyPersonID(companyPersonID);
        addEdit.setAddressID(addressID);

        addDB.update(addEdit);
    }
}
