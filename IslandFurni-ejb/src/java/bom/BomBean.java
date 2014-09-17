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

    //for global hq to create bom
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
                if (!results.isEmpty()) {
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

    //for global hq to delete bom
    @Override
    public boolean deleteBOM(Long id) {
        try {
            Query query = em.createQuery("SELECT bom FROM BOMEntity WHERE bom.id =:first");
            query.setParameter("first", id);
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    BOMEntity bom = (BOMEntity) o;
                    em.remove(bom);
                    return true;
                }
            } else {
                System.out.println("BOM does not exist");
                return false;
            }
        } catch (Exception e) {
            System.out.println("exception in delete bom method");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    //for global hq to edit bom
    @Override
    public boolean editBOM(Long id, List<Long> rm, List<Integer> qty) {
        //find the bom
        try {
            Query query = em.createQuery("SELECT bom FROM BOMEntity WHERE bom.id =:first");
            query.setParameter("first", id);
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    BOMEntity bom = (BOMEntity) o;

                    List<RawMaterialEntity> rmlist = new ArrayList<RawMaterialEntity>();
                    //to get the new list of raw materials
                    for (Long i : rm) {
                        Query query1 = em.createQuery("SELECT rm FROM RawMaterialEntity WHERE rm.id =:first");
                        query1.setParameter("first", i);
                        List results1 = query1.getResultList();
                        if (results1.size() != 0) {
                            for (Object o1 : results1) {
                                RawMaterialEntity raw;
                                raw = (RawMaterialEntity) o1;
                                rmlist.add(raw);
                            }
                        } else {
                            System.out.println("Cannot find raw material");
                        }
                    }
                    
                    //edit the bom
                    bom.setRawMats(rmlist);
                    bom.setQuantity(qty);
                    em.persist(bom);
                    return true;
                }
            } else {
                System.out.println("BOM does not exist");
                return false;
            }
        } catch (Exception e) {
            System.out.println("exception in edit bom method");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
    
    //for global hq to search for bom
    @Override
    public List<String> searchBOM(Long id) {

        List<String> searchList = new ArrayList<String>();

        try {
            //to find the bom
            Query query = em.createQuery("SELECT bom FROM BOMEntity WHERE bom.id =:first");
            query.setParameter("first", id);
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    BOMEntity bom = (BOMEntity) o;

                    //to get the bom details
                    String result = new String();
                    result = result + String.valueOf(bom.getId());

                    for (Object r : bom.getRawMats()) {
                        RawMaterialEntity raw = (RawMaterialEntity) r;
                        result = result + "#" + String.valueOf(raw.getId());
                    }

                    for (Object q : bom.getQuantity()) {
                        Integer i = (Integer) q;
                        result = result + "#" + String.valueOf(i);
                    }
                    searchList.add(result);
                }
            } else {
                System.out.println("BOM does not exist");
            }
        } catch (Exception e) {
            System.out.println("exception in search bom method");
            System.out.println(e.getMessage());
        }
        
        return searchList;
    }
    
    //for manu manager to view list of bom
    @Override
    public List<String> viewBOMList(){
        List<String> viewList = new ArrayList<String>();
        try {
            Query query = em.createQuery("SELECT bom FROM BOMEntity");
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    BOMEntity bom = (BOMEntity) o;

                    //to get the bom details
                    String result = new String();
                    result = result + String.valueOf(bom.getId());

                    for (Object r : bom.getRawMats()) {
                        RawMaterialEntity raw = (RawMaterialEntity) r;
                        result = result + "#" + String.valueOf(raw.getId());
                    }

                    for (Object q : bom.getQuantity()) {
                        Integer i = (Integer) q;
                        result = result + "#" + String.valueOf(i);
                    }
                    viewList.add(result);
                }
            } else {
                System.out.println("No BOM to view");
            }
        } catch (Exception e) {
            System.out.println("exception in view bom list method");
            System.out.println(e.getMessage());
        }
        
        return viewList;
    }
    
}
