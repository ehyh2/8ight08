/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bom;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Toshiba
 */
@Local
public interface BomBeanLocal {
    public boolean createBOM(List<Long> rm, List<Integer> qty);
    public boolean deleteBOM(Long id);
    public boolean editBOM(Long id, List<Long> rm, List<Integer> qty);
    public List<String> searchBOM(Long id);
    public List<String> viewBOMList();
}
