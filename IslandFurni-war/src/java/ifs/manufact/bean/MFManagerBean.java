/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifs.manufact.bean;

import bean.ManuBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Esther Hong
 */
@ManagedBean
@SessionScoped
public class MFManagerBean implements Serializable {

    @EJB
    private ManuBean manuBean;

    private String country;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void saveNewMF(String country) {

        boolean addStatus = manuBean.addMF(country);
        if (country != null) {
            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

            message = "Yay! You got it!";
        } else {

            // including HTML requires that you set escape="false" in view
            message = "<p>Sorry, " + country + " not added.</p>";

        }

    }
}
