/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manuFac;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface ManuBeanLocal {
    
    public boolean addMF(String country, List goodsQty, List partsQty);
    public boolean delMF(Long mfID);
}
