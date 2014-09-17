/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supplier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Liwei__xD
 */
@Local
public interface SupplierBeanLocal {

    public List<ArrayList> viewListofSupplier();
    //returns list of arraylist w supplierID
    //for global to view and select supplier to setPassword for the chosen supplier
    //for manufacturing m to view and select supplier to view more details

    public boolean delSupplier(Long ssID);

    public boolean addSupplier(String country, List<ArrayList> rawMats, Date contractEndDate);
    //when supplier is created, the rawmaterial that is supplied should be determined as well
    //Each row of the list should contain an arraylist, consisting of Long rawMatID, int leadTime, int lotSize, int minQty
    //the contract enddate should be determined as well

    public List<ArrayList> viewSupplierDetails(Long supplierID);
    //takes in supplierID to return one supplier's details
    //first line on the List contains arraylist of country etc.
    //the rest of the lines onwards contains details of the raw materials that the supplier is supplying

    boolean updateSupplierDetails(Long supplierID, String contactNo, String country);
    // This function is for suppliers themselves to update their contact number and country
    //returns 1 if sucessfully updated
    
    boolean updateSupplierContractEndDate(Date contractEndDate);
    //This function is for manufacturingM to update the contractEndDate of supplliers;
    // returns 1 if successfully updated

    boolean searchSupplierExist(Long supplierID);
    //returns 1 if supplier exist

    boolean sendSupplierListToGlobal();
    // 

    boolean setSupplierPassword(Long supplierID);
    // for global to set the supplier password when they click on the supplier in the list of
    //supplierID
    
}
