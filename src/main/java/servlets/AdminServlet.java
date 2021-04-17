package servlets;

import dataaccess.CompanyDB;
import dataaccess.LoginDB;
import domain.Company;
import domain.Logins;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.CompanyService;
import services.LoginService;
import services.PasswordStorage;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminServlet.
 *
 * @author Chels
 */
public class AdminServlet extends HttpServlet {

    /**
     * Do get.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CompanyDB cs = new CompanyDB();
        CompanyService compservice = new CompanyService();
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        //Synchronizer Token Pattern used to lower the risk of being attacked through CSRF.
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        request.getSession().setAttribute("token", token);
        request.setAttribute("token", token);

        String logout = request.getParameter("logout");

        //sends user to logout page 
        if (logout != null) {
            session.invalidate();
            session = request.getSession();
            response.sendRedirect("login");
            return;
        }

        //hides edit section until its clicked.
        if (action != null && action.equals("view")) {
            Integer selectedCompany = Integer.parseInt(request.getParameter("selectedCompany"));
           
            try {
                Company comp = cs.get(selectedCompany);
                String account = comp.getAccount();
                String industry = comp.getIndustry();
                 if(account == null || industry == null){
                            if(account == null){
                                account = "N/A";
                            }
                            if(industry == null){
                                industry = "N/A";
                            }               
                request.setAttribute("selectedComp", comp);
                 }
                 else{
                     request.setAttribute("selectedComp", comp);
                 }
                
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Company> comp = new ArrayList<Company>();

        try {
            comp = cs.getAll();
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("company", comp);

        // for logins 
        LoginDB pdb = new LoginDB();

        String actionM = request.getParameter("actionM");
        
        List<Logins> loginUser = new ArrayList<Logins>();

        List<Logins> activeUsers = new ArrayList<Logins>();
        List<Logins> inactiveUsers = new ArrayList<Logins>();

        try {

            loginUser = pdb.getAll();
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("logins", loginUser);
        Character activeUser = 'T';
        Character inActiveUser = 'F';

        //this is to update the active and inactive tables
        for (int i = 0; i < loginUser.size(); i++) {

            if (loginUser.get(i).getIsActive().equals(inActiveUser)) {
                inactiveUsers.add(loginUser.get(i));
            } else if (loginUser.get(i).getIsActive().equals(activeUser)) {
                activeUsers.add(loginUser.get(i));
            }

        }

        request.setAttribute("inActiveManagers", inactiveUsers);
        request.setAttribute("activeManagers", activeUsers);

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

    }

    /**
     * Do post.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CompanyService cs = new CompanyService();
        LoginService ls = new LoginService();

        String tokenInput = request.getParameter("token");

        if (tokenInput != null) {
            String tokenSession = (String) request.getSession().getAttribute("token");

            if (tokenInput.equals(tokenSession)) {

                String action = request.getParameter("action");

                Date dateAdded = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String tempDate = sdf.format(dateAdded);

                try {
                    dateAdded = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);
                } catch (ParseException ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                String compname = request.getParameter("compname");
                String shortname = request.getParameter("shortname");
                String description = request.getParameter("description");
                String account = request.getParameter("account");
                String industry = request.getParameter("industry");

                try {
                    //to delete companies
                    if (action.equals("delete")) {
                        Integer selectedCompany = Integer.parseInt(request.getParameter("selectedCompany"));
                        cs.delete(selectedCompany);
                        request.setAttribute("message", "Company Deleted.");
                        doGet(request, response);
                    } 
                    //to edit companies
                    else if (action.equals("edit")) {

                        Integer id = Integer.parseInt(request.getParameter("id"));
                        cs.update(id, compname, shortname, description, account, industry);
                        request.setAttribute("message", "Changes Saved.");
                        doGet(request, response);
                    } // to add companies
                    else if (action.equals("add")) {
                        cs.insert(dateAdded, compname, shortname, description, account, industry);
                        request.setAttribute("message", "Company Added.");
                        doGet(request, response);
                    }
                } catch (Exception ex) {
                    request.setAttribute("Errormessage", "An error occured.");
                }

                CompanyDB compID = new CompanyDB();

                List<Company> comps = new ArrayList<Company>();
                try {
                    comps = (List<Company>) cs.getAll();
                } catch (Exception ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("company", comps);

                // for logins
                Logins logins = new Logins();
                Company compM = logins.getCompanyID();
                String actionM = request.getParameter("actionM");
                LoginDB loginDB = new LoginDB();

                // variables
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");

                String active = request.getParameter("isActive");
                String admin = request.getParameter("isAdmin");
                // if statement if active/admin is null
                Character isActive;
                Character isAdmin;

                try {
                    //to delete managers
                    if (actionM.equals("deleteM")) {
                        Integer selectedManager = Integer.parseInt(request.getParameter("selectedMan"));

                        Logins userToDelete = new Logins(); //child object

                        try {

                            userToDelete = loginDB.get(selectedManager);
                            char notActive = 'F';
                            char isActiveTrue = 'T';
                            char status = userToDelete.getIsActive();
                            String st = Character.toString(status);

                            if (userToDelete == null) {
                                request.setAttribute("messageManager", "Error, could not deactivate user.");
                            } else if (userToDelete != null && !st.isEmpty()) {
                                userToDelete.setIsActive(notActive);
                                loginDB.delete(userToDelete);
                                request.setAttribute("messageManager", "Manager Deactivated.");

                            }
                            doGet(request, response);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);

                        }
                    } //to reactivate managers
                    else if (actionM.equals("Reactivate")) {
                        Integer selectedManagerReactivate = Integer.parseInt(request.getParameter("selectedManReactivate"));

                        Logins userToReactivate = new Logins(); //child object

                        try {

                            userToReactivate = loginDB.get(selectedManagerReactivate);

                            char isActiveTrue = 'T';
                            char status = userToReactivate.getIsActive();
                            String st = Character.toString(status);

                            if (userToReactivate == null) {
                                request.setAttribute("messageManager", "Error, could not reactivate user.");
                            } else if (userToReactivate != null && !st.isEmpty()) {
                                userToReactivate.setIsActive(isActiveTrue);
                                loginDB.delete(userToReactivate);
                                request.setAttribute("messageManager", "Manager Reactivated.");
                            }
                            doGet(request, response);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);

                        }

                    } //to add managers
                    else if (actionM.equals("addUser") & (admin != null && active != null)) {
                        int ss = Integer.parseInt(request.getParameter("userCompanyID"));
                        Company cc = new Company();

                        CompanyDB companydb = new CompanyDB();
                        try {
                            cc = companydb.get(ss);

                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        isActive = active.charAt(0);
                        isAdmin = admin.charAt(0);

                        String hash = null;

                        //To hash the passwords.
                        PasswordStorage ps = new PasswordStorage();

                        try {
                            hash = ps.createHash(password);
                        } catch (PasswordStorage.CannotPerformOperationException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        ls.insert(dateAdded, username, hash, cc, isActive, isAdmin);
                        request.setAttribute("messageManager", "Manager Added.");
                        doGet(request, response);
                    }
                } catch (Exception ex) {
                    request.setAttribute("messageManager", "An error occured.");
                }

                List<Logins> user = new ArrayList<Logins>();

                try {

                    user = (List<Logins>) ls.getAll();
                } catch (Exception ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("logins", user);

            }
        }
    }

}