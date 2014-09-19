/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userAccount;

import entity.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author Esther Hong
 */
@Stateless
public class userSessionBean implements userSessionBeanLocal {
    EntityManager em;

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean logout(Long id) {
        return false;
    }

    @Override
    public Long createAcc(String username, String password, String userType) {
        UserEntity user = new UserEntity(username, password, userType);
        em.persist(user);
        Long id = user.getId();
        return id;
    }

    @Override
    public boolean changePassword(Long id, String newPass) {
        return false;
    }

    @Override
    public boolean editAccess(Long id, String newType) {
        return false;
    }

    @Override
    public boolean delAcc(Long id) {
        return false;
    }
}
