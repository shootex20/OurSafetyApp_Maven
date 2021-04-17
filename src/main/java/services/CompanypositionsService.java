/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CompanyPositionsDB;
import dataaccess.CompanypersonDB;
import domain.Company;
import domain.CompanyPerson;
import domain.CompanyPositions;
import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanypositionsService.
 *
 * @author Chels
 */
public class CompanypositionsService {
    
    /**
     * Update.
     *
     * @param posID the pos ID
     * @param posTitle the pos title
     * @throws Exception the exception
     */
    public void update(int posID, String posTitle) throws Exception {
        CompanyPositionsDB compPerDB = new CompanyPositionsDB();
        CompanyPositions compPos = compPerDB.get(posID);
        compPos.setPositionTitle(posTitle);
        compPerDB.update(compPos);
    }
        
    /**
     * Insert.
     *
     * @param userAdded the user added
     * @param positionTitle the position title
     * @param companyPersonID the company person ID
     * @param companyID the company ID
     * @return the company positions
     * @throws Exception the exception
     */
    public CompanyPositions insert(Integer userAdded, String positionTitle, CompanyPerson companyPersonID, Company companyID) throws Exception {
        CompanyPositionsDB addDB = new CompanyPositionsDB();
        
        Date dateAdded = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate = formatter.format(dateAdded);
        /*Formats the created date*/
        dateAdded = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);

        CompanyPositions add = new CompanyPositions(dateAdded, userAdded, positionTitle, companyPersonID, companyID);
        addDB.insert(add);
        return add;
    }
        
    /**
     * Link.
     *
     * @param posID the pos ID
     * @param personID the person ID
     * @throws Exception the exception
     */
    public void link(int posID, CompanyPerson personID) throws Exception {
        CompanyPositionsDB addDB = new CompanyPositionsDB();
        CompanyPositions compPersEdit = addDB.get(posID);
        compPersEdit.setCompanyPersonID(personID);
        addDB.update(compPersEdit);
    }
}
