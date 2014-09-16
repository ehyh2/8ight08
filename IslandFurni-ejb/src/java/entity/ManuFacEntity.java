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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author User
 */
@Entity
public class ManuFacEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long manuFacID;
    private String country;
    private List goodsQty; //the quantity of finished goods
    private List partsQty;
    
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<SupplierEntity> suppliers = new ArrayList<SupplierEntity>();        
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="ManuFacEntity_RawMaterialEntity")
    private List<RawMaterialEntity> parts = new ArrayList<>();        
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="ManuFacEntity_FinishedGoodEntity")
    private List<FinishedGoodEntity> finishedGoods = new ArrayList<>();        

    @OneToMany(cascade={CascadeType.ALL}, mappedBy="manuFac")
    private List<PurchaseOrderEntity> purchaseOrders = new ArrayList<PurchaseOrderEntity>();
    
    public ManuFacEntity() {
    }

    public Long getManuFacID() {
        return manuFacID;
    }

    public void setManuFacID(Long manuFacID) {
        this.manuFacID = manuFacID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(List goodsQty) {
        this.goodsQty = goodsQty;
    }

    public List getPartsQty() {
        return partsQty;
    }

    public void setPartsQty(List partsQty) {
        this.partsQty = partsQty;
    }

    public List<SupplierEntity> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierEntity> suppliers) {
        this.suppliers = suppliers;
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

    public List<PurchaseOrderEntity> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrderEntity> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (manuFacID != null ? manuFacID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ManuFacEntity)) {
            return false;
        }
        ManuFacEntity other = (ManuFacEntity) object;
        if ((this.manuFacID == null && other.manuFacID != null) || (this.manuFacID != null && !this.manuFacID.equals(other.manuFacID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ManuFacEntity[ id=" + manuFacID + " ]";
    }
    
}
