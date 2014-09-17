/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package purchaseOrder;
  
import entity.FinishedGoodEntity;
import entity.PurchaseOrderEntity;
import entity.RawMaterialEntity;
import entity.RetailProductEntity;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Liwei__xD
 */
@Stateless
public class ViewPurchaseBean implements ViewPurchaseLocal {
    @PersistenceContext
    private EntityManager em;
    private PurchaseOrderEntity purchaseOrderEntity;

    //for purchase order of raw materials, retail products, finished goods
    @Override
    public Long createPurchaseOrder(String type, Long sendFrom, Long sendTo) {
        try {
            purchaseOrderEntity = new PurchaseOrderEntity();
            
            SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gmtDateFormat.setTimeZone(TimeZone.getTimeZone("Singapore"));
            String dateTime = gmtDateFormat.format(new Date());
            String[] splited = dateTime.split(" ");
            String date = splited[0];
            String time = splited[1];
            
            String status = purchaseOrderEntity.getStatus();
            purchaseOrderEntity.create(type, date, time, sendFrom, sendTo, status);
            em.persist(purchaseOrderEntity);
        } catch (Exception ex) {
            System.out.println("\nException Error: " + ex.getMessage());
        }
        return purchaseOrderEntity.getPurchaseID();
    }
    
    @Override
    public void deletePurchaseOrder(Long purchaseID) {
        Query query = em.createQuery("SELECT po FROM PurchaseOrderEntity WHERE po.purchaseID =:first");
        query.setParameter("first", purchaseID);
        PurchaseOrderEntity purchaseOrder = (PurchaseOrderEntity) query.getSingleResult();
        em.remove(purchaseOrder);
    }
    
    @Override
    public boolean addItemToPurchaseOrder(Long purchaseID, Long partID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE r.purchaseID =:first");
        query1.setParameter("first", purchaseID);
        List result1 = query1.getResultList();
        Iterator it2 = result1.iterator(); 
        PurchaseOrderEntity p = (PurchaseOrderEntity)it2.next();
        
        String type = p.getType();
        
        if (type.equals("Raw Material")) {
            Query query2 = em.createQuery("SELECT r FROM RawMaterialEntity WHERE r.partID =:first");
            query2.setParameter("first", partID);
            List result2 = query2.getResultList();
            Iterator it = result2.iterator(); 
            RawMaterialEntity r = (RawMaterialEntity)it.next();
            
            if (!result1.isEmpty() && !result2.isEmpty()) {
                List allParts = p.getParts();
                allParts.add(r);
                p.setParts(allParts);
                em.persist(p);
                return true;
            }
        } else if (type.equals("Finished Good")) {
            Query query2 = em.createQuery("SELECT f FROM FinishedGoodEntity WHERE f.modelID =:first");
            query2.setParameter("first", partID);
            List result2 = query2.getResultList();
            Iterator it = result2.iterator(); 
            FinishedGoodEntity f = (FinishedGoodEntity)it.next();
            
            if (!result1.isEmpty() && !result2.isEmpty()) {
                List allGoods = p.getFinishedGoods();
                allGoods.add(f);
                p.setParts(allGoods);
                em.persist(p);
                return true;
            }
        } else if (type.equals("Retail Product")) {
            Query query2 = em.createQuery("SELECT r FROM RetailProductEntity WHERE r.modelID =:first");
            query2.setParameter("first", partID);
            List result2 = query2.getResultList();
            Iterator it = result2.iterator(); 
            RetailProductEntity r = (RetailProductEntity)it.next();
            
            if (!result1.isEmpty() && !result2.isEmpty()) {
                List allRetailPdts = p.getRetailProducts();
                allRetailPdts.add(r);
                p.setParts(allRetailPdts);
                em.persist(p);
                return true;
            } 
        } return false;  
    }

