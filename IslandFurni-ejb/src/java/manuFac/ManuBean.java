/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manuFac;

import entity.ManuFacEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class ManuBean implements ManuBeanLocal {

    @PersistenceContext
    private EntityManager em;


    @Override
    public boolean addMF(String country, List goodsQty, List partsQty) {
        ManuFacEntity manuFacil = new ManuFacEntity(country, goodsQty, partsQty);
        //manuFacil.setCountry(country);
        em.persist(manuFacil);
        return true;
    }
    
    @Override
    public boolean delMF(Long mfID) {
        ManuFacEntity manuFacil = em.find(ManuFacEntity.class, mfID);
        //if (manuFacil.getFinishedGoods() == null)
        //if (manuFacil.getRawMats() == null)
        //if (manuFacil.getSuppliers() == null)
        if (manuFacil != null) {
            em.remove(manuFacil);
            return true;
        } else {
            return false;
        }
    }
}
