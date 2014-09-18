/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finishedGoods;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Toshiba
 */
@Local
public interface FinishedGoodsBeanLocal {

    public Long createDispatchRequest(Long sendFrom, Long sendTo);
            
    public List<String> viewDispatchRequests();

    public boolean approveDispatch(Long dispatchId);

    public boolean rejectDispatch(Long dispatchId);

    public List<String> viewOrderSummary();

    public boolean updateOrderStatus(Long purchaseId);
}
