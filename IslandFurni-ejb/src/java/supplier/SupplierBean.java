/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supplier;

import entity.RawMaterialEntity;
import entity.SupplierEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Liwei__xD
 */
@Stateless
public class SupplierBean implements SupplierBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public boolean addSupplier(String country, List<Long> rawMats) {//query raw material entities and save into list
        List<RawMaterialEntity>  raw = new ArrayList<RawMaterialEntity>();
        Iterator it1 = rawMats.iterator();
        SupplierEntity supplier = new SupplierEntity();
        supplier.setCountry(country);

        while(it1.hasNext()) {
            ArrayList alc = new ArrayList();
            Long rawMatID = (Long)it1.next();
            RawMaterialEntity rawMat = em.find(RawMaterialEntity.class, rawMatID);
            raw.add(rawMat);
        }   
        supplier.setRawMats(raw);    
        //manuFacil.setCountry(country);
        em.persist(supplier);
        return true;
    }
    
    public boolean delSupplier(Long ssID) {
        SupplierEntity supplier = em.find(SupplierEntity.class, ssID);
        //if (manuFacil.getFinishedGoods() == null)
        //if (manuFacil.getRawMats() == null)
        //if (manuFacil.getSuppliers() == null)
        if (supplier != null) {
            em.remove(supplier);
            return true;
        } else {
            return false;
        }
    }
}
