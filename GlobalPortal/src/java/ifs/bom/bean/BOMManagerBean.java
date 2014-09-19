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

    private long rm;
    private int qty;
    private List<Long> rmList;
    private List<Integer> qtyList;
    private String id;
    private String message = null;
    private List<String> bomList;
    private List<String> filteredBomList;

    /**
     * Creates a new instance of BOMManagerBean
     */
    public BOMManagerBean() {
        qtyList = new ArrayList<>();
        rmList = new ArrayList<>();

    }

    public List<String> getFilteredBomList() {
        return filteredBomList;
    }

    public void setFilteredBomList(List<String> filteredBomList) {
        this.filteredBomList = filteredBomList;
    }

    public List<String> getBomList() {
        return bomList;
    }

    public void setBomList(List<String> bomList) {
        this.bomList = bomList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getRm() {
        return rm;
    }

    public void setRm(long rm) {
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
    }

    public List<Integer> getQtyList() {
        return qtyList;
    }

    public void setQtyList(List<Integer> qtyList) {
        this.qtyList = qtyList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void saveBom(ActionEvent event) {

        boolean saveStatus = bomBean.createBOM(rmList, qtyList);
        if (saveStatus) {
            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
            System.out.println("Added");

        } else {
            System.out.println("not added");
            // including HTML requires that you set escape="false" in view
            //message = "<p>Sorry, " + country + " not added.</p>";

            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

        }
    }

    public void addToBom(ActionEvent event) {
        //int q = Integer.parseInt("qty");
        qtyList.add(qty);
        rmList.add(rm);
        message = "Added";

    }

    public void delBom(ActionEvent event) {
        boolean delStatus = bomBean.deleteBOM(id);
        if (delStatus) {
            message = "BOM deleted.";
            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

        } else {

            // including HTML requires that you set escape="false" in view
            message = "<p>Sorry, BOM " + id + " not deleted.</p>";
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

        }
    }

    public void addBomItem(ActionEvent event) {

    }

    public void delBomItem(ActionEvent event) {

    }

    public void editBomQty(ActionEvent event) {

    }

//    public void viewBom(ActionEvent event) {
//        bomList = bomBean.viewBOMList();
//        
//    }
//    
//    public void searchBom(ActionEvent event) {
//        filteredBomList = bomBean.searchBOM(id);
//        
//    }
}
