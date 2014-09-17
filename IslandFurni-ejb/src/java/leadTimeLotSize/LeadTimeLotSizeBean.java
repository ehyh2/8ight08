/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package leadTimeLotSize;

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
public class LeadTimeLotSizeBean implements LeadTimeLotSizeBeanLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    //for manu sk to view list of lead time
    @Override
    public List<String> viewLeadTime() {
        List entityList = new ArrayList();
        Query query = em.createQuery("SELECT rm FROM RawMaterialEntity rm");
        List results = query.getResultList();
        if (!results.isEmpty()) {
            for (Object o : results) {
                RawMaterialEntity rm = (RawMaterialEntity) o;
                String entity = new String();
                entity = entity + String.valueOf(rm.getId());
                entity = entity + "#" + String.valueOf(rm.getLeadTime());
            }
        } else {
            System.out.println("No lead time to view");
        }
        return entityList;
    }
    
    //for manu sk to view lot size details
    @Override
    public List<String> viewLotSize() {
        List entityList = new ArrayList();
        Query query = em.createQuery("SELECT rm FROM RawMaterialEntity rm");
        List results = query.getResultList();
        if (!results.isEmpty()) {
            for (Object o : results) {
                RawMaterialEntity rm = (RawMaterialEntity) o;
                String entity = new String();
                entity = entity + String.valueOf(rm.getId());
                entity = entity + "#" + String.valueOf(rm.getLotSize());
            }
        } else {
            System.out.println("No lot size to view");
        }
        return entityList;
    }
    
    //for supplier to add lead time
    @Override
    public boolean addLeadTime(Long id, int leadTime){
        
        try{
        Query query = em.createQuery("SELECT rm FROM RawMaterialEntity rm where rm.id=:first");
        query.setParameter("first", id);
                List results = query.getResultList();
                if (!results.isEmpty()) {
                    for (Object o : results) {
                        RawMaterialEntity raw;
                        raw = (RawMaterialEntity) o;
                        raw.setLeadTime(leadTime);
                        em.persist(raw);
                        return true;
                    }
                } else {
                    System.out.println("Cannot find raw material");
                    return false;
                }
        } catch(Exception e){
            System.out.println("exception in add lead time method");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
    
    //for supplier to edit lead time
    @Override
    public boolean editLeadTime(Long id, int leadTime){
        try{
        Query query = em.createQuery("SELECT rm FROM RawMaterialEntity rm where rm.id=:first");
        query.setParameter("first", id);
                List results = query.getResultList();
                if (!results.isEmpty()) {
                    for (Object o : results) {
                        RawMaterialEntity raw;
                        raw = (RawMaterialEntity) o;
                        raw.setLeadTime(leadTime);
                        em.persist(raw);
                        return true;
                    }
                } else {
                    System.out.println("Cannot find raw material");
                    return false;
                }
        } catch(Exception e){
            System.out.println("exception in edit lead time method");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
        
    }
    
    //for supplier to delete lead time
    @Override
    public boolean deleteLeadTime(Long id){
        
        try{
        Query query = em.createQuery("SELECT rm FROM RawMaterialEntity rm where rm.id=:first");
        query.setParameter("first", id);
                List results = query.getResultList();
                if (!results.isEmpty()) {
                    for (Object o : results) {
                        RawMaterialEntity raw;
                        raw = (RawMaterialEntity) o;
                        raw.setLeadTime(0);
                        em.persist(raw);
                        return true;
                    }
                } else {
                    System.out.println("Cannot find raw material");
                    return false;
                }
        } catch(Exception e){
            System.out.println("exception in delete lead time method");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
        
    }
    
    //for supplier to view lead time
    //public List<String> viewLeadTime(){
    //}
    
    //for supplier to add lot size
    @Override
    public boolean addLotSize(Long id, int lotSize){
        
        try{
        Query query = em.createQuery("SELECT rm FROM RawMaterialEntity rm where rm.id=:first");
        query.setParameter("first", id);
                List results = query.getResultList();
                if (!results.isEmpty()) {
                    for (Object o : results) {
                        RawMaterialEntity raw;
                        raw = (RawMaterialEntity) o;
                        raw.setLotSize(lotSize);
                        em.persist(raw);
                        return true;
                    }
                } else {
                    System.out.println("Cannot find raw material");
                    return false;
                }
        } catch(Exception e){
            System.out.println("exception in add lot size method");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
    
    //for supplier to edit lot size
    @Override
    public boolean editLotSize(Long id, int lotSize){
        
        try{
        Query query = em.createQuery("SELECT rm FROM RawMaterialEntity rm where rm.id=:first");
        query.setParameter("first", id);
                List results = query.getResultList();
                if (!results.isEmpty()) {
                    for (Object o : results) {
                        RawMaterialEntity raw;
                        raw = (RawMaterialEntity) o;
                        raw.setLotSize(lotSize);
                        em.persist(raw);
                        return true;
                    }
                } else {
                    System.out.println("Cannot find raw material");
                    return false;
                }
        } catch(Exception e){
            System.out.println("exception in edit lot size method");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
    
    //for supplier to delete lot size
    @Override
    public boolean deleteLotSize(Long id){
        
        try{
        Query query = em.createQuery("SELECT rm FROM RawMaterialEntity rm where rm.id=:first");
        query.setParameter("first", id);
                List results = query.getResultList();
                if (!results.isEmpty()) {
                    for (Object o : results) {
                        RawMaterialEntity raw;
                        raw = (RawMaterialEntity) o;
                        raw.setLotSize(0);
                        em.persist(raw);
                        return true;
                    }
                } else {
                    System.out.println("Cannot find raw material");
                    return false;
                }
        } catch(Exception e){
            System.out.println("exception in delete lot size method");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
        
    }
    
    //for supplier to view lot size
    //public List<String> viewLotSize(){
    //}
}
