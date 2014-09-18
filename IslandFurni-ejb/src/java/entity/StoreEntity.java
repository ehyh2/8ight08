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

/**
 *
 * @author Toshiba
 */
@Entity
public class StoreEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long storeId;
    private String country;
    private String region;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "store")
    private List<DispatchRequestEntity> dispatchRequests = new ArrayList<DispatchRequestEntity>();

    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="StoreEntity_FinishedGoodEntity")
    private List<FinishedGoodEntity> finishedGoods = new ArrayList<>();
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="store")
    private List<ReturnOrderEntity> returnOrders = new ArrayList<ReturnOrderEntity>();
    
    public StoreEntity(){
        
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<DispatchRequestEntity> getDispatchRequests() {
        return dispatchRequests;
    }

    public void setDispatchRequests(List<DispatchRequestEntity> dispatchRequests) {
        this.dispatchRequests = dispatchRequests;
    }

    public List<FinishedGoodEntity> getFinishedGoods() {
        return finishedGoods;
    }

    public void setFinishedGoods(List<FinishedGoodEntity> finishedGoods) {
        this.finishedGoods = finishedGoods;
    }

    public List<ReturnOrderEntity> getReturnOrders() {
        return returnOrders;
    }

    public void setReturnOrders(List<ReturnOrderEntity> returnOrders) {
        this.returnOrders = returnOrders;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeId != null ? storeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreEntity)) {
            return false;
        }
        StoreEntity other = (StoreEntity) object;
        if ((this.storeId == null && other.storeId != null) || (this.storeId != null && !this.storeId.equals(other.storeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StoreEntity[ id=" + storeId + " ]";
    }

}
