package servlets;

import dataaccess.CompanyDB;
import dataaccess.CompanypersonDB;
import dataaccess.LoginDB;
import dataaccess.PersonDB;
import domain.Address;
import domain.Company;
import domain.CompanyPerson;
import domain.CompanyPersonAddress;
import domain.CompanyPersonPhone;
import domain.CompanyPositions;
import domain.EmergencyContact;
import domain.Logins;
import domain.Person;
import domain.Phone;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AddressService;
import services.CompanyPersonAddressService;
import services.CompanyPersonPhoneService;
import services.CompanypersonService;
import services.CompanypositionsService;
import services.EmergencyContactService;
import services.PersonService;
import services.PhoneService;

/**
 *
 * @author 809968
 */
public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");

        int userID = (Integer) session.getAttribute("userID");
        Logins logins = new Logins();
        String logout = request.getParameter("logout");
        String company = request.getParameter("company");
        String equipment = request.getParameter("equipment");
        String companyWelcome = request.getParameter("companyWelcome");
        String manual = request.getParameter("manual");
        LoginDB logindb = new LoginDB();

        try {
            List<Logins> loginList = logindb.getAll();
            for (Logins login : loginList) {
                if (login.getUserId() == userID) {
                    logins = login;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(companyWelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Company curr = logins.getCompanyID();
        
        CompanypersonDB compPerDB = new CompanypersonDB();
        
        
        if(logins.getCompanyID().getCompanyID() == null)
        {
           request.setAttribute("companyName", "Welcome Motive Safety Admin!"); 
        }
        else
        {
        request.setAttribute("companyName", logins.getCompanyID().getDescription());
        }
        
        try {
            if (company != null) {
                response.sendRedirect("company");
                return;
            } else if (equipment != null) {
                response.sendRedirect("equipmentmanager");
                return;
            } else if (companyWelcome != null) {
                response.sendRedirect("companyWelcome");
                return;
            } else if (manual != null) {
                response.sendRedirect("manual");
                return;
            } else if (logout != null) {
                session.invalidate();
                session = request.getSession();
                response.sendRedirect("login");
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
                if (action != null && action.equals("view")){
                String selectedEmp = request.getParameter("edactive");
            try {
                CompanyPerson toEdit = compPerDB.get(Integer.parseInt(selectedEmp));
                request.setAttribute("user", toEdit);
                char gen = toEdit.getPersonID().getGender();
                String gender = Character.toString(gen);
                request.setAttribute("gender", gender);
                
                java.util.Date date = toEdit.getPersonID().getDateOfBirth();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String format = formatter.format(date);
                request.setAttribute("birthday", format);
            } catch (Exception ex) {
                Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        else if (action != null && action.equals("view1")) {
          String selectedEmp = request.getParameter("edinactive"); 
          
            try {
                CompanyPerson toEdit = compPerDB.get(Integer.parseInt(selectedEmp));
                request.setAttribute("user", toEdit);
                char gen = toEdit.getPersonID().getGender();
                String gender = Character.toString(gen);
                request.setAttribute("gender", gender);
                //Formats the date
                java.util.Date date = toEdit.getPersonID().getDateOfBirth();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String format = formatter.format(date);
                request.setAttribute("birthday", format);
            } catch (Exception ex) {
                Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        List<CompanyPerson> compPersonList = new ArrayList<CompanyPerson>();
        List<CompanyPerson> compPersonListNotActive = new ArrayList<CompanyPerson>();
        List<CompanyPerson> compPersonListActive = new ArrayList<CompanyPerson>();
        
        try {
            compPersonList = (List<CompanyPerson>) compPerDB.getAll(curr);
        } catch (Exception ex) {
            Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < compPersonList.size(); i++)
        {
            if(compPersonList.get(i).getIsEmployeeActive() == false)
            {
                compPersonListNotActive.add(compPersonList.get(i));
            }
            else if (compPersonList.get(i).getIsEmployeeActive() == true)
            {
                compPersonListActive.add(compPersonList.get(i));
            }
        }
        
        request.setAttribute("employeeList", compPersonListActive);
        request.setAttribute("inActiveEmployeeList", compPersonListNotActive);

        getServletContext().getRequestDispatcher("/WEB-INF/employee.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        int userID = (Integer) session.getAttribute("userID");
        Logins logins = new Logins();
        LoginDB logindb = new LoginDB();

        try {
            List<Logins> loginList = logindb.getAll();
            for (Logins login : loginList) {
                if (login.getUserId() == userID) {
                    logins = login;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(companyWelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Company curr = logins.getCompanyID();

        /**Database stuff, do not touch.**/
        CompanypersonDB compPersonDB = new CompanypersonDB();
        PersonDB personDB = new PersonDB();
                
        if(action.equals("Add"))  
        {
            PhoneService ps = new PhoneService();
            CompanyPersonPhoneService cpps = new CompanyPersonPhoneService();
            AddressService as = new AddressService();
            CompanyPersonAddressService cpas = new CompanyPersonAddressService();
            CompanypersonService cpers = new CompanypersonService();
            CompanypositionsService cps = new CompanypositionsService();
            EmergencyContactService ecs = new EmergencyContactService();
            PersonService pers = new PersonService();
            CompanyDB company = new CompanyDB();
            
            /*Person start*/
            String firstname = request.getParameter("comp_firstname");
            String lastname = request.getParameter("comp_lastname");
            String birthdate = request.getParameter("comp_birthday");

            String gender = request.getParameter("comp_gender");
            
            String email = request.getParameter("comp_email");
            /*Phone#*/
            String phonenum = request.getParameter("comp_phone");
            String phoneExt = request.getParameter("comp_phoneExt");
            /*Address*/
            String addressLine1 = request.getParameter("comp_addressLine1");
            String addressLine2 = request.getParameter("comp_addressLine2");
            String addressCity = request.getParameter("comp_city");
            String addressProvince = request.getParameter("comp_prov");
            String addressPostal = request.getParameter("comp_postal");
            String addressCountry = request.getParameter("comp_country");
            /*Position*/
            String position = request.getParameter("comp_pos");
            
            /*Emergency Contact*/
            String emerFirst = request.getParameter("emer_first");
            String emerLast = request.getParameter("emer_last");
            String emerPhone = request.getParameter("emer_phone");
            String emerRelation = request.getParameter("emer_relationship");

            try {
                EmergencyContact emergContact = ecs.insert(userID, emerFirst, emerLast, emerPhone, emerRelation);
                Person persToAdd = pers.insert(userID, firstname, lastname, birthdate, gender.charAt(0), emergContact);
                CompanyPerson compPers = cpers.insert(userID, email, true, curr, persToAdd);
                Address address = as.insert(userID, addressLine1, addressLine2, addressCity, addressProvince, addressPostal, addressCountry);
                Phone phone = ps.insert(userID, phonenum, phoneExt);
                CompanyPersonAddress compPerAdd = cpas.insert(userID, address, compPers);
                CompanyPersonPhone compPerPho = cpps.insert(userID, compPers, phone);
                CompanyPositions compPos = cps.insert(userID, position, compPers, curr);
                request.setAttribute("message", "New employee added!");
                doGet(request, response);  
            } catch (Exception ex) {
                Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);

            } 
        }

        else if (action.equals("Save"))
        {
            String compPerId = request.getParameter("compPerID");
            String perID = request.getParameter("perID");
            
            PhoneService ps = new PhoneService();
            AddressService as = new AddressService();
            CompanypersonService cpers = new CompanypersonService();
            CompanypositionsService cps = new CompanypositionsService();
            EmergencyContactService ecs = new EmergencyContactService();
            PersonService pers = new PersonService();
            

            /*Person start*/
            String firstname = request.getParameter("edcomp_firstname");
            String lastname = request.getParameter("edcomp_lastname");
            /*Cannot update this, will not work*/
            //String birthdate = request.getParameter("edcomp_birthday");
            String genderString = request.getParameter("edcomp_gender");
            char gender = genderString.charAt(0);
            
            String email = request.getParameter("edcomp_email");
            

            /*Phone#*/
            String phonenum = request.getParameter("edcomp_phone");
            String phoneExt = request.getParameter("edcomp_phoneExt");
                        
            /*Address*/
            String addressLine1 = request.getParameter("edcomp_addressLine1");
            String addressLine2 = request.getParameter("edcomp_addressLine2");
            String addressCity = request.getParameter("edcomp_city");
            String addressProvince = request.getParameter("edcomp_prov");
            String addressPostal = request.getParameter("edcomp_postal");
            String addressCountry = request.getParameter("edcomp_country");
            
            /*Position*/
            String position = request.getParameter("edcomp_pos");
            
            /*Emergency Contact*/
            String emerFirst = request.getParameter("edemer_first");
            String emerLast = request.getParameter("edemer_last");
            String emerPhone = request.getParameter("edemer_phone");
            String emerRelation = request.getParameter("edemer_relationship"); 
            
            String phoneID = request.getParameter("phoneID");
            String addressID = request.getParameter("addressID");
            String posID = request.getParameter("positionID");
            
            int phoneIDParse = Integer.parseInt(phoneID);
            int addressIDParse = Integer.parseInt(addressID);
            int positionIDParse = Integer.parseInt(posID);
            
            try {
                CompanyPerson compPerson = compPersonDB.get(Integer.parseInt(compPerId));
                Person person = personDB.get(Integer.parseInt(perID));
                
                pers.update(person.getPersonID(), firstname, lastname, gender);
                cpers.update(compPerson, email);
                ps.update(phoneIDParse, phonenum, phoneExt);
                as.update(addressIDParse, addressLine1, addressLine2, addressCity, addressProvince, addressPostal, addressCountry);
                cps.update(positionIDParse, position);
                ecs.update(person, emerFirst, emerLast, emerPhone, emerRelation);
            } catch (Exception ex) {
                Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", ex);
                doGet(request, response);
            }
        doGet(request, response);
        }
        else if (action.equals("DeactivateEmployee"))
        {
            String companyPersonIDString = request.getParameter("hidden_da_cp");
            String personIDString = request.getParameter("hidden_da_person");
            
            int companyPersonID = Integer.parseInt(companyPersonIDString);
            int personID = Integer.parseInt(personIDString);
                
            CompanyPerson cpToDeactivate = new CompanyPerson();
            Person personToDeactivate = new Person();
            
            
            
            try {
                
                personToDeactivate = personDB.get(personID);
                cpToDeactivate = compPersonDB.get(companyPersonID);
                
                if(personToDeactivate == null && cpToDeactivate == null)
                {
                request.setAttribute("message", "Error, could not deactivate user.");
                doGet(request, response);  
                }
                else
                {
                cpToDeactivate.setIsEmployeeActive(false);
                compPersonDB.update(cpToDeactivate);
                request.setAttribute("message", "Successfully Deactivated: " + personToDeactivate.getFirstName() + " " + personToDeactivate.getLastName());
                doGet(request, response);  
                }      
                } catch (Exception ex) {
                    Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("message", ex);
                     doGet(request, response);   
                }
            }
            else if (action.equals("ActivateEmployee"))
            {
            String companyPersonIDString = request.getParameter("hidden_ra_cp");
            String personIDString = request.getParameter("hidden_ra_person");
            
            int companyPersonID = Integer.parseInt(companyPersonIDString);
            int personID = Integer.parseInt(personIDString);
                
            CompanyPerson cpToReactivate = new CompanyPerson();
            Person personToReactivate = new Person();
            
            try {
                
                personToReactivate = personDB.get(personID);
                cpToReactivate = compPersonDB.get(companyPersonID);
                
                if(personToReactivate == null && cpToReactivate == null)
                {
                request.setAttribute("message", "Error, could not reactivate user.");
                doGet(request, response);  
                }
                else
                {
                cpToReactivate.setIsEmployeeActive(true);
                compPersonDB.update(cpToReactivate);
                request.setAttribute("message", "Successfully Reactivated: " + personToReactivate.getFirstName() + " " + personToReactivate.getLastName());
                doGet(request, response);  
                }      
                } catch (Exception ex) {
                    Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("message", ex);
                    doGet(request, response);   
                }
            }
        }
    
    }

