/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ifs.manuuser.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Esther Hong
 */
@ManagedBean(name = "mfUserBean")
@ViewScoped
public class MFUserBean {
    
    private String username;
    private String password;
    /**
     * Creates a new instance of MFUserBean
     */
    public MFUserBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void login(ActionEvent action) {
        
    }
    
    
}
