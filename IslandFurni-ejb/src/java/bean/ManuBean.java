/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.ManuFacEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class ManuBean implements ManuBeanRemote {

    @PersistenceContext
    private EntityManager em;


    @Override
    public boolean addMF(String country) {
        ManuFacEntity manuFacil = new ManuFacEntity(country);

        // ManuFacil found = em.find(ManuFacil.class, country);
        //if (found == null) {

        em.persist(manuFacil);
        return true;
        /*}
         else {
         System.out.println("MF exists.");
         return false;
         }*/
    }
}
