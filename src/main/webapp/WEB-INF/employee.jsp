<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            <%@include file="css/employee.css" %>
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee JSP</title>
    </head>
    <body>  
        <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>

        <header class="employeeHeader">
            <ul>
                <li><a href="companyWelcome?companyWelcome">${companyName} Welcome Page</a></li>
                <li><a href="equipmentmanager">Equipment Page</a></li>
                <li><a href="manual?manual">Manual Page</a></li>
                <li><a href="employee?logout=logout">Logout</a></li>     
            </ul>
        </header>
        <div class="companyEmployees">
            <h1>${companyName} & OurSafety</h1>
            <h3>Current Active Employee List</h3>
            <table>

                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth date</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Position</th>
                    <th>Edit</th>
                    <th>Deactivate</th>
                    <th>Emergency Contact</th>
                </tr>

                <c:forEach  var="emp" items="${employeeList}">
                    <tr>
                        <th>${emp.personID.firstName}</th>
                        <th>${emp.personID.lastName}</th>
                        <th><fmt:formatDate value="${emp.personID.dateOfBirth}" pattern="dd/MMM/YYYY"/></th>
                        <th>${emp.personID.gender}</th>
                        <th>${emp.email}</th>

                        <c:if test="${empty emp.companyPersonPhoneList}">
                            <th> </th>
                            </c:if>

                        <c:if test="${not empty emp.companyPersonPhoneList}">
                            <c:forEach  var="phone" items="${emp.companyPersonPhoneList}">
                                <th>
                                    <c:if test="${phone.companyPersonID eq emp}">
                                        ${phone.phoneID.countryCode}-${phone.phoneID.areaCode}-${phone.phoneID.localNumber} <c:if test="${not empty phone.phoneID.extension}"> ext:${phone.phoneID.extension} </c:if>
                                    </c:if>
                                </th>
                            </c:forEach>
                        </c:if>

                        <c:if test="${empty emp.companyPersonAddressList}">
                            <th></th>
                            </c:if>


                        <c:if test="${not empty emp.companyPersonAddressList}">
                            <c:forEach  var="add" items="${emp.companyPersonAddressList}">
                                <th>
                                    ${add.addressID.addressLine1} ${add.addressID.addressLine2}, ${add.addressID.city}, ${add.addressID.province} ${add.addressID.postalCode}, ${add.addressID.country}
                                </th>
                            </c:forEach>
                        </c:if>
                        <c:forEach var="pos" items="${emp.companyPositionsList}">
                            <th>${pos.positionTitle}</th>
                            </c:forEach>
                        <th>
                            <form action="employee" method="get">
                                <input type="hidden" name="edactive" value="${emp.companyPersonID}">
                                <input type="hidden" name="action" value="view">
                                <input type="submit" value="Edit">
                            </form>
                        </th>
                        <th>
                            <form action="employee" method="post">
                                <input type="hidden" name="hidden_da_cp" value="${emp.companyPersonID}">
                                <input type="hidden" name="hidden_da_person" value="${emp.personID.personID}">
                                <input type="hidden" name="action" value="DeactivateEmployee">
                                <input type="hidden" name="token" value="<c:out value="${token}"/>">
                                <input type="submit" value="Deactivate" onclick="return confirm('Are you sure you want to deactivate ${emp.personID.firstName} ${emp.personID.lastName}?')">
                            </form>
                        </th>
                        <th>
                            <input type="button" value="Emergency Contact" onclick="alert('Firstname: ${emp.personID.emergencyContactID.emergencyContactFirstName}\nLastname: ${emp.personID.emergencyContactID.emergencyContactLastName}\nContact Number: ${emp.personID.emergencyContactID.emergencyContactNumber}\nRelationship: ${emp.personID.emergencyContactID.emergencyContactRelationship}')">
                        </th>
                    </tr>
                </c:forEach>
            </table>
            <h3>Current Inactive Employee List</h3>
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth date</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Position</th>
                    <th>Edit</th>
                    <th>Activate</th>
                    <th>Emergency Contact</th>
                </tr>

                <c:forEach  var="empInActive" items="${inActiveEmployeeList}">
                    <tr>
                        <th>${empInActive.personID.firstName}</th>
                        <th>${empInActive.personID.lastName}</th>
                        <th><fmt:formatDate value="${empInActive.personID.dateOfBirth}" pattern="dd/MMM/YYYY"/></th>
                        <th>${empInActive.personID.gender}</th>
                        <th>${empInActive.email}</th>

                        <c:if test="${empty empInActive.companyPersonPhoneList}">
                            <th> </th>
                            </c:if>

                        <c:if test="${not empty empInActive.companyPersonPhoneList}">
                            <c:forEach  var="phone" items="${empInActive.companyPersonPhoneList}">
                                <th>
                                    <c:if test="${phone.companyPersonID eq empInActive}">
                                        ${phone.phoneID.countryCode}-${phone.phoneID.areaCode}-${phone.phoneID.localNumber}-${phone.phoneID.extension}
                                    </c:if>
                                </th>
                            </c:forEach>
                        </c:if>

                        <c:if test="${empty empInActive.companyPersonAddressList}">
                            <th></th>
                            </c:if>

                        <c:if test="${not empty empInActive.companyPersonAddressList}">
                            <c:forEach  var="add" items="${empInActive.companyPersonAddressList}">
                                <th>
                                    ${add.addressID.addressLine1} ${add.addressID.addressLine2}, ${add.addressID.city}, ${add.addressID.province} ${add.addressID.postalCode}, ${add.addressID.country}
                                </th>
                            </c:forEach>
                        </c:if>
                        <c:forEach var="pos" items="${empInActive.companyPositionsList}">
                            <th>${pos.positionTitle}</th>
                            </c:forEach>
                        <th>
                            <form action="employee" method="get">
                                <input type="hidden" name="edinactive" value="${empInActive.companyPersonID}">
                                <input type="hidden" name="action" value="view1">
                                <input type="submit" value="Edit">
                            </form>
                        </th>
                        <th>
                            <form action="employee" method="post">
                                <input type="hidden" name="hidden_ra_cp" value="${empInActive.companyPersonID}">
                                <input type="hidden" name="hidden_ra_person" value="${empInActive.personID.personID}">
                                <input type="hidden" name="action" value="ActivateEmployee">
                                <input type="hidden" name="token" value="<c:out value="${token}"/>">
                                <input type="submit" value="Activate" onclick="return confirm('Are you sure you want to reactivate ${empInActive.personID.firstName} ${empInActive.personID.lastName}?')">
                            </form>
                        </th>
                        <th>
                            <input type="button" value="Emergency Contact" onclick="alert('Firstname: ${empInActive.personID.emergencyContactID.emergencyContactFirstName}\nLastname: ${empInActive.personID.emergencyContactID.emergencyContactLastName}\nContact Number: ${empInActive.personID.emergencyContactID.emergencyContactNumber}\nRelationship: ${empInActive.personID.emergencyContactID.emergencyContactRelationship}')">
                        </th>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
        ${message}
        <br>
        <c:if test="${user == null}">
            <h3>Add a new employee</h3>
            <form method="POST" class="companyAddForm">
                <input type="hidden" name="hidden_comp_emp_add" value="hiddenCompany"><br>
                <input type="hidden" name="token" value="<c:out value="${token}"/>">
                <label>First Name </label><input required type="text" name="comp_firstname"><br>
                <label>Last Name </label><input required type="text" name="comp_lastname"><br>
                <label>Birth Date </label><input required type="date" name="comp_birthday"><br>
                <%--
                <label>Gender</label><input required type="text" name="comp_gender"><br>
                --%>
                <label>Gender </label>
                <select name="comp_gender" id="comp_gender">
                    <option value="F">Female</option>
                    <option value="M">Male</option>
                    <option value="O">Other</option>
                </select><br>
                <label>Phone Number </label><input required pattern="[0-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{4}" type="tel" name="comp_phone" placeholder="123-456-7890"><br>
                <label>Phone Ext </label><input type="tel" name="comp_phoneExt" placeholder="7890"><br>
                <label>Email </label><input required type="email" name="comp_email" placeholder="Ex: address@service.com"><br>
                <label>Address Line 1 </label><input required type="text" name="comp_addressLine1"><br>
                <label>Address Line 2 </label><input type="text" name="comp_addressLine2"><br>  
                <label>City </label><input required type="text" name="comp_city"><br>
                <label>Province </label><input required type="text" name="comp_prov"><br>
                <label>Postal Code </label><input required type="text" name="comp_postal"><br>
                <label>Country </label><input required type="text" name="comp_country"><br>
                <label>Position </label><input required type="text" name="comp_pos"><br>
                <h3>Emergency Contact Details</h3>
                <label>First name </label><input required type="text" name="emer_first"><br>
                <label>Last name </label><input required type="text" name="emer_last"><br>
                <label>Phone Number </label><input required type="tel" name="emer_phone" pattern="[0-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{4}" placeholder="1234567890"><br>
                <label>Relationship </label><input required type="text" name="emer_relationship"><br>
                <input type="submit" name="action" value="Add">
                ${compAddMsg}
            </form>
        </c:if>

        <c:if test="${user != null}">
            <h3>Edit employee</h3>

            <form action="employee"  method="POST" class="companyAddForm">
                <input type="hidden" name="compPerID" value="${user.companyPersonID}">
                <input type="hidden" name="perID" value="${user.personID.personID}">
                <input type="hidden" name="hidden_comp_emp_add" value="hiddenCompany"><br>
                <input type="hidden" name="token" value="<c:out value="${token}"/>">

                <label>First Name </label><input required type="text" name="edcomp_firstname" value="${user.personID.firstName}"><br>
                <label>Last Name </label><input required type="text" name="edcomp_lastname" value="${user.personID.lastName}"><br>
                <label>Birth Date </label><input required type="date" name="edcomp_birthday" value="${birthday}"><br>

                <input type="hidden" name="gender" value="${genderOfPerson}">

                <c:if test="${gender == 'F'}">
                    <label>Gender</label>
                    <select name="edcomp_gender" var="gen" id="edcomp_gender">
                        <option value="F" selected>Female</option>
                        <option value="M">Male</option>
                        <option value="O">Other</option>
                    </select><br>
                </c:if>

                <c:if test="${gender == 'M'}">
                    <label>Gender</label>
                    <select name="edcomp_gender" var="gen" id="edcomp_gender">
                        <option value="F">Female</option>
                        <option value="M" selected>Male</option>
                        <option value="O">Other</option>
                    </select><br>
                </c:if>

                <c:if test="${gender == 'O'}">
                    <label>Gender</label>
                    <select name="edcomp_gender" var="gen" id="edcomp_gender">
                        <option value="F">Female</option>
                        <option value="M">Male</option>
                        <option value="O" selected>Other</option>
                    </select><br>
                </c:if>
                <c:forEach  var="phone" items="${user.companyPersonPhoneList}">
                    <th>
                        <c:if test="${phone.companyPersonID eq user}">
                            <label>Phone Number </label><input pattern="[0-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{4}" required type="tel" name="edcomp_phone" placeholder="123-456-7890" value="${phone.phoneID.countryCode}-${phone.phoneID.areaCode}-${phone.phoneID.localNumber}"><br>
                            <label>Phone Ext </label><input type="tel" name="edcomp_phoneExt" placeholder="7890" value="${phone.phoneID.extension}"><br>
                            <input type="hidden" name="phoneID" value="${phone.phoneID.phoneID}"><br>
                        </c:if>
                    </th>
                </c:forEach>

                <label>Email</label><input required type="email" name="edcomp_email" placeholder="Ex: address@service.com" value="${user.email}"><br>

                <c:forEach  var="add" items="${user.companyPersonAddressList}">
                    <label>Address Line 1 </label><input required type="text" name="edcomp_addressLine1" value="${add.addressID.addressLine1}"><br>
                    <label>Address Line 2 </label><input required type="text" name="edcomp_addressLine2" value="${add.addressID.addressLine2}"><br>  
                    <label>City </label><input type="text" name="edcomp_city" value="${add.addressID.city}"><br>
                    <label>Province </label><input required type="text" name="edcomp_prov" value="${add.addressID.province}"><br>
                    <label>Postal Code </label><input required type="text" name="edcomp_postal" value="${add.addressID.postalCode}"><br>
                    <label>Country </label><input required type="text" name="edcomp_country" value="${add.addressID.country}"><br>
                    <input type="hidden" name="addressID" value="${add.addressID.addressID}"><br>
                </c:forEach>
                <c:forEach var="pos" items="${user.companyPositionsList}">
                    <label>Position</label><input required type="text" name="edcomp_pos" value="${pos.positionTitle}"><br>
                    <input type="hidden" name="positionID" value="${pos.companyPositionsID}"><br>
                </c:forEach>

                <h3>Emergency Contact Details</h3>
                <label>First name</label><input required type="text" name="edemer_first" value="${user.personID.emergencyContactID.emergencyContactFirstName}"><br>
                <label>Last name</label><input required type="text" name="edemer_last" value="${user.personID.emergencyContactID.emergencyContactLastName}"><br>
                <label>Phone Number</label><input required type="tel" name="edemer_phone" placeholder="1-234-567-8900" pattern="[0-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{4}"  value="${user.personID.emergencyContactID.emergencyContactNumber}"><br>
                <label>Relationship</label><input required type="text" name="edemer_relationship"  value="${user.personID.emergencyContactID.emergencyContactRelationship}"><br>
                <input type="submit" name="action" value="Save">
                ${compAddMsg}
            </form>
        </c:if>
    </body>
</html>
