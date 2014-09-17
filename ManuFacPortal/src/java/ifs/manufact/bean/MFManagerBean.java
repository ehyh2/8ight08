/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifs.manufact.bean;

import manuFac.ManuBeanLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Esther Hong
 */
@ManagedBean(name = "mfManagerBean")
@SessionScoped
public class MFManagerBean implements Serializable {

    @EJB
    private ManuBeanLocal manuBean;

    private String country;
    private String message;
    private Long mfID;
    private List goodsQty;
    private List partsQty;

    /**
     * Creates a new instance of MFManagerBean
     */
    public MFManagerBean() {
    }

    @PostConstruct
    public void init() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getMfID() {
        return mfID;
    }

    public void setMfID(Long mfID) {
        this.mfID = mfID;
    }

    public List getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(List goodsQty) {
        this.goodsQty = goodsQty;
    }

    public List getPartsQty() {
        return partsQty;
    }

    public void setPartsQty(List partsQty) {
        this.partsQty = partsQty;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void saveNewMF(ActionEvent event) {

        boolean addStatus = manuBean.addMF(country, goodsQty, partsQty);
        if (addStatus) {
            message = "Manufacturing Facility added.";
            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

        } else {

            // including HTML requires that you set escape="false" in view
            message = "<p>Sorry, Manufacturing Facility of " + country + " not added.</p>";

        }
    }

    public void delMF(ActionEvent event) {

        boolean delStatus = manuBean.delMF(mfID);
        if (delStatus) {
            message = "Manufacturing Facility deleted.";
            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

        } else {

            // including HTML requires that you set escape="false" in view
            message = "<p>Sorry, Manufacturing Facility of " + mfID + " not deleted.</p>";

        }

    }
}
