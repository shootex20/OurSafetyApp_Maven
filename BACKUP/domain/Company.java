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

/**
 *
 * @author Chels
 */
@Entity
@Table(name = "company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
    , @NamedQuery(name = "Company.findByCompanyID", query = "SELECT c FROM Company c WHERE c.companyID = :companyID")
    , @NamedQuery(name = "Company.findByDateAdded", query = "SELECT c FROM Company c WHERE c.dateAdded = :dateAdded")
    , @NamedQuery(name = "Company.findByDateRemoved", query = "SELECT c FROM Company c WHERE c.dateRemoved = :dateRemoved")
    , @NamedQuery(name = "Company.findByUserAdded", query = "SELECT c FROM Company c WHERE c.userAdded = :userAdded")
    , @NamedQuery(name = "Company.findByUserRemoved", query = "SELECT c FROM Company c WHERE c.userRemoved = :userRemoved")
    , @NamedQuery(name = "Company.findByShortname", query = "SELECT c FROM Company c WHERE c.shortname = :shortname")
    , @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name")
    , @NamedQuery(name = "Company.findByDescription", query = "SELECT c FROM Company c WHERE c.description = :description")
    , @NamedQuery(name = "Company.findBySaltHash", query = "SELECT c FROM Company c WHERE c.saltHash = :saltHash")
    , @NamedQuery(name = "Company.findByAccount", query = "SELECT c FROM Company c WHERE c.account = :account")
    , @NamedQuery(name = "Company.findByIndustry", query = "SELECT c FROM Company c WHERE c.industry = :industry")})
public class Company implements Serializable {

    @OneToMany(mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<CompanyRelationship> companyRelationshipList;
    @OneToMany(mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<CompanyType> companyTypeList;
    @OneToMany(mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<CompanyPositions> companyPositionsList;
    @OneToMany(mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<CompanyPerson> companyPersonList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "company_ID")
    private Integer companyID;
    @Column(name = "dateAdded")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "dateRemoved")
    @Temporal(TemporalType.DATE)
    private Date dateRemoved;
    @Column(name = "userAdded")
    private Integer userAdded;
    @Column(name = "userRemoved")
    private Integer userRemoved;
    @Column(name = "shortname")
    private String shortname;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "saltHash")
    private String saltHash;
    @Column(name = "account")
    private String account;
    @Column(name = "industry")
    private String industry;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<Item> itemList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<CompanyPerson> companypersonList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<Url> urlList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<CompanyPositions> companypositionsList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<CompanyType> companytypeList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<Logins> loginsList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "companyID", fetch = FetchType.EAGER)
    private List<CompanyRelationship> companyrelationshipList;

    /**
     *
     */
    public Company() {
    }

    /**
     *
     * @param companyID
     */
    public Company(Integer companyID) {
        this.companyID = companyID;
    }

    /**
     *
     * @param dateAdded
     * @param name
     * @param shortname
     * @param description
     * @param account
     * @param industry
     */
    public Company(Date dateAdded, String name, String shortname, String description, String account, String industry) {
        this.dateAdded = dateAdded;
        this.name = name;
        this.shortname = shortname;
        this.description = description;
        this.account = account;
        this.industry = industry;
               
    }

    /**
     *
     * @return
     */
    public Integer getCompanyID() {
        return companyID;
    }

    /**
     *
     * @param companyID
     */
    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    /**
     *
     * @return
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     *
     * @param dateAdded
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     *
     * @return
     */
    public Date getDateRemoved() {
        return dateRemoved;
    }

    /**
     *
     * @param dateRemoved
     */
    public void setDateRemoved(Date dateRemoved) {
        this.dateRemoved = dateRemoved;
    }

    /**
     *
     * @return
     */
    public Integer getUserAdded() {
        return userAdded;
    }

    /**
     *
     * @param userAdded
     */
    public void setUserAdded(Integer userAdded) {
        this.userAdded = userAdded;
    }

    /**
     *
     * @return
     */
    public Integer getUserRemoved() {
        return userRemoved;
    }

    /**
     *
     * @param userRemoved
     */
    public void setUserRemoved(Integer userRemoved) {
        this.userRemoved = userRemoved;
    }

    /**
     *
     * @return
     */
    public String getShortname() {
        return shortname;
    }

    /**
     *
     * @param shortname
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getSaltHash() {
        return saltHash;
    }

    /**
     *
     * @param saltHash
     */
    public void setSaltHash(String saltHash) {
        this.saltHash = saltHash;
    }

    /**
     *
     * @return
     */
    public String getAccount() {
        return account;
    }

    /**
     *
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     *
     * @return
     */
    public String getIndustry() {
        return industry;
    }

    /**
     *
     * @param industry
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     *
     * @param itemList
     */
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<CompanyPerson> getCompanypersonList() {
        return companypersonList;
    }

    /**
     *
     * @param companypersonList
     */
    public void setCompanypersonList(List<CompanyPerson> companypersonList) {
        this.companypersonList = companypersonList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<Url> getUrlList() {
        return urlList;
    }

    /**
     *
     * @param urlList
     */
    public void setUrlList(List<Url> urlList) {
        this.urlList = urlList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<CompanyPositions> getCompanypositionsList() {
        return companypositionsList;
    }

    /**
     *
     * @param companypositionsList
     */
    public void setCompanypositionsList(List<CompanyPositions> companypositionsList) {
        this.companypositionsList = companypositionsList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<CompanyType> getCompanytypeList() {
        return companytypeList;
    }

    /**
     *
     * @param companytypeList
     */
    public void setCompanytypeList(List<CompanyType> companytypeList) {
        this.companytypeList = companytypeList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<Logins> getLoginsList() {
        return loginsList;
    }

    /**
     *
     * @param loginsList
     */
    public void setLoginsList(List<Logins> loginsList) {
        this.loginsList = loginsList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<CompanyRelationship> getCompanyrelationshipList() {
        return companyrelationshipList;
    }

    /**
     *
     * @param companyrelationshipList
     */
    public void setCompanyrelationshipList(List<CompanyRelationship> companyrelationshipList) {
        this.companyrelationshipList = companyrelationshipList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyID != null ? companyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyID == null && other.companyID != null) || (this.companyID != null && !this.companyID.equals(other.companyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Company[ companyID=" + companyID + " ]";
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<CompanyRelationship> getCompanyRelationshipList() {
        return companyRelationshipList;
    }

    /**
     *
     * @param companyRelationshipList
     */
    public void setCompanyRelationshipList(List<CompanyRelationship> companyRelationshipList) {
        this.companyRelationshipList = companyRelationshipList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<CompanyType> getCompanyTypeList() {
        return companyTypeList;
    }

    /**
     *
     * @param companyTypeList
     */
    public void setCompanyTypeList(List<CompanyType> companyTypeList) {
        this.companyTypeList = companyTypeList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<CompanyPositions> getCompanyPositionsList() {
        return companyPositionsList;
    }

    /**
     *
     * @param companyPositionsList
     */
    public void setCompanyPositionsList(List<CompanyPositions> companyPositionsList) {
        this.companyPositionsList = companyPositionsList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<CompanyPerson> getCompanyPersonList() {
        return companyPersonList;
    }

    /**
     *
     * @param companyPersonList
     */
    public void setCompanyPersonList(List<CompanyPerson> companyPersonList) {
        this.companyPersonList = companyPersonList;
    }
    
}
