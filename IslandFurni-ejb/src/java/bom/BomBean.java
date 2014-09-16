/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bom;

import entity.BOMEntity;
import entity.RawMaterialEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Toshiba
 */
@Stateless
public class BomBean implements BomBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean createBOM(List<Long> rm, List<Integer> qty) {

        List<RawMaterialEntity> rmlist = new ArrayList<RawMaterialEntity>();
        BOMEntity bom = new BOMEntity();

        try {
            //to get the list of raw materials
            for (Long id : rm) {
                Query query = em.createQuery("SELECT rm FROM RawMaterialEntity WHERE rm.id =:first");
                query.setParameter("first", id);
                List results = query.getResultList();
                if (results.size() != 0) {
                    for (Object o : results) {
                        RawMaterialEntity raw;
                        raw = (RawMaterialEntity) o;
                        rmlist.add(raw);
                    }
                } else {
                    System.out.println("Cannot find raw material");
                    return false;
                }
            }

            //create the bom
            bom.setRawMats(rmlist);
            bom.setQuantity(qty);
            em.persist(bom);
            return true;
        } catch (Exception e) {
            System.out.println("exception in create bom method");
            System.out.println(e.getMessage());
            return false;
        }
    }

}
