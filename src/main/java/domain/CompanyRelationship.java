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
@Table(name = "companyRelationship")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyRelationship.findAll", query = "SELECT c FROM CompanyRelationship c")
    , @NamedQuery(name = "CompanyRelationship.findByCompanyRelationshipID", query = "SELECT c FROM CompanyRelationship c WHERE c.companyRelationshipID = :companyRelationshipID")
    , @NamedQuery(name = "CompanyRelationship.findByDateAdded", query = "SELECT c FROM CompanyRelationship c WHERE c.dateAdded = :dateAdded")
    , @NamedQuery(name = "CompanyRelationship.findByDateRemoved", query = "SELECT c FROM CompanyRelationship c WHERE c.dateRemoved = :dateRemoved")
    , @NamedQuery(name = "CompanyRelationship.findByUserAdded", query = "SELECT c FROM CompanyRelationship c WHERE c.userAdded = :userAdded")
    , @NamedQuery(name = "CompanyRelationship.findByUserRemoved", query = "SELECT c FROM CompanyRelationship c WHERE c.userRemoved = :userRemoved")
    , @NamedQuery(name = "CompanyRelationship.findByParent", query = "SELECT c FROM CompanyRelationship c WHERE c.parent = :parent")
    , @NamedQuery(name = "CompanyRelationship.findByChild", query = "SELECT c FROM CompanyRelationship c WHERE c.child = :child")})
public class CompanyRelationship implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyRelationship_ID")
    private Integer companyRelationshipID;
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
    @Column(name = "parent")
    private Integer parent;
    @Column(name = "child")
    private Integer child;
    @JoinColumn(name = "company_ID", referencedColumnName = "company_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Company companyID;
    @JoinColumn(name = "typeLibrary_ID", referencedColumnName = "typeLibrary_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeLibrary typeLibraryID;

    /**
     *
     */
    public CompanyRelationship() {
    }

    /**
     *
     * @param companyRelationshipID
     */
    public CompanyRelationship(Integer companyRelationshipID) {
        this.companyRelationshipID = companyRelationshipID;
    }

    /**
     *
     * @return
     */
    public Integer getCompanyRelationshipID() {
        return companyRelationshipID;
    }

    /**
     *
     * @param companyRelationshipID
     */
    public void setCompanyRelationshipID(Integer companyRelationshipID) {
        this.companyRelationshipID = companyRelationshipID;
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
    public Integer getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     */
    public Integer getChild() {
        return child;
    }

    /**
     *
     * @param child
     */
    public void setChild(Integer child) {
        this.child = child;
    }

    /**
     *
     * @return
     */
    public Company getCompanyID() {
        return companyID;
    }

    /**
     *
     * @param companyID
     */
    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    /**
     *
     * @return
     */
    public TypeLibrary getTypeLibraryID() {
        return typeLibraryID;
    }

    /**
     *
     * @param typeLibraryID
     */
    public void setTypeLibraryID(TypeLibrary typeLibraryID) {
        this.typeLibraryID = typeLibraryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyRelationshipID != null ? companyRelationshipID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyRelationship)) {
            return false;
        }
        CompanyRelationship other = (CompanyRelationship) object;
        if ((this.companyRelationshipID == null && other.companyRelationshipID != null) || (this.companyRelationshipID != null && !this.companyRelationshipID.equals(other.companyRelationshipID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Companyrelationship[ companyRelationshipID=" + companyRelationshipID + " ]";
    }
    
}
