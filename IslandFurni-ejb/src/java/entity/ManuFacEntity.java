/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
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
public class ManuFacEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String country;
    
    @ManyToMany
    @JoinTable(name="ManuFacEntity_SupplierEntity")
    private Set<SupplierEntity> suppliers = new HashSet<SupplierEntity>();
    
    @ManyToMany
    @JoinTable(name="ManuFacEntity_RawMaterialEntity")
    private Set<RawMaterialEntity> rawMats = new HashSet<RawMaterialEntity>();
    
    @ManyToMany
    @JoinTable(name="ManuFacEntity_FinishedGoodEntity")
    private Set<FinishedGoodEntity> finishedGoods = new HashSet<FinishedGoodEntity>();

    public Set<FinishedGoodEntity> getFinishedGoods() {
        return finishedGoods;
    }

    public void setFinishedGoods(Set<FinishedGoodEntity> finishedGoods) {
        this.finishedGoods = finishedGoods;
    }

    public Set<RawMaterialEntity> getRawMats() {
        return rawMats;
    }

    public void setRawMats(Set<RawMaterialEntity> rawMats) {
        this.rawMats = rawMats;
    }
    
    public ManuFacEntity() {
    }

    public ManuFacEntity(String country) {
        this.country = country;
        //this.userName = userName;
        //this.password = password;
    }
    
    public void create (String country) {
        this.setCountry(country);
       //this.setPassword(password);
        //this.setCountry(country);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<SupplierEntity> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<SupplierEntity> suppliers) {
        this.suppliers = suppliers;
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
        if (!(object instanceof ManuFacEntity)) {
            return false;
        }
        ManuFacEntity other = (ManuFacEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ManuFacEntity[ id=" + id + " ]";
    }
    
}
