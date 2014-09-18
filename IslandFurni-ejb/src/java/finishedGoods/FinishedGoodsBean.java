/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finishedGoods;

import entity.DispatchRequestEntity;
import entity.PurchaseOrderEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Toshiba
 */
@Stateless
public class FinishedGoodsBean implements FinishedGoodsBeanLocal {

    @PersistenceContext
    private EntityManager em;

    //for manu sk to create dispatch request??
    //not inside the list of functionalities i think?
    @Override
    public Long createDispatchRequest(Long sendFrom, Long sendTo){
        DispatchRequestEntity dispatch = new DispatchRequestEntity();
        try{
            
            SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gmtDateFormat.setTimeZone(TimeZone.getTimeZone("Singapore"));
            String dateTime = gmtDateFormat.format(new Date());
            String[] splited = dateTime.split(" ");
            String date = splited[0];
            String time = splited[1];
            
            dispatch.setDispatchDate(date);
            dispatch.setDispatchTime(time);
            dispatch.setSendFrom(sendFrom);
            dispatch.setSendTo(sendTo);
            dispatch.setStatus("pending");
            
            em.persist(dispatch);
        } catch(Exception e){
            System.out.println("exception in the create dispatch request method");
            System.out.println(e.getMessage());
        }
        return dispatch.getDispatchId();
    }
    
    
    //for manu manager to view dispatch requests
    @Override
    public List<String> viewDispatchRequests() {
        List<String> viewList = new ArrayList<String>();
        return viewList;
    }

    //for manu manager to approve dispatch request
    @Override
    public boolean approveDispatch(Long dispatchId) {
        Query query = em.createQuery("SELECT d FROM DispatchRequestEntity d WHERE d.dispatchId =:first");
        query.setParameter("first", dispatchId);
        List results = query.getResultList();
        if (!results.isEmpty()) {
            for (Object o : results) {
                DispatchRequestEntity dispatch;
                dispatch = (DispatchRequestEntity) o;
                dispatch.setStatus("approved");
                em.persist(dispatch);
                return true;
            }
        } else {
            System.out.println("Cannot find dispatch request");
            return false;
        }
        return false;
    }

    //for manu manager to reject dispatch request
    @Override
    public boolean rejectDispatch(Long dispatchId) {
        Query query = em.createQuery("SELECT d FROM DispatchRequestEntity d WHERE d.dispatchId =:first");
        query.setParameter("first", dispatchId);
        List results = query.getResultList();
        if (!results.isEmpty()) {
            for (Object o : results) {
                DispatchRequestEntity dispatch;
                dispatch = (DispatchRequestEntity) o;
                dispatch.setStatus("rejected");
                em.persist(dispatch);
                return true;
            }
        } else {
            System.out.println("Cannot find dispatch request");
            return false;
        }
        return false;
    }

    //for manu sk to view order summary
    @Override
    public List<String> viewOrderSummary() {
        List<String> viewList = new ArrayList<String>();
        return viewList;
    }

    //for manu sk to update the order status to delivered
    @Override
    public boolean updateOrderStatus(Long purchaseId) {
        Query query = em.createQuery("SELECT p FROM PurchaseOrderEntity p WHERE p.purchaseId =:second");
        query.setParameter("second", purchaseId);
        List results = query.getResultList();
        if (!results.isEmpty()) {
            for (Object o : results) {
                PurchaseOrderEntity purchase;
                purchase = (PurchaseOrderEntity) o;
                purchase.setStatus("delivered");
                em.persist(purchase);
                return true;
            }
        } else {
            System.out.println("Cannot find purchase order");
            return false;
        }
        return false;
    }
}
