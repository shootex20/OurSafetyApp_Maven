/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AddressDB;
import dataaccess.CompanyPersonAddressDB;
import domain.Address;
import domain.CompanyPerson;
import domain.CompanyPersonAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressService.
 *
 * @author Chels
 */
public class AddressService {
    
    /**
     * Update.
     *
     * @param addressID the address ID
     * @param addressLine1 the address line 1
     * @param addressLine2 the address line 2
     * @param addressCity the address city
     * @param addressProvince the address province
     * @param addressPostal the address postal
     * @param addressCountry the address country
     * @throws Exception the exception
     */
    public void update(int addressID, String addressLine1, String addressLine2, String addressCity, 
                String addressProvince,String addressPostal, String addressCountry) throws Exception {
        AddressDB addDB = new AddressDB();
        Address addEdit = addDB.get(addressID);


        addEdit.setAddressLine1(addressLine1);
        addEdit.setAddressLine2(addressLine2);
        addEdit.setCity(addressCity);
        addEdit.setProvince(addressProvince);
        addEdit.setPostalCode(addressPostal);
        addEdit.setCountry(addressCountry);
        addDB.update(addEdit);
    }
        
    /**
     * Insert.
     *
     * @param userAdded the user added
     * @param addressLine1 the address line 1
     * @param addressLine2 the address line 2
     * @param city the city
     * @param province the province
     * @param country the country
     * @param postalCode the postal code
     * @return the address
     * @throws Exception the exception
     */
    public Address insert(Integer userAdded, String addressLine1, String addressLine2, String city, String province, String country, String postalCode) throws Exception {
        AddressDB addDB = new AddressDB();
        
        Date dateAdded = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate = formatter.format(dateAdded);
        /*Formats the created date*/
        dateAdded = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);

        Address add = new Address(dateAdded, userAdded, addressLine1, addressLine2, city, province, country, postalCode);
        addDB.insert(add);
        return add;
    }
        /*
        
        public void link (int addressID, Companypersonaddress address) throws Exception {
        AddressDB addDB = new AddressDB();
        Address addEdit = addDB.get(addressID);

        List<Companypersonaddress> cpList = addEdit.getCompanypersonaddressList();
        cpList.add(address);
        addEdit.setCompanypersonaddressList(cpList);
        addDB.update(addEdit);
    }
*/
}
