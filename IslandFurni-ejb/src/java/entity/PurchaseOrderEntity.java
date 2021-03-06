/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author User
 */
@Entity
public class PurchaseOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseID;
    //private String type;
    private String purchaseDate;
    private String purchaseTime;
    private Long sendFrom;
    private Long sendTo;
    //private List quantity;
    private double cost;
    private String status;
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="PurchaseOrderEntity_RawMaterialEntity")
    private List<RawMaterialEntity> parts = new ArrayList<>();
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="PurchaseOrderEntity_FinishedGoodEntity")
    private List<FinishedGoodEntity> finishedGoods = new ArrayList<>();

    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="PurchaseOrderEntity_RetailProductEntity")
    private List<RetailProductEntity> retailProducts = new ArrayList<>();
    
    @ManyToOne(cascade={CascadeType.ALL})
    private ManuFacEntity manuFac = new ManuFacEntity();
    
    @ManyToOne(cascade={CascadeType.ALL})
    private SupplierEntity supplier = new SupplierEntity();
    
    @OneToMany
    private List<POQtyEntity> poQty = new ArrayList<POQtyEntity>(); 

    public PurchaseOrderEntity() {
    }
    
    public void create(String purchaseDate, String purchaseTime, Long sendFrom, Long sendTo, String status) {
        
        this.setPurchaseDate(purchaseDate);
        this.setPurchaseTime(purchaseTime);
        this.setSendFrom(sendFrom);
        this.setSendTo(sendTo); 
        this.setStatus(status);
    }
    
    public Long getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(Long purchaseID) {
        this.purchaseID = purchaseID;
    }


    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Long getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(Long sendFrom) {
        this.sendFrom = sendFrom;
    }

    public Long getSendTo() {
        return sendTo;
    }

    public void setSendTo(Long sendTo) {
        this.sendTo = sendTo;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<POQtyEntity> getPoQty() {
        return poQty;
    }

    public void setPoQty(List<POQtyEntity> poQty) {
        this.poQty = poQty;
    }
     
    public List<RawMaterialEntity> getParts() {
        return parts;
    }

    public void setParts(List<RawMaterialEntity> parts) {
        this.parts = parts;
    }

    public List<FinishedGoodEntity> getFinishedGoods() {
        return finishedGoods;
    }

    public void setFinishedGoods(List<FinishedGoodEntity> finishedGoods) {
        this.finishedGoods = finishedGoods;
    }

    public List<RetailProductEntity> getRetailProducts() {
        return retailProducts;
    }

    public void setRetailProducts(List<RetailProductEntity> retailProducts) {
        this.retailProducts = retailProducts;
    }

    public ManuFacEntity getManuFac() {
        return manuFac;
    }

    public void setManuFac(ManuFacEntity manuFac) {
        this.manuFac = manuFac;
    } 

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseID != null ? purchaseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrderEntity)) {
            return false;
        }
        PurchaseOrderEntity other = (PurchaseOrderEntity) object;
        if ((this.purchaseID == null && other.purchaseID != null) || (this.purchaseID != null && !this.purchaseID.equals(other.purchaseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PurchaseOrderEntity[ id=" + purchaseID + " ]";
    }
    
}
