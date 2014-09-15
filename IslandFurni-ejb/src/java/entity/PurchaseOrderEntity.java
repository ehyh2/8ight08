/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
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
public class PurchaseOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private Date purchaseDate;
    private Time purchaseTime;
    private Long sendFrom;
    private Long sentTo;
    private int quantity;
    private double cost;
    private String status;
    
    @ManyToMany
    @JoinTable(name="PurchaseOrderEntity_RawMaterialEntity")
    private Set<RawMaterialEntity> rawMats = new HashSet<RawMaterialEntity>();
    
    @ManyToMany
    @JoinTable(name="PurchaseOrderEntity_FinishedGoodEntity")
    private Set<FinishedGoodEntity> finishedGoods = new HashSet<FinishedGoodEntity>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Time getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Time purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Long getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(Long sendFrom) {
        this.sendFrom = sendFrom;
    }

    public Long getSentTo() {
        return sentTo;
    }

    public void setSentTo(Long sentTo) {
        this.sentTo = sentTo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Set<RawMaterialEntity> getRawMats() {
        return rawMats;
    }

    public void setRawMats(Set<RawMaterialEntity> rawMats) {
        this.rawMats = rawMats;
    }

    public Set<FinishedGoodEntity> getFinishedGoods() {
        return finishedGoods;
    }

    public void setFinishedGoods(Set<FinishedGoodEntity> finishedGoods) {
        this.finishedGoods = finishedGoods;
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
        if (!(object instanceof PurchaseOrderEntity)) {
            return false;
        }
        PurchaseOrderEntity other = (PurchaseOrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PurchaseOrderEntity[ id=" + id + " ]";
    }
    
}
