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
@Table(name = "companyNotes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyNotes.findAll", query = "SELECT c FROM CompanyNotes c")
    , @NamedQuery(name = "CompanyNotes.findByCompanyNotesID", query = "SELECT c FROM CompanyNotes c WHERE c.companyNotesID = :companyNotesID")
    , @NamedQuery(name = "CompanyNotes.findByDateAdded", query = "SELECT c FROM CompanyNotes c WHERE c.dateAdded = :dateAdded")
    , @NamedQuery(name = "CompanyNotes.findByDateRemoved", query = "SELECT c FROM CompanyNotes c WHERE c.dateRemoved = :dateRemoved")
    , @NamedQuery(name = "CompanyNotes.findByUserAdded", query = "SELECT c FROM CompanyNotes c WHERE c.userAdded = :userAdded")
    , @NamedQuery(name = "CompanyNotes.findByUserRemoved", query = "SELECT c FROM CompanyNotes c WHERE c.userRemoved = :userRemoved")
    , @NamedQuery(name = "CompanyNotes.findByNoteDate", query = "SELECT c FROM CompanyNotes c WHERE c.noteDate = :noteDate")
    , @NamedQuery(name = "CompanyNotes.findByNoteIndex", query = "SELECT c FROM CompanyNotes c WHERE c.noteIndex = :noteIndex")
    , @NamedQuery(name = "CompanyNotes.findByNote", query = "SELECT c FROM CompanyNotes c WHERE c.note = :note")})
public class CompanyNotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyNotes_ID")
    private Integer companyNotesID;
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
    @Column(name = "noteDate")
    @Temporal(TemporalType.DATE)
    private Date noteDate;
    @Column(name = "noteIndex")
    private Integer noteIndex;
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "companyPerson_ID", referencedColumnName = "companyPerson_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private CompanyPerson companyPersonID;

    public CompanyNotes() {
    }

    public CompanyNotes(Integer companyNotesID) {
        this.companyNotesID = companyNotesID;
    }

    public Integer getCompanyNotesID() {
        return companyNotesID;
    }

    public void setCompanyNotesID(Integer companyNotesID) {
        this.companyNotesID = companyNotesID;
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

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    public Integer getNoteIndex() {
        return noteIndex;
    }

    public void setNoteIndex(Integer noteIndex) {
        this.noteIndex = noteIndex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CompanyPerson getCompanyPersonID() {
        return companyPersonID;
    }

    public void setCompanyPersonID(CompanyPerson companyPersonID) {
        this.companyPersonID = companyPersonID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyNotesID != null ? companyNotesID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyNotes)) {
            return false;
        }
        CompanyNotes other = (CompanyNotes) object;
        if ((this.companyNotesID == null && other.companyNotesID != null) || (this.companyNotesID != null && !this.companyNotesID.equals(other.companyNotesID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Companynotes[ companyNotesID=" + companyNotesID + " ]";
    }
    
}
