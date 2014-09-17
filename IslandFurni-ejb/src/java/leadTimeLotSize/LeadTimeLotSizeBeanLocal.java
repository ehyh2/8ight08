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
    public boolean addLeadTime(Long id, int leadTime);
    public boolean editLeadTime(Long id, int leadTime);
    public boolean deleteLeadTime(Long id);
    //public List<String> viewLeadTime();
    public boolean addLotSize(Long id, int lotSize);
    public boolean editLotSize(Long id, int lotSize);
    public boolean deleteLotSize(Long id);
    //public List<String> viewLotSize();
}
