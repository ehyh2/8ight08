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
 * @author Toshiba
 */
@Entity
public class DispatchRequestEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dispatchId;
    private String dispatchDate;
    private String dispatchTime;
    private Long sendFrom;
    private Long sendTo;
    private List<Integer> quantity;
    private double cost;
    private String status;
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="DispatchRequestEntity_FinishedGoodEntity")
    private List<FinishedGoodEntity> finishedGoods = new ArrayList<>();
    
    @ManyToOne(cascade={CascadeType.ALL})
    private ManuFacEntity manuFac = new ManuFacEntity();

    public DispatchRequestEntity() {
    }
    
    public Long getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Long dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(String dispatchTime) {
        this.dispatchTime = dispatchTime;
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

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
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

    public List<FinishedGoodEntity> getFinishedGoods() {
        return finishedGoods;
    }

    public void setFinishedGoods(List<FinishedGoodEntity> finishedGoods) {
        this.finishedGoods = finishedGoods;
    }

    public ManuFacEntity getManuFac() {
        return manuFac;
    }

    public void setManuFac(ManuFacEntity manuFac) {
        this.manuFac = manuFac;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dispatchId != null ? dispatchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DispatchRequestEntity)) {
            return false;
        }
        DispatchRequestEntity other = (DispatchRequestEntity) object;
        if ((this.dispatchId == null && other.dispatchId != null) || (this.dispatchId != null && !this.dispatchId.equals(other.dispatchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DispatchRequestEntity[ id=" + dispatchId + " ]";
    }
    
}
