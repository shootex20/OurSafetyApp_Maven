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

/**
 *
 * @author Chels
 */
@Entity
@Table(name = "companyType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyType.findAll", query = "SELECT c FROM CompanyType c")
    , @NamedQuery(name = "CompanyType.findByCompanyTypeID", query = "SELECT c FROM CompanyType c WHERE c.companyTypeID = :companyTypeID")
    , @NamedQuery(name = "CompanyType.findByDateAdded", query = "SELECT c FROM CompanyType c WHERE c.dateAdded = :dateAdded")
    , @NamedQuery(name = "CompanyType.findByDateRemoved", query = "SELECT c FROM CompanyType c WHERE c.dateRemoved = :dateRemoved")
    , @NamedQuery(name = "CompanyType.findByUserAdded", query = "SELECT c FROM CompanyType c WHERE c.userAdded = :userAdded")
    , @NamedQuery(name = "CompanyType.findByUserRemoved", query = "SELECT c FROM CompanyType c WHERE c.userRemoved = :userRemoved")})
public class CompanyType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyType_ID")
    private Integer companyTypeID;
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
    @JoinColumn(name = "company_ID", referencedColumnName = "company_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Company companyID;
    @JoinColumn(name = "typeLibrary_ID", referencedColumnName = "typeLibrary_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeLibrary typeLibraryID;

    public CompanyType() {
    }

    public CompanyType(Integer companyTypeID) {
        this.companyTypeID = companyTypeID;
    }

    public Integer getCompanyTypeID() {
        return companyTypeID;
    }

    public void setCompanyTypeID(Integer companyTypeID) {
        this.companyTypeID = companyTypeID;
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

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    public TypeLibrary getTypeLibraryID() {
        return typeLibraryID;
    }

    public void setTypeLibraryID(TypeLibrary typeLibraryID) {
        this.typeLibraryID = typeLibraryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyTypeID != null ? companyTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyType)) {
            return false;
        }
        CompanyType other = (CompanyType) object;
        if ((this.companyTypeID == null && other.companyTypeID != null) || (this.companyTypeID != null && !this.companyTypeID.equals(other.companyTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Companytype[ companyTypeID=" + companyTypeID + " ]";
    }
    
}
