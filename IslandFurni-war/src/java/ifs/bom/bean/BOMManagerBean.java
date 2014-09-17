/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifs.bom.bean;

import bom.BomBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Esther Hong
 */
@Named(value = "bomManagerBean")
@SessionScoped
public class BOMManagerBean implements Serializable {

    @EJB
    private BomBeanLocal bomBean;
    
    private Long rm;
    private int qty;
    private List<Long> rmList;
    private List<Integer> qtyList;
    private Long id;
    private int newQty;
    private String message = null;

    /**
     * Creates a new instance of BOMManagerBean
     */
    public BOMManagerBean() {
        qtyList = new ArrayList<>();
        rmList = new ArrayList<>();
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRm() {
        return rm;
    }

    public void setRm(Long rm) {
        this.rm = rm;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public List<Long> getRmList() {
        return rmList;
    }

    public void setRmList(List<Long> rmList) {
        this.rmList = rmList;
        //rmList.add(rm);
    }

    public List<Integer> getQtyList() {
        return qtyList;
    }

    public void setQtyList(List<Integer> qtyList) {
        this.qtyList = qtyList;
        //qtyList.add(qty);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNewQty() {
        return newQty;
    }

    public void setNewQty(int newQty) {
        this.newQty = newQty;
    }
    
    
    public void saveBom(ActionEvent event) {

        boolean saveStatus = bomBean.createBOM(rmList, qtyList);
        if (saveStatus) {
            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

        } else {

            // including HTML requires that you set escape="false" in view
            //message = "<p>Sorry, " + country + " not added.</p>";

        }
    }
    
    public void addToBom(ActionEvent event) {
        //int q = Integer.parseInt("qty");
        qtyList.add(qty);
        rmList.add(rm);
        message = "Added";
       
    }

}
