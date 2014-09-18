/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manuFac;

import entity.FinishedGoodEntity;
import entity.ManuFacEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public List<ArrayList> viewListOfManu() {
        List<ArrayList> al = new ArrayList();
        Query query1 = em.createQuery("SELECT m FROM ManuFacEntity");
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        while(it1.hasNext()) {
            ArrayList alc = new ArrayList();
            ManuFacEntity m = (ManuFacEntity)it1.next();
            Long id = m.getManuFacID();
            alc.add(id);
            al.add(alc);
        }   
        return al;
    }

    @Override
    public void updateManuCountry(Long manuID, String country) {
        ManuFacEntity manu = em.find(ManuFacEntity.class, manuID);
        manu.setCountry(country);
        em.persist(manu);
    }

    @Override
    public boolean searchManuExist(Long manuID) {
        boolean exist = false;
        Query query1 = em.createQuery("SELECT m FROM ManuFacEntity WHERE m.manuFacID =:first");
        query1.setParameter("first", manuID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        while(it1.hasNext()) {
            exist = true;
        }   
        return exist;
    }

    @Override
    public List<ArrayList> viewManuDetails(Long manuID) {
        return null;
    }

    @Override
    public void addProduct(Long manuID, Long productID) {
        ManuFacEntity manu = em.find(ManuFacEntity.class, manuID);
        List<FinishedGoodEntity> fglist = manu.getFinishedGoods();
        FinishedGoodEntity fg = em.find(FinishedGoodEntity.class, productID);
        fglist.add(fg);
        manu.setFinishedGoods(fglist);
        em.persist(manu);
    }

    @Override
    public void delProduct(Long manuID, Long productID) {
        ManuFacEntity manu = em.find(ManuFacEntity.class, manuID);
        List<FinishedGoodEntity> fglist = manu.getFinishedGoods();
        FinishedGoodEntity fg = em.find(FinishedGoodEntity.class, productID);
        Iterator it1 = fglist.iterator();
        while(it1.hasNext()) {
            FinishedGoodEntity g = (FinishedGoodEntity)it1.next();
            if (fg.equals(g))
                fglist.remove(g);
        }
        manu.setFinishedGoods(fglist);
        em.persist(manu);
    }
    
}
