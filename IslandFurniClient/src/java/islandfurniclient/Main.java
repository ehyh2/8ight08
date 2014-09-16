/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islandfurniclient;

import bean.GlobalBeanRemote;
import bean.ManuBeanRemote;
//import entity.RawMaterialEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author Toshiba
 */
public class Main {

    @EJB

    private static GlobalBeanRemote gb;
    private static ManuBeanRemote mb;
    Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Main client = new Main();
        client.doIt(args);
    }

    public void doIt(String[] args) {
        try {
            String choice = "";
            while (!choice.equals("0")) {
                System.out.println("\n\n\t\tWelcome");
                System.out.println("1. create bom");
                System.out.println("2. view lead time");
                System.out.println("3. view lot size");
                System.out.println("0.  Exit");

                System.out.print("\nEnter choice: ");
                choice = sc.next();
                sc.nextLine();

                if (choice.equals("1")) {
                    displayCreateBOM();
                } else if (choice.equals("2")) {
                    displayViewLeadTime();
                } else if (choice.equals("3")) {
                    displayViewLotSize();
                } else if (choice.equals("0")) {
                    return;
                } else {
                    System.out.println("Invalid Choice");
                }
            }//end while
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            System.out.println(ex.getMessage());
        }
    }

    private void displayCreateBOM() {

        String rm;
        String qty;
        List<String> rmlist = new ArrayList<String>();
        List<String> qtylist = new ArrayList<String>();

        try {
            System.out.println("\n\n\t\tCreate a BOM");
            System.out.println("Enter raw mats:");

            rm = sc.nextLine();
            rmlist.add(rm);
            System.out.println("Enter qty:");
            qty = sc.nextLine();
            qtylist.add(qty);

            boolean success = gb.createBOM(rmlist, qtylist);
            if (success) {
                System.out.println("\nBom added successfully.\n");
            } else {
                System.out.println("\nBom was not added\n");
            }

        } catch (Exception ex) {
            System.out.println("\nFailed to create"
                    + "bom because "
                    + ex.getMessage() + "\n");
        }

    }

    private void displayViewLeadTime() {
        System.out.println("Lead times:");
        List<String> leadTime = new ArrayList<String>();
        try {
            leadTime = mb.viewLeadTime();
            for (String s : leadTime) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayViewLotSize() {
        System.out.println("Lot sizes");
        List<String> lotSize = new ArrayList<String>();
        try {
            lotSize = mb.viewLotSize();
            for (String s : lotSize) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