    @Override
    public boolean delItemFromPurchaseOrder(Long purchaseID, Long partID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.purchaseID =:first");
        query1.setParameter("first", purchaseID);
        List result1 = query1.getResultList();
        Iterator it = result1.iterator(); 
        PurchaseOrderEntity p = (PurchaseOrderEntity)it.next();
        
        String type = p.getType();
        
        if (type.equals("Raw Material")) {
            Query query2 = em.createQuery("SELECT r FROM RawMaterialEntity WHERE r.partID =:first");
            query2.setParameter("first", partID);
            List result2 = query2.getResultList();
            Iterator it2 = result2.iterator();
            RawMaterialEntity r = (RawMaterialEntity)it2.next();
        
            if (!result1.isEmpty() && !result2.isEmpty()) {
                List allParts = p.getParts();
                allParts.remove(r);
                p.setParts(allParts);
                em.persist(p);
                return true;
            }
        } else if (type.equals("Finished Good")) {
            Query query2 = em.createQuery("SELECT r FROM FinishedGoodEntity WHERE r.modelID =:first");
            query2.setParameter("first", partID);
            List result2 = query2.getResultList();
            Iterator it2 = result2.iterator();
            FinishedGoodEntity f = (FinishedGoodEntity)it2.next();
        
            if (!result1.isEmpty() && !result2.isEmpty()) {
                List allGoods = p.getFinishedGoods();
                allGoods.remove(f);
                p.setParts(allGoods);
                em.persist(p);
                return true;
            }
        } else if (type.equals("Retail Product")) {
            Query query2 = em.createQuery("SELECT r FROM RetailProductEntity WHERE r.modelID =:first");
            query2.setParameter("first", partID);
            List result2 = query2.getResultList();
            Iterator it2 = result2.iterator();
            RetailProductEntity r = (RetailProductEntity)it2.next();
        
            if (!result1.isEmpty() && !result2.isEmpty()) {
                List allRetailPdts = p.getRetailProducts();
                allRetailPdts.remove(r);
                p.setParts(allRetailPdts);
                em.persist(p);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean editQtyOfPurchaseOrder(Long purchaseID, Long partID, int quantity) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity p WHERE p.purchaseID=:purchaseID");
        query1.setParameter("purchaseID", purchaseID);
        PurchaseOrderEntity p = (PurchaseOrderEntity) query1.getSingleResult();
            
        String type = p.getType();
        //get list of parts & qty in a purchase order
        List qty = p.getQuantity();
        
        if (type.equals("Raw Material")) {
            if (p != null) {
                List parts = p.getParts();
                // return index of partID & its quantity
                int partQtyIndex = parts.indexOf(partID);
                // set updated quantity
                qty.set(partQtyIndex, quantity);
                em.refresh(p);
                return true;
            } 
        } else if (type.equals("Finished Good")) {
            if (p != null) {
                List finishedGoods = p.getFinishedGoods();
                // return index of partID & its quantity
                int partQtyIndex = finishedGoods.indexOf(partID);
                // set updated quantity
                qty.set(partQtyIndex, quantity);
                em.refresh(p);
                return true;
            } 
        } else if (type.equals("Retail Product")) {
            if (p != null) {
                List retailPdts = p.getRetailProducts();
                // return index of partID & its quantity
                int partQtyIndex = retailPdts.indexOf(partID);
                // set updated quantity
                qty.set(partQtyIndex, quantity);
                em.refresh(p);
                return true;
            } 
        } 
        return false;
    }
  
    /*public void sendRawMaterialPurchaseOrder(Long purchaseID, Long sendTo) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.purchaseID =:first");
        query1.setParameter("first", purchaseID);
        PurchaseOrderEntity po = (PurchaseOrderEntity) query1.getSingleResult();
        
        Query query2 = em.createQuery("SELECT s FROM SupplierEntity WHERE s.sendTo =:first");
        query2.setParameter("first", sendTo);
        SupplierEntity supplier = (SupplierEntity) query2.getSingleResult();
    }*/

    
    @Override
    public List<ArrayList> viewUncompletedPurchaseOrderList(Long userID) {
    //displays all the purchase orders of the userID that are not completed
        List<ArrayList> al = new ArrayList();
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.sendFrom =:second");
        query1.setParameter("second", userID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        while(it1.hasNext()) {
            ArrayList alc = new ArrayList();
            PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
            String pStatus = p.getStatus();
            Long purchaseID = p.getPurchaseID();
            if (!pStatus.equals("completed")){
                System.out.println("print modelid in getpurchaseid" + purchaseID);
                alc.add(purchaseID);
                String type = p.getType();
                alc.add(type);
                alc.add(pStatus);
                String date = p.getPurchaseDate();  // date changed to string
                String time = p.getPurchaseTime();  // time changed to string
                alc.add(date);
                alc.add(time);
            }   
        }
        return al;
    }    

    @Override
    public List<ArrayList> viewCompletedPurchaseOrderList(Long userID) {
      //displays all the purchase orders of the userID that are completed
        List<ArrayList> al = new ArrayList();
     /*   Query query1 = em.createQuery("SELECT p FROM PurchaseEntity WHERE p.sendFrom =:second");
        query1.setParameter("second", userID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        while(it1.hasNext()) {
            ArrayList alc = new ArrayList();
            PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
            String pStatus = p.getStatus();
            Long purchaseID = p.getId();
            if (pStatus.equals("completed")){
                System.out.println("print modelid in getpurchaseid" + purchaseID);
                alc.add(purchaseID);
                String type = p.getType();
                alc.add(type);
                alc.add(pStatus);
                Date date = p.getPurchaseDate();
                Time time = p.getPurchaseTime();
                alc.add(date);
                alc.add(time);
            }   
        }*/
        System.out.println("Testing");
        return al;
        
    }
    @Override
    public List<ArrayList> viewPurchaseOrderDetails(String purchaseID) {
        List<ArrayList> al = new ArrayList();
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        while(it1.hasNext()) {
            ArrayList alc = new ArrayList();
            PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
            alc.add(purchaseID);
            String status = p.getStatus();
            String date = p.getPurchaseDate(); //date changed to string
            String time = p.getPurchaseTime();   //time changed to string
            double cost = p.getCost();
            Long sendFrom = p.getSendFrom();
            Long sentTo = p.getSendTo();
            String type = p.getType();
            List qty = p.getQuantity();
            alc.add(type);
            alc.add(status);
            alc.add(date);
            alc.add(time);
            alc.add(cost);
            alc.add(sendFrom);
            alc.add(sentTo);
            alc.add(qty);
            List<FinishedGoodEntity> finishedGoods = p.getFinishedGoods();
            Iterator it2 = finishedGoods.iterator();
            while(it2.hasNext()) {
                
            }    
            List<FinishedGoodEntity> rawMats = p.getFinishedGoods();
            Iterator it3 = finishedGoods.iterator();
            while(it3.hasNext()) {
                
            }
        }   
        return al;
    }
    
    @Override
    public void setStatusPending(Long purchaseID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
        p.setStatus("pending");
        em.persist(p);
    }
    
    @Override
    public void setStatusSent(Long purchaseID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
        p.setStatus("sent");
        em.persist(p);
    }
    
    @Override
    public void setStatusReceived(Long purchaseID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
        p.setStatus("received");
        em.persist(p);
    }
    
    @Override
    public void setStatusInProgress(Long purchaseID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
        p.setStatus("in progress");
        em.persist(p);
    }
    
    @Override
    public void setStatusCompleted(Long purchaseID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
        p.setStatus("completed");
        em.persist(p);
    }
}
