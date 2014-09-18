/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilation;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Toshiba
 */
@Local
public interface CompilationBeanLocal {

    public Long compilePurchaseOrder(String dateFrom, String dateTo, Long sendFrom, Long sendTo);
    public boolean sendPurchaseOrder(Long orderId);
    public List<String> viewPurchaseOrders();
    public List<String> viewTransactionHistory();
}
