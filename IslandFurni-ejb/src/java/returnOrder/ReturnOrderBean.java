/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package returnOrder;

import entity.ReturnOrderEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Charmaine
 */
@Stateless
public class ReturnOrderBean implements ReturnOrderBeanLocal {
    @PersistenceContext
    private EntityManager em;
    private ReturnOrderEntity returnOrderEntity;

}
