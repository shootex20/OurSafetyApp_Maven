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
 * The Class ItemClassFields.
 *
 * @author Chels
 */
@Entity
@Table(name = "itemClassFields")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemClassFields.findAll", query = "SELECT i FROM ItemClassFields i")
    , @NamedQuery(name = "ItemClassFields.findByItemClassFieldsID", query = "SELECT i FROM ItemClassFields i WHERE i.itemClassFieldsID = :itemClassFieldsID")
    , @NamedQuery(name = "ItemClassFields.findByDateAdded", query = "SELECT i FROM ItemClassFields i WHERE i.dateAdded = :dateAdded")
    , @NamedQuery(name = "ItemClassFields.findByDateRemoved", query = "SELECT i FROM ItemClassFields i WHERE i.dateRemoved = :dateRemoved")
    , @NamedQuery(name = "ItemClassFields.findByUserAdded", query = "SELECT i FROM ItemClassFields i WHERE i.userAdded = :userAdded")
    , @NamedQuery(name = "ItemClassFields.findByUserRemoved", query = "SELECT i FROM ItemClassFields i WHERE i.userRemoved = :userRemoved")
    , @NamedQuery(name = "ItemClassFields.findByFieldDescr", query = "SELECT i FROM ItemClassFields i WHERE i.fieldDescr = :fieldDescr")
    , @NamedQuery(name = "ItemClassFields.findByFieldDescrType", query = "SELECT i FROM ItemClassFields i WHERE i.fieldDescrType = :fieldDescrType")})
public class ItemClassFields implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The item class fields ID. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "itemClassFields_ID")
    private Integer itemClassFieldsID;
    
    /** The date added. */
    @Column(name = "dateAdded")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    
    /** The date removed. */
    @Column(name = "dateRemoved", insertable=false)
    @Temporal(TemporalType.DATE)
    private Date dateRemoved;
    
    /** The user added. */
    @Column(name = "userAdded")
    private Integer userAdded;
    
    /** The user removed. */
    @Column(name = "userRemoved", insertable=false)
    private Integer userRemoved;
    
    /** The field descr. */
    @Column(name = "fieldDescr")
    private String fieldDescr;
    
    /** The field descr type. */
    @Column(name = "fieldDescrType")
    private String fieldDescrType;
    
    /** The item class list. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "itemClassFieldsID", fetch = FetchType.EAGER)
    private List<ItemClass> itemClassList;
    
    /** The type library ID. */
    @JoinColumn(name = "typeLibrary_ID", referencedColumnName = "typeLibrary_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeLibrary typeLibraryID;

    /**
     * Instantiates a new item class fields.
     */
    public ItemClassFields() {
    }

    /**
     * Instantiates a new item class fields.
     *
     * @param itemClassFieldsID the item class fields ID
     */
    public ItemClassFields(Integer itemClassFieldsID) {
        this.itemClassFieldsID = itemClassFieldsID;
    }
    
    /**
     * Instantiates a new item class fields.
     *
     * @param itemClassFieldsID the item class fields ID
     * @param dateAdded the date added
     * @param userAdded the user added
     * @param fieldDescr the field descr
     * @param fieldDescrType the field descr type
     * @param typeLibraryID the type library ID
     */
    public ItemClassFields(Integer itemClassFieldsID, Date dateAdded, Integer userAdded, 
            String fieldDescr, String fieldDescrType, TypeLibrary typeLibraryID) {
      
        this.itemClassFieldsID = itemClassFieldsID;
        this.dateAdded = dateAdded;
        this.userAdded = userAdded;
        this.fieldDescr = fieldDescr;
        this.fieldDescrType = fieldDescrType;
        this.typeLibraryID = typeLibraryID;
       
    }

    /**
     * Gets the item class fields ID.
     *
     * @return the item class fields ID
     */
    public Integer getItemClassFieldsID() {
        return itemClassFieldsID;
    }

    /**
     * Sets the item class fields ID.
     *
     * @param itemClassFieldsID the new item class fields ID
     */
    public void setItemClassFieldsID(Integer itemClassFieldsID) {
        this.itemClassFieldsID = itemClassFieldsID;
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
     * Gets the field descr.
     *
     * @return the field descr
     */
    public String getFieldDescr() {
        return fieldDescr;
    }

    /**
     * Sets the field descr.
     *
     * @param fieldDescr the new field descr
     */
    public void setFieldDescr(String fieldDescr) {
        this.fieldDescr = fieldDescr;
    }

    /**
     * Gets the field descr type.
     *
     * @return the field descr type
     */
    public String getFieldDescrType() {
        return fieldDescrType;
    }

    /**
     * Sets the field descr type.
     *
     * @param fieldDescrType the new field descr type
     */
    public void setFieldDescrType(String fieldDescrType) {
        this.fieldDescrType = fieldDescrType;
    }

    /**
     * Gets the item class list.
     *
     * @return the item class list
     */
    @XmlTransient
    public List<ItemClass> getItemClassList() {
        return itemClassList;
    }

    /**
     * Sets the item class list.
     *
     * @param itemClassList the new item class list
     */
    public void setItemClassList(List<ItemClass> itemClassList) {
        this.itemClassList = itemClassList;
    }

    /**
     * Gets the type library ID.
     *
     * @return the type library ID
     */
    public TypeLibrary getTypeLibraryID() {
        return typeLibraryID;
    }

    /**
     * Sets the type library ID.
     *
     * @param typeLibraryID the new type library ID
     */
    public void setTypeLibraryID(TypeLibrary typeLibraryID) {
        this.typeLibraryID = typeLibraryID;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemClassFieldsID != null ? itemClassFieldsID.hashCode() : 0);
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
        if (!(object instanceof ItemClassFields)) {
            return false;
        }
        ItemClassFields other = (ItemClassFields) object;
        if ((this.itemClassFieldsID == null && other.itemClassFieldsID != null) || (this.itemClassFieldsID != null && !this.itemClassFieldsID.equals(other.itemClassFieldsID))) {
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
        return "domain.Itemclassfields[ itemClassFieldsID=" + itemClassFieldsID + " ]";
    }
    
}
