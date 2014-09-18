/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leadTimeLotSize;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Toshiba
 */
@Local
public interface LeadTimeLotSizeBeanLocal {

    public List<String> viewLeadTime();
    public List<String> viewLotSize();
    public boolean addLeadTime(String id, String leadTime, String supplierId);
    public boolean editLeadTime(String id, String leadTime, String supplierId);
    public boolean deleteLeadTime(String id, String supplierId);
    public List<String> viewLeadTimeSupplier(String supplierId);
    public boolean addLotSize(String id, String lotSize, String supplierId);
    public boolean editLotSize(String id, String lotSize, String supplierId);
    public boolean deleteLotSize(String id, String supplierId);
    public List<String> viewLotSizeSupplier(String supplierId);
}
