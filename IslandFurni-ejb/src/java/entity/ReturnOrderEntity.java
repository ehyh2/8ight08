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

/**
 *
 * @author Charmaine 
 */
@Entity
public class ReturnOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnID;
    private String type;
    private Long productID;
    private String sendDate;
    private String sendTime;
    private Long sendFrom;
    private Long sendTo;
    private int quantity;
    private String returnStatus;
    private String returnDetails; 
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="ReturnOrderEntity_RawMaterialEntity")
    private List<RawMaterialEntity> defectiveParts = new ArrayList<>();
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="ReturnOrderEntity_FinishedGoodEntity")
    private List<FinishedGoodEntity> defectiveFinishedGoods = new ArrayList<>();

    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="ReturnOrderEntity_RetailProductEntity")
    private List<RetailProductEntity> defectiveRetailProducts = new ArrayList<>();
    
    @ManyToOne(cascade={CascadeType.ALL})
    private ManuFacEntity manuFac = new ManuFacEntity();
    
    @ManyToOne(cascade={CascadeType.ALL})
    private StoreEntity store = new StoreEntity();

    public ReturnOrderEntity() {
    }
    
    public void create(String type, Long productID, String sendDate, String sendTime, Long sendFrom, Long sendTo, int quantity, String returnStatus, String returnDetails) {
        this.setType(type);
        this.setProductID(productID);
        this.setSendDate(sendDate);
        this.setSendTime(sendTime);
        this.setSendFrom(sendFrom);
        this.setSendTo(sendTo);
        this.setQuantity(quantity);
        this.setReturnStatus(returnStatus);
        this.setReturnDetails(returnDetails);
    }

    public Long getReturnID() {
        return returnID;
    }

    public void setReturnID(Long returnID) {
        this.returnID = returnID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }
   
    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnDetails() {
        return returnDetails;
    }

    public void setReturnDetails(String returnDetails) {
        this.returnDetails = returnDetails;
    }

    public List<RawMaterialEntity> getDefectiveParts() {
        return defectiveParts;
    }

    public void setDefectiveParts(List<RawMaterialEntity> defectiveParts) {
        this.defectiveParts = defectiveParts;
    }

    public List<FinishedGoodEntity> getDefectiveFinishedGoods() {
        return defectiveFinishedGoods;
    }

    public void setDefectiveFinishedGoods(List<FinishedGoodEntity> defectiveFinishedGoods) {
        this.defectiveFinishedGoods = defectiveFinishedGoods;
    }

    public List<RetailProductEntity> getDefectiveRetailProducts() {
        return defectiveRetailProducts;
    }

    public void setDefectiveRetailProducts(List<RetailProductEntity> defectiveRetailProducts) {
        this.defectiveRetailProducts = defectiveRetailProducts;
    }

    public ManuFacEntity getManuFac() {
        return manuFac;
    }

    public void setManuFac(ManuFacEntity manuFac) {
        this.manuFac = manuFac;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnID != null ? returnID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReturnOrderEntity)) {
            return false;
        }
        ReturnOrderEntity other = (ReturnOrderEntity) object;
        if ((this.returnID == null && other.returnID != null) || (this.returnID != null && !this.returnID.equals(other.returnID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReturnOrderEntity[ id=" + returnID + " ]";
    }
}
