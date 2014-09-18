/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilation;

import entity.CompiledOrderEntity;
import entity.PurchaseOrderEntity;
import entity.RetailProductEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class CompilationBean implements CompilationBeanLocal {

    @PersistenceContext
    private EntityManager em;

    //for country manager to compile purchase orders
    @Override
    public Long compilePurchaseOrder(String dateFrom, String dateTo, Long sendFrom, Long sendTo) {
        CompiledOrderEntity order = new CompiledOrderEntity();
        List<PurchaseOrderEntity> purchases = new ArrayList<PurchaseOrderEntity>();
        List<RetailProductEntity> products = new ArrayList<RetailProductEntity>();
        List<Integer> qty = new ArrayList<Integer>();

        try {

            SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gmtDateFormat.setTimeZone(TimeZone.getTimeZone("Singapore"));
            String dateTime = gmtDateFormat.format(new Date());
            String[] splited = dateTime.split(" ");
            String date = splited[0];
            String time = splited[1];

            Query query = em.createQuery("SELECT p FROM PurchaseOrderEntity p"); //where the date is right
            //query.setParameter("first", dateFrom);
            //query.setParameter("second", dateTo);
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    PurchaseOrderEntity purchase = (PurchaseOrderEntity) o;
                    purchases.add(purchase);
                    List<RetailProductEntity> retail = new ArrayList<RetailProductEntity>();
                    retail = purchase.getRetailProducts();
                    for (Object x : retail) {
                        RetailProductEntity r = (RetailProductEntity) x;
                        Long retailId = r.getModelID();
                        //check if model id exists in the list alr
                        if (!products.contains(r)) {
                            products.add(r);
                            //get the quantity
                            //int index = retail.indexOf(retailId);
                            //qty.set(index, quantity);

                        } else {
                            //add on to the quantity
                        }
                    }
                }
            } else {
                System.out.println("no purchase orders to compile");

            }

            order.setPurchaseDate(date);
            order.setPurchaseTime(time);
            order.setDateFrom(dateFrom);
            order.setDateTo(dateTo);
            order.setSendFrom(sendFrom);
            order.setSendTo(sendTo);
            order.setStatus("pending");
            order.setPurchaseOrders(purchases);
            order.setRetailProducts(products);
            order.setQty(qty);
            
            em.persist(order);
        } catch (Exception e) {
            System.out.println("exception in the compile purchase orders request method");
            System.out.println(e.getMessage());
        }
        return order.getId();
    }

    //for country manager to send the compiled order
    @Override
    public boolean sendPurchaseOrder(Long orderId) {
        try {
            Query query = em.createQuery("SELECT c FROM CompiledOrderEntity c WHERE c.id =:first");
            query.setParameter("first", orderId);
            List results = query.getResultList();
            if (!results.isEmpty()) {
                for (Object o : results) {
                    CompiledOrderEntity order = (CompiledOrderEntity) o;
                    order.setStatus("sent");
                    return true;
                }
            }
            System.out.println("compiled order does not exist");
            return false;
        } catch (Exception e) {
            System.out.println("exception in send compiled purchase order method");
            System.out.println(e.getMessage());
        }
        return false;
    }

    //for country manager to view the current compiled orders
    @Override
    public List<String> viewPurchaseOrders() {
        List<String> viewList = new ArrayList<String>();
        return viewList;
    }

    //for country manager to view the transaction history
    @Override
    public List<String> viewTransactionHistory() {
        List<String> viewList = new ArrayList<String>();
        return viewList;
    }
}
