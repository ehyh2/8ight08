/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bom;

import entity.BOMEntity;
import entity.BOMListEntity;
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
//    @Override
//    public boolean createBOM(List<String> rm, List<String> qty) {
//
//        List<RawMaterialEntity> rmlist = new ArrayList<RawMaterialEntity>();
//        List<Integer> qtylist = new ArrayList<Integer>();
//        BOMEntity bom = new BOMEntity();
//
//        try {
//            //to get the list of raw materials
//            for (String id : rm) {
//                Long longId = Long.parseLong(id);
//                Query query = em.createQuery("SELECT rm FROM RawMaterialEntity rm WHERE rm.id =:first");
//                query.setParameter("first", longId);
//                List results = query.getResultList();
//                if (!results.isEmpty()) {
//                    for (Object o : results) {
//                        RawMaterialEntity raw;
//                        raw = (RawMaterialEntity) o;
//                        rmlist.add(raw);
//                    }
//                } else {
//                    System.out.println("Cannot find raw material");
//                    return false;
//                }
//            }
//
//            //to get the list of quantity
//            for (String q : qty) {
//                Integer quantity = Integer.parseInt(q);
//                qtylist.add(quantity);
//            }
//
//            //create the bom
//            bom.setRawMats(rmlist);
//            bom.setQuantity(qtylist);
//            em.persist(bom);
//            return true;
//        } catch (Exception e) {
//            System.out.println("exception in create bom method");
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
    @Override
    public boolean createBOM(List<Long> rawMatId, List<Integer> qty) {
        BOMListEntity bom = new BOMListEntity();
        List<BOMEntity> list = new ArrayList<BOMEntity>();
        System.out.println("create called");

        for (Long l : rawMatId) {
            System.out.println("looping list input");
            BOMEntity details = new BOMEntity();
            //details.setRawMat(l);

            //Get raw material entity
            Query query = em.createQuery("SELECT rm FROM RawMaterialEntity rm WHERE rm.id =:first");
            query.setParameter("first", l);
            List results = query.getResultList();
            if (!results.isEmpty() && results.size() == 1) {
                for (Object o : results) {
                    RawMaterialEntity raw;
                    raw = (RawMaterialEntity) o;
                    details.setRawMat(raw);
                    System.out.println("added rm");

                }
            } else {
                System.out.println("Cannot find raw material");
                return false;
            }
            int index = rawMatId.indexOf(l);
            int quantity = qty.get(index);
            details.setQty(quantity);
            System.out.println("added qty");
            em.persist(details);
            System.out.println("added bom");
            list = bom.getBom();
            list.add(details);

        }
        bom.setBom(list);
        em.persist(bom);
        System.out.println("added bomlist");
        return true;
    }

    //for global hq to delete bom
    @Override
    public boolean deleteBOM(String id) {
        Long longId = Long.parseLong(id);
        try {
            Query query = em.createQuery("SELECT bom FROM BOMEntity bom WHERE bom.id =:first");
            query.setParameter("first", longId);
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
    //@Override
//    public boolean editBOM(String id, List<String> rm, List<String> qty) {
//        Long longId = Long.parseLong(id);
//        List<RawMaterialEntity> rmlist = new ArrayList<RawMaterialEntity>();
//        List<Integer> qtylist = new ArrayList<Integer>();
//        //find the bom
//        try {
//            Query query = em.createQuery("SELECT bom FROM BOMEntity bom WHERE bom.id =:first");
//            query.setParameter("first", longId);
//            List results = query.getResultList();
//            if (!results.isEmpty()) {
//                for (Object o : results) {
//                    BOMEntity bom = (BOMEntity) o;
//
//                    //to get the new list of raw materials
//                    for (String s : rm) {
//                        Long longRawId = Long.parseLong(s);
//                        Query query1 = em.createQuery("SELECT rm FROM RawMaterialEntity WHERE rm.id =:second");
//                        query1.setParameter("second", longRawId);
//                        List results1 = query1.getResultList();
//                        if (results1.size() != 0) {
//                            for (Object o1 : results1) {
//                                RawMaterialEntity raw;
//                                raw = (RawMaterialEntity) o1;
//                                rmlist.add(raw);
//                            }
//                        } else {
//                            System.out.println("Cannot find raw material");
//                        }
//                    }
//
//                    //to get the list of quantity
//                    for (String q : qty) {
//                        Integer quantity = Integer.parseInt(q);
//                        qtylist.add(quantity);
//                    }
//
//                    //edit the bom
//                    bom.setRawMats(rmlist);
//                    bom.setQuantity(qtylist);
//                    em.persist(bom);
//                    return true;
//                }
//            } else {
//                System.out.println("BOM does not exist");
//                return false;
//            }
//        } catch (Exception e) {
//            System.out.println("exception in edit bom method");
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return false;
//    }
    //for global hq to add item to bom
//    @Override
//    public boolean addItemToBOM(String id, String rm, String qty) {
//
//        Long bomId = Long.parseLong(id);
//        Long rawId = Long.parseLong(id);
//        int quantity = Integer.parseInt(qty);
//
//        try {
//            Query query = em.createQuery("SELECT b FROM BOMEntity b WHERE b.id =:first");
//            query.setParameter("first", bomId);
//            List results = query.getResultList();
//            if (!results.isEmpty()) {
//                for (Object o : results) {
//                    BOMEntity bom = (BOMEntity) o;
//                    Query query1 = em.createQuery("SELECT rm FROM RawMaterialEntity rm WHERE rm.id =:second");
//                    query1.setParameter("second", rawId);
//                    List results1 = query1.getResultList();
//                    if (results1.size() != 0) {
//                        for (Object o1 : results1) {
//                            RawMaterialEntity raw;
//                            raw = (RawMaterialEntity) o1;
//                            List<RawMaterialEntity> rmList = new ArrayList<RawMaterialEntity>();
//                            rmList = bom.getRawMats();
//                            rmList.add(raw);
//                            bom.setRawMats(rmList);
//                            List<Integer> qtyList = new ArrayList<Integer>();
//                            qtyList = bom.getQuantity();
//                            qtyList.add(quantity);
//                            bom.setQuantity(qtyList);
//                            em.persist(bom);
//                            return true;
//                        }
//                    } else {
//                        System.out.println("raw material does not exist");
//                    }
//                    return false;
//                }
//            }
//            System.out.println("bom does not exist");
//            return false;
//        } catch (Exception e) {
//            System.out.println("exception in add item to bom method");
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }
//
//    //for global hq to delete item from bom
//    @Override
//    public boolean delItemFromBOM(String id, String rm, String qty) {
//
//        Long bomId = Long.parseLong(id);
//        Long rawId = Long.parseLong(id);
//        int quantity = Integer.parseInt(qty);
//
//        try {
//            Query query = em.createQuery("SELECT b FROM BOMEntity b WHERE b.id =:first");
//            query.setParameter("first", bomId);
//            List results = query.getResultList();
//            if (!results.isEmpty()) {
//                for (Object o : results) {
//                    BOMEntity bom = (BOMEntity) o;
//                    Query query1 = em.createQuery("SELECT rm FROM RawMaterialEntity rm WHERE rm.id =:second");
//                    query1.setParameter("second", rawId);
//                    List results1 = query1.getResultList();
//                    if (results1.size() != 0) {
//                        for (Object o1 : results1) {
//                            RawMaterialEntity raw;
//                            raw = (RawMaterialEntity) o1;
//                            List<RawMaterialEntity> rmList = new ArrayList<RawMaterialEntity>();
//                            rmList = bom.getRawMats();
//                            rmList.remove(raw);
//                            bom.setRawMats(rmList);
//                            List<Integer> qtyList = new ArrayList<Integer>();
//                            qtyList = bom.getQuantity();
//                            qtyList.remove(quantity);
//                            bom.setQuantity(qtyList);
//                            em.persist(bom);
//                            return true;
//                        }
//                    } else {
//                        System.out.println("raw material does not exist");
//                    }
//                    return false;
//                }
//            }
//            System.out.println("bom does not exist");
//            return false;
//        } catch (Exception e) {
//            System.out.println("exception in delete item from bom method");
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }
//
//    //for global hq to edit the item quantity in the bom
//    @Override
//    public boolean editQty(String id, String rm, String qty) {
//
//        Long bomId = Long.parseLong(id);
//        Long rawId = Long.parseLong(id);
//        int quantity = Integer.parseInt(qty);
//
//        try {
//            Query query = em.createQuery("SELECT b FROM BOMEntity b WHERE b.id =:first");
//            query.setParameter("first", bomId);
//            List results = query.getResultList();
//            if (!results.isEmpty()) {
//                for (Object o : results) {
//                    BOMEntity bom = (BOMEntity) o;
//                    List<Integer> qtyList = new ArrayList<Integer>();
//                    if (!qtyList.isEmpty()) {
//                        List<RawMaterialEntity> rmList = new ArrayList<RawMaterialEntity>();
//                        rmList = bom.getRawMats();
//                        int index = rmList.indexOf(rawId);
//                        qtyList.set(index, quantity);
//                        em.persist(bom);
//                        return true;
//                    } else {
//                        System.out.println("no quantity to edit");
//                    }
//                    return false;
//                }
//            }
//            System.out.println("bom does not exist");
//            return false;
//        } catch (Exception e) {
//            System.out.println("exception in edit qty of item in bom method");
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }
//
//    //for global hq to search for bom
//    @Override
//    public List<String> searchBOM(String id
//    ) {
//
//        List<String> searchList = new ArrayList<String>();
//        Long longId = Long.parseLong(id);
//
//        try {
//            //to find the bom
//            Query query = em.createQuery("SELECT bom FROM BOMEntity bom WHERE bom.id =:first");
//            query.setParameter("first", longId);
//            List results = query.getResultList();
//            if (!results.isEmpty()) {
//                for (Object o : results) {
//                    BOMEntity bom = (BOMEntity) o;
//
//                    //to get the bom details
//                    String result = new String();
//                    result = result + String.valueOf(bom.getId());
//
//                    for (Object r : bom.getRawMats()) {
//                        RawMaterialEntity raw = (RawMaterialEntity) r;
//                        result = result + "#" + String.valueOf(raw.getId());
//                    }
//
//                    for (Object q : bom.getQuantity()) {
//                        Integer i = (Integer) q;
//                        result = result + "#" + String.valueOf(i);
//                    }
//                    searchList.add(result);
//                }
//            } else {
//                System.out.println("BOM does not exist");
//            }
//        } catch (Exception e) {
//            System.out.println("exception in search bom method");
//            System.out.println(e.getMessage());
//        }
//
//        return searchList;
//    }
//
//    //for manu manager to view list of bom
//    @Override
//    public List<String> viewBOMList() {
//        List<String> viewList = new ArrayList<String>();
//        try {
//            Query query = em.createQuery("SELECT bom FROM BOMEntity bom");
//            List results = query.getResultList();
//            if (!results.isEmpty()) {
//                for (Object o : results) {
//                    BOMEntity bom = (BOMEntity) o;
//
//                    //to get the bom details
//                    String result = new String();
//                    result = result + String.valueOf(bom.getId());
//
//                    for (Object r : bom.getRawMats()) {
//                        RawMaterialEntity raw = (RawMaterialEntity) r;
//                        result = result + "#" + String.valueOf(raw.getId());
//                    }
//
//                    for (Object q : bom.getQuantity()) {
//                        Integer i = (Integer) q;
//                        result = result + "#" + String.valueOf(i);
//                    }
//                    viewList.add(result);
//                }
//            } else {
//                System.out.println("No BOM to view");
//            }
//        } catch (Exception e) {
//            System.out.println("exception in view bom list method");
//            System.out.println(e.getMessage());
//        }
//
//        return viewList;
//    }
//
}
