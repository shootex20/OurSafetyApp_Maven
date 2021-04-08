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
@Table(name = "companyPositions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyPositions.findAll", query = "SELECT c FROM CompanyPositions c")
    , @NamedQuery(name = "CompanyPositions.findByCompanyPositionsID", query = "SELECT c FROM CompanyPositions c WHERE c.companyPositionsID = :companyPositionsID")
    , @NamedQuery(name = "CompanyPositions.findByDateAdded", query = "SELECT c FROM CompanyPositions c WHERE c.dateAdded = :dateAdded")
    , @NamedQuery(name = "CompanyPositions.findByDateRemoved", query = "SELECT c FROM CompanyPositions c WHERE c.dateRemoved = :dateRemoved")
    , @NamedQuery(name = "CompanyPositions.findByUserAdded", query = "SELECT c FROM CompanyPositions c WHERE c.userAdded = :userAdded")
    , @NamedQuery(name = "CompanyPositions.findByUserRemoved", query = "SELECT c FROM CompanyPositions c WHERE c.userRemoved = :userRemoved")
    , @NamedQuery(name = "CompanyPositions.findByPositionTitle", query = "SELECT c FROM CompanyPositions c WHERE c.positionTitle = :positionTitle")})
public class CompanyPositions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyPositions_ID", insertable = false)
    private Integer companyPositionsID;
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
    @Column(name = "positionTitle")
    private String positionTitle;
    @JoinColumn(name = "companyPerson_ID", referencedColumnName = "companyPerson_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private CompanyPerson companyPersonID;
    @JoinColumn(name = "company_ID", referencedColumnName = "company_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Company companyID;

    public CompanyPositions() {
    }

    public CompanyPositions(Integer companyPositionsID) {
        this.companyPositionsID = companyPositionsID;
    }
    
    public CompanyPositions(Date dateAdded, Integer userAdded, String positionTitle, CompanyPerson companyPersonID, Company companyID) {
        
        this.dateAdded = dateAdded;
        this.userAdded = userAdded;
        this.positionTitle = positionTitle;
        this.companyPersonID = companyPersonID;
        this.companyID = companyID; 
    }
    
    public Integer getCompanyPositionsID() {
        return companyPositionsID;
    }

    public void setCompanyPositionsID(Integer companyPositionsID) {
        this.companyPositionsID = companyPositionsID;
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

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public CompanyPerson getCompanyPersonID() {
        return companyPersonID;
    }

    public void setCompanyPersonID(CompanyPerson companyPersonID) {
        this.companyPersonID = companyPersonID;
    }

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyPositionsID != null ? companyPositionsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyPositions)) {
            return false;
        }
        CompanyPositions other = (CompanyPositions) object;
        if ((this.companyPositionsID == null && other.companyPositionsID != null) || (this.companyPositionsID != null && !this.companyPositionsID.equals(other.companyPositionsID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Companypositions[ companyPositionsID=" + companyPositionsID + " ]";
    }
    
}
