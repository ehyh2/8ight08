/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finishedGoods;

import entity.DispatchRequestEntity;
import entity.FinishedGoodEntity;
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

    //for manu sk to create dispatch request
    @Override
    public Long createDispatchRequest(Long sendFrom, Long sendTo) {
        DispatchRequestEntity dispatch = new DispatchRequestEntity();
        try {

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
        } catch (Exception e) {
            System.out.println("exception in the create dispatch request method");
            System.out.println(e.getMessage());
        }
        return dispatch.getDispatchId();
    }

    //for manu sk to delete the dispatch request
    @Override
    public boolean deleteDispatchRequest(Long dispatchId) {
        try {
            Query query = em.createQuery("SELECT d FROM DispatchRequestEntity d WHERE d.dispatchId =:first");
            query.setParameter("first", dispatchId);
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    DispatchRequestEntity dispatch = (DispatchRequestEntity) o;
                    em.remove(dispatch);
                    return true;
                }
            } else {
                System.out.println("dispatch request does not exist");
                return false;
            }
        } catch (Exception e) {
            System.out.println("exception in delete dispatch request method");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    //for manu sk to add item to dispatch request
    @Override
    public boolean addItemToRequest(Long dispatchId, Long modelId, int qty) {
        try {
            Query query = em.createQuery("SELECT d FROM DispatchRequestEntity d WHERE d.dispatchId =:first");
            query.setParameter("first", dispatchId);
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    DispatchRequestEntity dispatch = (DispatchRequestEntity) o;
                    Query query1 = em.createQuery("SELECT fg FROM FinishedGoodEntity fg WHERE fg.modelId =:second");
                    query1.setParameter("second", modelId);
                    List results1 = query1.getResultList();
                    if (results1.size() != 0) {
                        for (Object o1 : results1) {
                            FinishedGoodEntity finished;
                            finished = (FinishedGoodEntity) o1;
                            List<FinishedGoodEntity> fgList = new ArrayList<FinishedGoodEntity>();
                            fgList = dispatch.getFinishedGoods();
                            fgList.add(finished);
                            dispatch.setFinishedGoods(fgList);
                            List<Integer> qtyList = new ArrayList<Integer>();
                            qtyList = dispatch.getQuantity();
                            qtyList.add(qty);
                            dispatch.setQuantity(qtyList);
                            em.persist(dispatch);
                            return true;
                        }
                    } else {
                        System.out.println("finished good does not exist");
                    }
                    return false;
                }
            }
            System.out.println("dispatch request does not exist");
            return false;
        } catch (Exception e) {
            System.out.println("exception in add item to request method");
            System.out.println(e.getMessage());
        }
        return false;
    }

    //for manu sk to delete item from dispatch request
    @Override
    public boolean delItemFromRequest(Long dispatchId, Long modelId, int qty) {
        try {
            Query query = em.createQuery("SELECT d FROM DispatchRequestEntity d WHERE d.dispatchId =:first");
            query.setParameter("first", dispatchId);
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    DispatchRequestEntity dispatch = (DispatchRequestEntity) o;
                    Query query1 = em.createQuery("SELECT fg FROM FinishedGoodEntity fg WHERE fg.modelId =:second");
                    query1.setParameter("second", modelId);
                    List results1 = query1.getResultList();
                    if (results1.size() != 0) {
                        for (Object o1 : results1) {
                            FinishedGoodEntity finished;
                            finished = (FinishedGoodEntity) o1;
                            List<FinishedGoodEntity> fgList = new ArrayList<FinishedGoodEntity>();
                            fgList = dispatch.getFinishedGoods();
                            fgList.remove(finished);
                            dispatch.setFinishedGoods(fgList);
                            List<Integer> qtyList = new ArrayList<Integer>();
                            qtyList = dispatch.getQuantity();
                            qtyList.remove(qty);
                            dispatch.setQuantity(qtyList);
                            em.persist(dispatch);
                            return true;
                        }
                    } else {
                        System.out.println("finished good does not exist");
                    }
                    return false;
                }
            }
            System.out.println("dispatch request does not exist");
            return false;
        } catch (Exception e) {
            System.out.println("exception in delete item from dispatch method");
            System.out.println(e.getMessage());
        }
        return false;
    }

    //for manu sk to edit item qty in dispatch request
    @Override
    public boolean editQtyInRequest(Long dispatchId, Long modelId, int qty) {
        try {
            Query query = em.createQuery("SELECT d FROM DispatchRequestEntity d WHERE d.dispatchId =:first");
            query.setParameter("first", dispatchId);
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    DispatchRequestEntity dispatch = (DispatchRequestEntity) o;
                    List<Integer> qtyList = new ArrayList<Integer>();
                    if (!qtyList.isEmpty()) {
                        List<FinishedGoodEntity> fgList = new ArrayList<FinishedGoodEntity>();
                        fgList = dispatch.getFinishedGoods();
                        int index = fgList.indexOf(modelId);
                        qtyList.set(index, qty);
                        em.persist(dispatch);
                        return true;
                    } else {
                        System.out.println("finished good does not exist");
                    }
                    return false;
                }
            }
            System.out.println("dispatch request does not exist");
            return false;
        } catch (Exception e) {
            System.out.println("exception in edit qty in request method");
            System.out.println(e.getMessage());
        }
        return false;
    }

    //for manu sk to submit the dispatch request
    @Override
    public boolean submitDispatchRequest(Long dispatchId){
        try {
            Query query = em.createQuery("SELECT d FROM DispatchRequestEntity d WHERE d.dispatchId =:first");
            query.setParameter("first", dispatchId);
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    DispatchRequestEntity dispatch = (DispatchRequestEntity) o;
                    dispatch.setStatus("submitted");
                    return true;
                }
            }
            System.out.println("dispatch request does not exist");
            return false;
        } catch (Exception e) {
            System.out.println("exception in submit request method");
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    
    //for manu manager to view dispatch requests
    @Override
    public List<String> viewDispatchRequests() {
        List<String> viewList = new ArrayList<String>();
        try {
            Query query = em.createQuery("SELECT d FROM DispatchRequestEntity d");
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    DispatchRequestEntity dispatch = (DispatchRequestEntity) o;

                    //to get the dispatch details
                    String result = new String();
                    result = result + "#" + String.valueOf(dispatch.getDispatchId());
                    result = result + "#" + dispatch.getDispatchDate();
                    result = result + "#" + dispatch.getDispatchTime();
                    result = result + "#" + String.valueOf(dispatch.getSendFrom());
                    result = result + "#" + String.valueOf(dispatch.getSendTo());
                    result = result + "#" + String.valueOf(dispatch.getCost());
                    result = result + "#" + dispatch.getStatus();
                    
                    for (Object r : dispatch.getFinishedGoods()) {
                        FinishedGoodEntity fg = (FinishedGoodEntity) r;
                        result = result + "#" + String.valueOf(fg.getModelID());
                    }

                    for (Object q : dispatch.getQuantity()) {
                        Integer i = (Integer) q;
                        result = result + "#" + String.valueOf(i);
                    }
                    viewList.add(result);
                }
            } else {
                System.out.println("No dispatch requets to view");
            }
        } catch (Exception e) {
            System.out.println("exception in view dispatch request method");
            System.out.println(e.getMessage());
        }

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
