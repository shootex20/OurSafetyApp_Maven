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

/**
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

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyPerson_ID", insertable = false)
    private Integer companyPersonID;
    @Column(name = "dateAdded")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "dateRemoved", insertable = false)
    @Temporal(TemporalType.DATE)
    private Date dateRemoved;
    @Column(name = "userAdded")
    private Integer userAdded;
    @Column(name = "userRemoved", insertable = false)
    private Integer userRemoved;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "isEmployeeActive")
    private boolean isEmployeeActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyPersonID", fetch = FetchType.EAGER)
    private List<CompanyNotes> companyNotesList;
    @JoinColumn(name = "company_ID", referencedColumnName = "company_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Company companyID;
    @JoinColumn(name = "person_ID", referencedColumnName = "person_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Person personID;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "companyPersonID", fetch = FetchType.EAGER)
    private List<CompanyPersonAddress> companyPersonAddressList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "companyPersonID", fetch = FetchType.EAGER)
    private List<CompanyPositions> companyPositionsList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "companyPersonID", fetch = FetchType.EAGER)
    private List<CompanyPersonPhone> companyPersonPhoneList;

    public CompanyPerson() {
    }

    public CompanyPerson(Integer companyPersonID) {
        this.companyPersonID = companyPersonID;
    }

    public CompanyPerson(String email, boolean isEmployeeActive, Company companyID, Person personID) {
        this.dateAdded = dateAdded;
        this.email = email;
        this.isEmployeeActive = isEmployeeActive;
        this.companyID = companyID;
        this.personID = personID;
    }

    public CompanyPerson(Date dateAdded, Integer userAdded, String email, boolean isEmployeeActive, Company companyID, Person personID) {
        this.dateAdded = dateAdded;
        this.userAdded = userAdded;
        this.email = email;
        this.isEmployeeActive = isEmployeeActive;
        this.companyID = companyID;
        this.personID = personID;
    }

    public Integer getCompanyPersonID() {
        return companyPersonID;
    }

    public void setCompanyPersonID(Integer companyPersonID) {
        this.companyPersonID = companyPersonID;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateRemoved() {
        return dateRemoved;
    }

    public void setDateRemoved(Date dateRemoved) {
        this.dateRemoved = dateRemoved;
    }

    public Integer getUserAdded() {
        return userAdded;
    }

    public void setUserAdded(Integer userAdded) {
        this.userAdded = userAdded;
    }

    public Integer getUserRemoved() {
        return userRemoved;
    }

    public void setUserRemoved(Integer userRemoved) {
        this.userRemoved = userRemoved;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsEmployeeActive() {
        return isEmployeeActive;
    }

    public void setIsEmployeeActive(boolean isEmployeeActive) {
        this.isEmployeeActive = isEmployeeActive;
    }

    @XmlTransient
    public List<CompanyPersonAddress> getCompanyPersonAddressList() {
        return companyPersonAddressList;
    }

    public void setCompanyPersonAddressList(List<CompanyPersonAddress> companyPersonAddressList) {
        this.companyPersonAddressList = companyPersonAddressList;
    }

    @XmlTransient
    public List<CompanyPositions> getCompanyPositionsList() {
        return companyPositionsList;
    }

    public void setCompanyPositionsList(List<CompanyPositions> companyPositionsList) {
        this.companyPositionsList = companyPositionsList;
    }

    @XmlTransient
    public List<CompanyNotes> getCompanyNotesList() {
        return companyNotesList;
    }

    public void setCompanyNotesList(List<CompanyNotes> companyNotesList) {
        this.companyNotesList = companyNotesList;
    }

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    public Person getPersonID() {
        return personID;
    }

    public void setPersonID(Person personID) {
        this.personID = personID;
    }

    @XmlTransient
    public List<CompanyPersonPhone> getCompanyPersonPhoneList() {
        return companyPersonPhoneList;
    }

    public void setCompanyPersonPhoneList(List<CompanyPersonPhone> companyPersonPhoneList) {
        this.companyPersonPhoneList = companyPersonPhoneList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyPersonID != null ? companyPersonID.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "domain.Companyperson[ companyPersonID=" + companyPersonID + " ]";
    }

}
