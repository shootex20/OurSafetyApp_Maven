/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyPerson.
 *
 * @author Chels
 */
@Entity
@Table(name = "companyPerson")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyPerson.findAll", query = "SELECT c FROM CompanyPerson c")
    , @NamedQuery(name = "CompanyPerson.findByCompanyPersonID", query = "SELECT c FROM CompanyPerson c WHERE c.companyPersonID = :companyPersonID")
    , @NamedQuery(name = "CompanyPerson.findByDateAdded", query = "SELECT c FROM CompanyPerson c WHERE c.dateAdded = :dateAdded")
    , @NamedQuery(name = "CompanyPerson.findByDateRemoved", query = "SELECT c FROM CompanyPerson c WHERE c.dateRemoved = :dateRemoved")
    , @NamedQuery(name = "CompanyPerson.findByUserAdded", query = "SELECT c FROM CompanyPerson c WHERE c.userAdded = :userAdded")
    , @NamedQuery(name = "CompanyPerson.findByUserRemoved", query = "SELECT c FROM CompanyPerson c WHERE c.userRemoved = :userRemoved")
    , @NamedQuery(name = "CompanyPerson.findByEmail", query = "SELECT c FROM CompanyPerson c WHERE c.email = :email")})
public class CompanyPerson implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The company person ID. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyPerson_ID", insertable = false)
    private Integer companyPersonID;
    
    /** The date added. */
    @Column(name = "dateAdded")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    
    /** The date removed. */
    @Column(name = "dateRemoved", insertable = false)
    @Temporal(TemporalType.DATE)
    private Date dateRemoved;
    
    /** The user added. */
    @Column(name = "userAdded")
    private Integer userAdded;
    
    /** The user removed. */
    @Column(name = "userRemoved", insertable = false)
    private Integer userRemoved;
    
    /** The email. */
    @Column(name = "email")
    private String email;
    
    /** The is employee active. */
    @Basic(optional = false)
    @Column(name = "isEmployeeActive")
    private boolean isEmployeeActive;
    
    /** The company notes list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyPersonID", fetch = FetchType.EAGER)
    private List<CompanyNotes> companyNotesList;
    
    /** The company ID. */
    @JoinColumn(name = "company_ID", referencedColumnName = "company_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Company companyID;
    
    /** The person ID. */
    @JoinColumn(name = "person_ID", referencedColumnName = "person_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Person personID;
    
    /** The company person address list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "companyPersonID", fetch = FetchType.EAGER)
    private List<CompanyPersonAddress> companyPersonAddressList;
    
    /** The company positions list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "companyPersonID", fetch = FetchType.EAGER)
    private List<CompanyPositions> companyPositionsList;
    
    /** The company person phone list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "companyPersonID", fetch = FetchType.EAGER)
    private List<CompanyPersonPhone> companyPersonPhoneList;

    /**
     * Instantiates a new company person.
     */
    public CompanyPerson() {
    }

    /**
     * Instantiates a new company person.
     *
     * @param companyPersonID the company person ID
     */
    public CompanyPerson(Integer companyPersonID) {
        this.companyPersonID = companyPersonID;
    }

    /**
     * Instantiates a new company person.
     *
     * @param email the email
     * @param isEmployeeActive the is employee active
     * @param companyID the company ID
     * @param personID the person ID
     */
    public CompanyPerson(String email, boolean isEmployeeActive, Company companyID, Person personID) {
        this.dateAdded = dateAdded;
        this.email = email;
        this.isEmployeeActive = isEmployeeActive;
        this.companyID = companyID;
        this.personID = personID;
    }

    /**
     * Instantiates a new company person.
     *
     * @param dateAdded the date added
     * @param userAdded the user added
     * @param email the email
     * @param isEmployeeActive the is employee active
     * @param companyID the company ID
     * @param personID the person ID
     */
    public CompanyPerson(Date dateAdded, Integer userAdded, String email, boolean isEmployeeActive, Company companyID, Person personID) {
        this.dateAdded = dateAdded;
        this.userAdded = userAdded;
        this.email = email;
        this.isEmployeeActive = isEmployeeActive;
        this.companyID = companyID;
        this.personID = personID;
    }

    /**
     * Gets the company person ID.
     *
     * @return the company person ID
     */
    public Integer getCompanyPersonID() {
        return companyPersonID;
    }

    /**
     * Sets the company person ID.
     *
     * @param companyPersonID the new company person ID
     */
    public void setCompanyPersonID(Integer companyPersonID) {
        this.companyPersonID = companyPersonID;
    }

    /**
     * Gets the date added.
     *
     * @return the date added
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * Sets the date added.
     *
     * @param dateAdded the new date added
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * Gets the date removed.
     *
     * @return the date removed
     */
    public Date getDateRemoved() {
        return dateRemoved;
    }

    /**
     * Sets the date removed.
     *
     * @param dateRemoved the new date removed
     */
    public void setDateRemoved(Date dateRemoved) {
        this.dateRemoved = dateRemoved;
    }

    /**
     * Gets the user added.
     *
     * @return the user added
     */
    public Integer getUserAdded() {
        return userAdded;
    }

    /**
     * Sets the user added.
     *
     * @param userAdded the new user added
     */
    public void setUserAdded(Integer userAdded) {
        this.userAdded = userAdded;
    }

    /**
     * Gets the user removed.
     *
     * @return the user removed
     */
    public Integer getUserRemoved() {
        return userRemoved;
    }

    /**
     * Sets the user removed.
     *
     * @param userRemoved the new user removed
     */
    public void setUserRemoved(Integer userRemoved) {
        this.userRemoved = userRemoved;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the checks if is employee active.
     *
     * @return the checks if is employee active
     */
    public boolean getIsEmployeeActive() {
        return isEmployeeActive;
    }

    /**
     * Sets the checks if is employee active.
     *
     * @param isEmployeeActive the new checks if is employee active
     */
    public void setIsEmployeeActive(boolean isEmployeeActive) {
        this.isEmployeeActive = isEmployeeActive;
    }

    /**
     * Gets the company person address list.
     *
     * @return the company person address list
     */
    @XmlTransient
    public List<CompanyPersonAddress> getCompanyPersonAddressList() {
        return companyPersonAddressList;
    }

    /**
     * Sets the company person address list.
     *
     * @param companyPersonAddressList the new company person address list
     */
    public void setCompanyPersonAddressList(List<CompanyPersonAddress> companyPersonAddressList) {
        this.companyPersonAddressList = companyPersonAddressList;
    }

    /**
     * Gets the company positions list.
     *
     * @return the company positions list
     */
    @XmlTransient
    public List<CompanyPositions> getCompanyPositionsList() {
        return companyPositionsList;
    }

    /**
     * Sets the company positions list.
     *
     * @param companyPositionsList the new company positions list
     */
    public void setCompanyPositionsList(List<CompanyPositions> companyPositionsList) {
        this.companyPositionsList = companyPositionsList;
    }

    /**
     * Gets the company notes list.
     *
     * @return the company notes list
     */
    @XmlTransient
    public List<CompanyNotes> getCompanyNotesList() {
        return companyNotesList;
    }

    /**
     * Sets the company notes list.
     *
     * @param companyNotesList the new company notes list
     */
    public void setCompanyNotesList(List<CompanyNotes> companyNotesList) {
        this.companyNotesList = companyNotesList;
    }

    /**
     * Gets the company ID.
     *
     * @return the company ID
     */
    public Company getCompanyID() {
        return companyID;
    }

    /**
     * Sets the company ID.
     *
     * @param companyID the new company ID
     */
    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    /**
     * Gets the person ID.
     *
     * @return the person ID
     */
    public Person getPersonID() {
        return personID;
    }

    /**
     * Sets the person ID.
     *
     * @param personID the new person ID
     */
    public void setPersonID(Person personID) {
        this.personID = personID;
    }

    /**
     * Gets the company person phone list.
     *
     * @return the company person phone list
     */
    @XmlTransient
    public List<CompanyPersonPhone> getCompanyPersonPhoneList() {
        return companyPersonPhoneList;
    }

    /**
     * Sets the company person phone list.
     *
     * @param companyPersonPhoneList the new company person phone list
     */
    public void setCompanyPersonPhoneList(List<CompanyPersonPhone> companyPersonPhoneList) {
        this.companyPersonPhoneList = companyPersonPhoneList;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyPersonID != null ? companyPersonID.hashCode() : 0);
        return hash;
    }

    /**
     * Equals.
     *
     * @param object the object
     * @return true, if successful
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyPerson)) {
            return false;
        }
        CompanyPerson other = (CompanyPerson) object;
        if ((this.companyPersonID == null && other.companyPersonID != null) || (this.companyPersonID != null && !this.companyPersonID.equals(other.companyPersonID))) {
            return false;
        }
        return true;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "domain.Companyperson[ companyPersonID=" + companyPersonID + " ]";
    }

}
