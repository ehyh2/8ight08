/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author User
 */
@Entity
public class SupplierEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierID;
    private String contactNo;
    private String country;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date contractEndDate;
    private String password;
    
    @ManyToMany(mappedBy="suppliers")
    private List<ManuFacEntity> manufacils = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name="SupplierEntity_RawMaterialEntity")
    private List<RawMaterialEntity> rawMats = new ArrayList<>();
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="supplier")
    private List<PurchaseOrderEntity> purchaseOrders = new ArrayList<PurchaseOrderEntity>();

    public SupplierEntity() {
    }

    public Long getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Long supplierID) {
        this.supplierID = supplierID;
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

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ManuFacEntity> getManufacils() {
        return manufacils;
    }

    public void setManufacils(List<ManuFacEntity> manufacils) {
        this.manufacils = manufacils;
    }

    public List<RawMaterialEntity> getRawMats() {
        return rawMats;
    }

    public void setRawMats(List<RawMaterialEntity> rawMats) {
        this.rawMats = rawMats;
    }

    public List<PurchaseOrderEntity> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrderEntity> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierID != null ? supplierID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierEntity)) {
            return false;
        }
        SupplierEntity other = (SupplierEntity) object;
        if ((this.supplierID == null && other.supplierID != null) || (this.supplierID != null && !this.supplierID.equals(other.supplierID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SupplierEntity[ id=" + supplierID + " ]";
    }
    
}
