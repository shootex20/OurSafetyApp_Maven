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
 * The Class TypeLibrary.
 *
 * @author Chels
 */
@Entity
@Table(name = "typeLibrary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeLibrary.findAll", query = "SELECT t FROM TypeLibrary t")
    , @NamedQuery(name = "TypeLibrary.findByTypeLibraryID", query = "SELECT t FROM TypeLibrary t WHERE t.typeLibraryID = :typeLibraryID")
    , @NamedQuery(name = "TypeLibrary.findByDateAdded", query = "SELECT t FROM TypeLibrary t WHERE t.dateAdded = :dateAdded")
    , @NamedQuery(name = "TypeLibrary.findByDateRemoved", query = "SELECT t FROM TypeLibrary t WHERE t.dateRemoved = :dateRemoved")
    , @NamedQuery(name = "TypeLibrary.findByUserAdded", query = "SELECT t FROM TypeLibrary t WHERE t.userAdded = :userAdded")
    , @NamedQuery(name = "TypeLibrary.findByUserRemoved", query = "SELECT t FROM TypeLibrary t WHERE t.userRemoved = :userRemoved")
    , @NamedQuery(name = "TypeLibrary.findByType", query = "SELECT t FROM TypeLibrary t WHERE t.type = :type")
    , @NamedQuery(name = "TypeLibrary.findByDescription", query = "SELECT t FROM TypeLibrary t WHERE t.description = :description")
    , @NamedQuery(name = "TypeLibrary.findByIsCategory", query = "SELECT t FROM TypeLibrary t WHERE t.isCategory = :isCategory")})
public class TypeLibrary implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The type library ID. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "typeLibrary_ID")
    private Integer typeLibraryID;
    
    /** The date added. */
    @Column(name = "dateAdded")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    
    /** The date removed. */
    @Column(name = "dateRemoved")
    @Temporal(TemporalType.DATE)
    private Date dateRemoved;
    
    /** The user added. */
    @Column(name = "userAdded")
    private Integer userAdded;
    
    /** The user removed. */
    @Column(name = "userRemoved")
    private Integer userRemoved;
    
    /** The type. */
    @Column(name = "type")
    private String type;
    
    /** The description. */
    @Column(name = "description")
    private String description;
    
    /** The is category. */
    @Column(name = "isCategory")
    private Character isCategory;
    
    /** The address list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "typeLibraryID", fetch = FetchType.EAGER)
    private List<Address> addressList;
    
    /** The manual list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "typeLibraryID", fetch = FetchType.EAGER)
    private List<Manual> manualList;
    
    /** The url list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "typeLibraryID", fetch = FetchType.EAGER)
    private List<Url> urlList;
    
    /** The phone list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "typeLibraryID", fetch = FetchType.EAGER)
    private List<Phone> phoneList;
    
    /** The company type list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "typeLibraryID", fetch = FetchType.EAGER)
    private List<CompanyType> companyTypeList;
    
    /** The item class fields list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "typeLibraryID", fetch = FetchType.EAGER)
    private List<ItemClassFields> itemClassFieldsList;
    
    /** The company relationship list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "typeLibraryID", fetch = FetchType.EAGER)
    private List<CompanyRelationship> companyRelationshipList;

    /**
     * Instantiates a new type library.
     */
    public TypeLibrary() {
    }

    /**
     * Instantiates a new type library.
     *
     * @param typeLibraryID the type library ID
     */
    public TypeLibrary(Integer typeLibraryID) {
        this.typeLibraryID = typeLibraryID;
    }

    /**
     * Gets the type library ID.
     *
     * @return the type library ID
     */
    public Integer getTypeLibraryID() {
        return typeLibraryID;
    }

    /**
     * Sets the type library ID.
     *
     * @param typeLibraryID the new type library ID
     */
    public void setTypeLibraryID(Integer typeLibraryID) {
        this.typeLibraryID = typeLibraryID;
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
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the checks if is category.
     *
     * @return the checks if is category
     */
    public Character getIsCategory() {
        return isCategory;
    }

    /**
     * Sets the checks if is category.
     *
     * @param isCategory the new checks if is category
     */
    public void setIsCategory(Character isCategory) {
        this.isCategory = isCategory;
    }

    /**
     * Gets the address list.
     *
     * @return the address list
     */
    @XmlTransient
    public List<Address> getAddressList() {
        return addressList;
    }

    /**
     * Sets the address list.
     *
     * @param addressList the new address list
     */
    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    /**
     * Gets the manual list.
     *
     * @return the manual list
     */
    @XmlTransient
    public List<Manual> getManualList() {
        return manualList;
    }

    /**
     * Sets the manual list.
     *
     * @param manualList the new manual list
     */
    public void setManualList(List<Manual> manualList) {
        this.manualList = manualList;
    }

    /**
     * Gets the url list.
     *
     * @return the url list
     */
    @XmlTransient
    public List<Url> getUrlList() {
        return urlList;
    }

    /**
     * Sets the url list.
     *
     * @param urlList the new url list
     */
    public void setUrlList(List<Url> urlList) {
        this.urlList = urlList;
    }

    /**
     * Gets the phone list.
     *
     * @return the phone list
     */
    @XmlTransient
    public List<Phone> getPhoneList() {
        return phoneList;
    }

    /**
     * Sets the phone list.
     *
     * @param phoneList the new phone list
     */
    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    /**
     * Gets the company type list.
     *
     * @return the company type list
     */
    @XmlTransient
    public List<CompanyType> getCompanyTypeList() {
        return companyTypeList;
    }

    /**
     * Sets the company type list.
     *
     * @param companyTypeList the new company type list
     */
    public void setCompanyTypeList(List<CompanyType> companyTypeList) {
        this.companyTypeList = companyTypeList;
    }

    /**
     * Gets the item class fields list.
     *
     * @return the item class fields list
     */
    @XmlTransient
    public List<ItemClassFields> getItemClassFieldsList() {
        return itemClassFieldsList;
    }

    /**
     * Sets the item class fields list.
     *
     * @param itemClassFieldsList the new item class fields list
     */
    public void setItemClassFieldsList(List<ItemClassFields> itemClassFieldsList) {
        this.itemClassFieldsList = itemClassFieldsList;
    }

    /**
     * Gets the company relationship list.
     *
     * @return the company relationship list
     */
    @XmlTransient
    public List<CompanyRelationship> getCompanyRelationshipList() {
        return companyRelationshipList;
    }

    /**
     * Sets the company relationship list.
     *
     * @param companyRelationshipList the new company relationship list
     */
    public void setCompanyRelationshipList(List<CompanyRelationship> companyRelationshipList) {
        this.companyRelationshipList = companyRelationshipList;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeLibraryID != null ? typeLibraryID.hashCode() : 0);
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
        if (!(object instanceof TypeLibrary)) {
            return false;
        }
        TypeLibrary other = (TypeLibrary) object;
        if ((this.typeLibraryID == null && other.typeLibraryID != null) || (this.typeLibraryID != null && !this.typeLibraryID.equals(other.typeLibraryID))) {
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
        return "domain.Typelibrary[ typeLibraryID=" + typeLibraryID + " ]";
    }
    
}
