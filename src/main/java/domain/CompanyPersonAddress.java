/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyPersonAddress.
 *
 * @author Chels
 */
@Entity
@Table(name = "companyPersonAddress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyPersonAddress.findAll", query = "SELECT c FROM CompanyPersonAddress c")
    , @NamedQuery(name = "CompanyPersonAddress.findByCompanyPersonAddressID", query = "SELECT c FROM CompanyPersonAddress c WHERE c.companyPersonAddressID = :companyPersonAddressID")
    , @NamedQuery(name = "CompanyPersonAddress.findByDateAdded", query = "SELECT c FROM CompanyPersonAddress c WHERE c.dateAdded = :dateAdded")
    , @NamedQuery(name = "CompanyPersonAddress.findByDateRemoved", query = "SELECT c FROM CompanyPersonAddress c WHERE c.dateRemoved = :dateRemoved")
    , @NamedQuery(name = "CompanyPersonAddress.findByUserAdded", query = "SELECT c FROM CompanyPersonAddress c WHERE c.userAdded = :userAdded")
    , @NamedQuery(name = "CompanyPersonAddress.findByUserRemoved", query = "SELECT c FROM CompanyPersonAddress c WHERE c.userRemoved = :userRemoved")})
public class CompanyPersonAddress implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The company person address ID. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyPersonAddress_ID", insertable = false)
    private Integer companyPersonAddressID;
    
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
    
    /** The address ID. */
    @JoinColumn(name = "address_ID", referencedColumnName = "address_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Address addressID;
    
    /** The company person ID. */
    @JoinColumn(name = "companyPerson_ID", referencedColumnName = "companyPerson_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private CompanyPerson companyPersonID;

    /**
     * Instantiates a new company person address.
     */
    public CompanyPersonAddress() {
    }

    /**
     * Instantiates a new company person address.
     *
     * @param companyPersonAddressID the company person address ID
     */
    public CompanyPersonAddress(Integer companyPersonAddressID) {
        this.companyPersonAddressID = companyPersonAddressID;
    }
    
    /**
     * Instantiates a new company person address.
     *
     * @param dateAdded the date added
     * @param userAdded the user added
     * @param addressID the address ID
     * @param companyPersonID the company person ID
     */
    public CompanyPersonAddress(Date dateAdded, Integer userAdded, Address addressID, CompanyPerson companyPersonID) {
        this.dateAdded = dateAdded;
        this.userAdded = userAdded;
        this.addressID = addressID;
        this.companyPersonID = companyPersonID;
    }

    /**
     * Gets the company person address ID.
     *
     * @return the company person address ID
     */
    public Integer getCompanyPersonAddressID() {
        return companyPersonAddressID;
    }

    /**
     * Sets the company person address ID.
     *
     * @param companyPersonAddressID the new company person address ID
     */
    public void setCompanyPersonAddressID(Integer companyPersonAddressID) {
        this.companyPersonAddressID = companyPersonAddressID;
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
     * Gets the address ID.
     *
     * @return the address ID
     */
    public Address getAddressID() {
        return addressID;
    }

    /**
     * Sets the address ID.
     *
     * @param addressID the new address ID
     */
    public void setAddressID(Address addressID) {
        this.addressID = addressID;
    }

    /**
     * Gets the company person ID.
     *
     * @return the company person ID
     */
    public CompanyPerson getCompanyPersonID() {
        return companyPersonID;
    }

    /**
     * Sets the company person ID.
     *
     * @param companyPersonID the new company person ID
     */
    public void setCompanyPersonID(CompanyPerson companyPersonID) {
        this.companyPersonID = companyPersonID;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyPersonAddressID != null ? companyPersonAddressID.hashCode() : 0);
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
        if (!(object instanceof CompanyPersonAddress)) {
            return false;
        }
        CompanyPersonAddress other = (CompanyPersonAddress) object;
        if ((this.companyPersonAddressID == null && other.companyPersonAddressID != null) || (this.companyPersonAddressID != null && !this.companyPersonAddressID.equals(other.companyPersonAddressID))) {
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
        return "domain.Companypersonaddress[ companyPersonAddressID=" + companyPersonAddressID + " ]";
    }
    
}
