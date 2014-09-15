/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author User
 */
@Entity
public class SupplierEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contactNo;
    private String country;
    private Date contractEndDate;
    
    
    @ManyToMany(mappedBy="suppliers")
    private Set<ManuFacEntity> manufacils = new HashSet<ManuFacEntity>();
    
    @ManyToMany
    @JoinTable(name="SupplierEntity_RawMaterialEntity")
    private Set<RawMaterialEntity> rawMats = new HashSet<RawMaterialEntity>();

    public SupplierEntity() {
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<RawMaterialEntity> getRawMats() {
        return rawMats;
    }

    public void setRawMats(Set<RawMaterialEntity> rawMats) {
        this.rawMats = rawMats;
    }

    public Set<ManuFacEntity> getManufacils() {
        return manufacils;
    }

    public void setManufacils(Set<ManuFacEntity> manufacils) {
        this.manufacils = manufacils;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierEntity)) {
            return false;
        }
        SupplierEntity other = (SupplierEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SupplierEntity[ id=" + id + " ]";
    }
    
}
