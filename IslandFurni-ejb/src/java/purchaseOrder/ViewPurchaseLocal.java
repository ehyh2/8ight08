/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package purchaseOrder;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Liwei__xD
 */
@Local
public interface ViewPurchaseLocal {
    public List<ArrayList> viewUncompletedPurchaseOrderList(Long userID);
    public List<ArrayList> viewCompletedPurchaseOrderList(Long userID);
    public List<ArrayList> viewPurchaseOrderDetails(String purchaseID);
    public void setStatusPending(Long purchaseID);
    public void setStatusSent(Long purchaseID);
    public void setStatusReceived(Long purchaseID);
    public void setStatusInProgress(Long purchaseID);
    public void setStatusCompleted(Long purchaseID);
    public void createRawMaterialPurchaseOrder(String type, Long sendFrom, Long sendTo);
    
}
