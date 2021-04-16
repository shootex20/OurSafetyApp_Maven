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

/**
 *
 * @author Chels
 */
public class CompanypositionsService {
    
    /**
     *
     * @param posID
     * @param posTitle
     * @throws Exception
     */
    public void update(int posID, String posTitle) throws Exception {
        CompanyPositionsDB compPerDB = new CompanyPositionsDB();
        CompanyPositions compPos = compPerDB.get(posID);
        compPos.setPositionTitle(posTitle);
        compPerDB.update(compPos);
    }
        
    /**
     *
     * @param userAdded
     * @param positionTitle
     * @param companyPersonID
     * @param companyID
     * @return
     * @throws Exception
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
     *
     * @param posID
     * @param personID
     * @throws Exception
     */
    public void link(int posID, CompanyPerson personID) throws Exception {
        CompanyPositionsDB addDB = new CompanyPositionsDB();
        CompanyPositions compPersEdit = addDB.get(posID);
        compPersEdit.setCompanyPersonID(personID);
        addDB.update(compPersEdit);
    }
}
