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
    public boolean createBOM(List<String> rm, List<String> qty);
    public boolean deleteBOM(String id);
    public boolean editBOM(String id, List<String> rm, List<String> qty);
    public List<String> searchBOM(String id);
    public List<String> viewBOMList();
}
