/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supplier;

import entity.RawMaterialEntity;
import entity.SupplierEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Liwei__xD
 */
@Stateless
public class SupplierBean implements SupplierBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public boolean addSupplier(String country, List<ArrayList> rawMats, Date contractEndDate) {//query raw material entities and save into list
        List<RawMaterialEntity>  raw = new ArrayList<RawMaterialEntity>();
        Iterator it1 = rawMats.iterator();
        SupplierEntity supplier = new SupplierEntity();
        supplier.setCountry(country);
        supplier.setContractEndDate(contractEndDate);
        while(it1.hasNext()) {
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

    @Override
    public List<ArrayList> viewListofSupplier() {
        List<ArrayList> al = new ArrayList();
        Query query1 = em.createQuery("SELECT s FROM SupplierEntity");
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        while(it1.hasNext()) {
            ArrayList alc = new ArrayList();
            SupplierEntity s = (SupplierEntity)it1.next();
            Long id = s.getId();
            alc.add(id);
            al.add(alc);
        }   
        return al;
    }

    @Override
    public List<ArrayList> viewSupplierDetails(Long supplierID) {
        SupplierEntity supplier = em.find(SupplierEntity.class, supplierID);
        List<ArrayList> al = new ArrayList();
        ArrayList alc = new ArrayList();
        String country = supplier.getCountry();
        String contact = supplier.getContactNo();
        Date contractEndDate = supplier.getContractEndDate();
        alc.add(supplierID);
        alc.add(country);
        alc.add(contact);
        alc.add(contractEndDate);
        al.add(alc);
        List<RawMaterialEntity> rawMats= supplier.getRawMats();
        Iterator it1 = rawMats.iterator();
        while(it1.hasNext()) {
            ArrayList al1 = new ArrayList();
            RawMaterialEntity rm = (RawMaterialEntity)it1.next();
            rm.getId();
           //haven finish 
        }
        return al;
    }

    @Override
    public boolean updateSupplierDetails(Long supplierID, String contactNo, String country) {
        return false;
    }

    @Override
    public boolean searchSupplierExist(Long supplierID) {
        return false;
    }

    @Override
    public boolean sendSupplierListToGlobal() {
        return false;
    }

    @Override
    public boolean updateSupplierContractEndDate(Date contractEndDate) {
        return false;
    }

    @Override
    public boolean setSupplierPassword(Long supplierID) {
        return false;
    }
    
    
}
