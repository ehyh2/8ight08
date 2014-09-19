/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userAccount;

import javax.ejb.Local;

/**
 *
 * @author Esther Hong
 */
@Local
public interface userSessionBeanLocal {

    boolean login(String username, String password);

    boolean logout(Long id);

    Long createAcc(String username, String password, String userType);

    boolean changePassword(Long id, String newPass);

    boolean editAccess(Long id, String newType);

    boolean delAcc(Long id);
    
}
