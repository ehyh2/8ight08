/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package purchaseOrder;

import entity.FinishedGoodEntity;
import entity.PurchaseOrderEntity;
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

    public void setStatusPending(Long purchaseID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
        p.setStatus("pending");
        em.persist(p);
    }
    
    public void setStatusSent(Long purchaseID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
        p.setStatus("sent");
        em.persist(p);
    }
    
    public void setStatusReceived(Long purchaseID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
        p.setStatus("received");
        em.persist(p);
    }
    
    public void setStatusInProgress(Long purchaseID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
        p.setStatus("in progress");
        em.persist(p);
    }
    
    public void setStatusCompleted(Long purchaseID) {
        Query query1 = em.createQuery("SELECT p FROM PurchaseOrderEntity WHERE p.id =:second");
        query1.setParameter("second", purchaseID);
        List results1 = query1.getResultList();
        Iterator it1 = results1.iterator();
        PurchaseOrderEntity p = (PurchaseOrderEntity)it1.next();
        p.setStatus("completed");
        em.persist(p);
    }
    public void createRawMaterialPurchaseOrder(String type, Long sendFrom, Long sendTo) {
        try {
            PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
            
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
        //return purchaseOrderEntity.getPurchaseID();
    }
}
