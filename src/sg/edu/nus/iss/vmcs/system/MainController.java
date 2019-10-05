/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.system;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.customer.ChangeGiver;
import sg.edu.nus.iss.vmcs.customer.CoinReceiver;
import sg.edu.nus.iss.vmcs.customer.CustomerPanel;
import sg.edu.nus.iss.vmcs.customer.DispenseController;
import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This object is the main controller of the vending machine&#46; It coordinates the main operations of the VMCS&#46;
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
/*
 * File amended to include the singleton pattern
 */
public class MainController {
	private SimulationController  simulatorCtrl;
	private MachineryController   machineryCtrl;
	private MaintenanceController maintenanceCtrl;
	private TransactionController txCtrl;
	private StoreController       storeCtrl;
	
	private DispenseController dispenseCtrl;
    private ChangeGiver changeGiver;
    private CoinReceiver coinReceiver;

	private String      propertyFile;

	/**
	 * This constructor creates an instance of MainController object.
	 * @param propertyFile the property file name.
	 */
	public MainController(String propertyFile) {
		this.propertyFile = propertyFile;
	}

	/**
	 * This method will initiate the creation of all the control objects necessary for
	 * simulation of the vending machine, and initiate the display of the SimulatorControlPanel
	 * (the main menu).
	 * @throws VMCSException if fail to initialize.
	 */
	public void start() throws VMCSException {
		try {
			initialize();
			simulatorCtrl.displaySimulatorControlPanel();
			simulatorCtrl.setSimulationActive(false);
		} catch (VMCSException e) {
			throw new VMCSException(e);
		}
	}

	/**
	 * This method creates all the control objects.
	 * @throws VMCSException if fail to load drinks properties or cash properties.
	 */
	public void initialize() throws VMCSException {
		try {
			Environment.initialize(propertyFile);
			CashPropertyLoader cashLoader =
				new CashPropertyLoader(Environment.getCashPropFile());
			DrinkPropertyLoader drinksLoader =
				new DrinkPropertyLoader(Environment.getDrinkPropFile());
			cashLoader.initialize();
			drinksLoader.initialize();
			storeCtrl = new StoreController(cashLoader, drinksLoader);
			storeCtrl.initialize();
			simulatorCtrl = new SimulationController(this);
			machineryCtrl = new MachineryController(this);
			machineryCtrl.initialize();
			maintenanceCtrl = new MaintenanceController(this);
			txCtrl = TransactionController.getInstance();
			txCtrl.setMainController(this);
			dispenseCtrl=new DispenseController(txCtrl);
	        coinReceiver=new CoinReceiver(txCtrl);
	        changeGiver=new ChangeGiver(txCtrl);
		} catch (IOException e) {
			throw new VMCSException(
				"MainController.initialize",
				e.getMessage());
		}
	}
	
	public void setupCustomer() {
        txCtrl.displayCustomerPanel();
    }
	
	public void setupMaintainer() {
	    maintenanceCtrl.displayMaintenancePanel();
    }
	
	public void setupSimulator() {
	    System.out.println("setupSimulator called from MainController");
        //MaintenanceController maintenanceCtrl;
        //maintenanceCtrl = mCtrl.getMaintenanceController();
        //MachineryController machCtrl;

        //machCtrl = getMachineryController();
        
        try {
            //getSimulationController().setupSimulator();
            
            // activate when not login
            // always diaply the door locked; isOpen false
            machineryCtrl.displayMachineryPanel();

            // display drink stock;
            machineryCtrl.displayDrinkStock();

            // display coin quantity;
            machineryCtrl.displayCoinStock();

            machineryCtrl.displayDoorState();
        } catch (VMCSException e) {
            System.out.println("SimulationController.setupSimulator:" + e);
        }
    }
	
	public void storeCoin(Coin c) throws VMCSException {
	    System.out.println("storeCoin called from MainController");
	    storeCtrl.storeCoin(c);
	}
	
	public void dispenseDrink(int idx) throws VMCSException {
	    System.out.println("dispenseDrink called from MainController");
	    storeCtrl.dispenseDrink(idx);
	}
	
	public void giveChange(int idx, int numOfCoins) throws VMCSException {
	    System.out.println("giveChange called from MainController");
	    storeCtrl.giveChange(idx, numOfCoins);
	}
	
	public StoreItem getStoreItem(int type, int idx) {
	    System.out.println("getStoreItem called from MainController");
	    return storeCtrl.getStoreItem(type, idx);
	}
	
	public void setPrice(int idx, int pr)  {
	    System.out.println("setPrice called from MainController");
	    storeCtrl.setPrice(idx, pr);
	}
	
	public int getTotalCash(){
	    System.out.println("getTotalCash called from MainController");
	    return storeCtrl.getTotalCash();
	}
	
	public int transferAll() {
	    System.out.println("transferAll called from MainController");
	    return storeCtrl.transferAll();
	}
	
	public void displayCoinStock() throws VMCSException {
	    System.out.println("displayCoinStock called from MainController");
	    machineryCtrl.displayCoinStock();
	}
	
	public boolean isDoorClosed() {
	    System.out.println("isDoorClosed called from MainController");
	    return machineryCtrl.isDoorClosed();
	}
	
	public void setDoorState(boolean state) {
	    System.out.println("setDoorState called from MainController");
	    machineryCtrl.setDoorState(state);
	}
	
	public void terminateTransaction(){
	    System.out.println("terminateTransaction called from MainController");
	    txCtrl.terminateTransaction();
	}
	
	public CustomerPanel getCustomerPanel(){
	    System.out.println("getCustomerPanel called from MainController");
	    return txCtrl.getCustomerPanel();
	}
	
	public void refreshCustomerPanel(){
	    System.out.println("refreshCustomerPanel called from MainController");
	    txCtrl.refreshCustomerPanel();
	}

	/**
	 * This method returns the SimulationController.
	 * @return the SimulationController.
	 */
	public SimulationController getSimulationController() {
		return simulatorCtrl;
	}

	/**
	 * This method returns the SimulatorControlPanel.
	 * @return the SimulatorControlPanel.
	 */
	public SimulatorControlPanel getSimulatorControlPanel() {
		return simulatorCtrl.getSimulatorControlPanel();
	}

	/**
	 * This method returns the StoreController.
	 * @return the StoreController.
	 */
	public StoreController getStoreController() {
		return storeCtrl;
	}

	/**
	 * This method returns the MachineryController&#46; 
	 * @return the MachineryController&#46;
	 */
	public MachineryController getMachineryController() {
		return machineryCtrl;
	}

	/**
	 * This method returns the MaintenanceController&#46;
	 * @return the MaintenanceController&#46;
	 */
	public MaintenanceController getMaintenanceController() {
		return maintenanceCtrl;
	}
	
	/**
	 * This method returns the TransactionController.
	 * @return the TransactionController.
	 */
	public TransactionController getTransactionController() {
		return txCtrl;
	}

	/**
	 * This method destroys all the object instances created for opening the vending
	 * machine&#46; It will instruct the SimulationController to close down (by closing
	 * down all the vending machine panels) and the TransactionController to close
	 * down&#46; It will also close down other control objects and the entity objects
	 * created for simulating the vending machine&#46;
	 */
	public void closeDown() {
		try {
			storeCtrl.closeDown();
		} catch (Exception e) {
			System.out.println("Error closing down the stores: " + e);
		}
		machineryCtrl.closeDown();
		maintenanceCtrl.closeDown();
		simulatorCtrl.closeDown();
	}
	
	public void refreshMachineryDisplay(){
	    System.out.println("refreshMachineryDisplay called from MainController");
	    machineryCtrl.refreshMachineryDisplay();
	}
	
	public void dispenseDrinkFromMachinery(int idx) throws VMCSException {
	    System.out.println("dispenseDrinkFromMachinery called from MainController");
	    machineryCtrl.dispenseDrink(idx);
	}
	
	public Store getStore(int type) {
	    System.out.println("getStore called from MainController");
	    return storeCtrl.getStore(type);
	}
	
	public int getStoreSize(int type) {
	    System.out.println("getStoreSize called from MainController");
	    return storeCtrl.getStoreSize(type);
	}
	
	public void storeCoinInMachinery(Coin c) throws VMCSException {
	    System.out.println("storeCoinInMachinery called from MainController");
	    machineryCtrl.storeCoin(c);
	}
	
	public void giveChangeFromMachinery(int idx, int numOfCoins) throws VMCSException {
	    System.out.println("giveChangeFromMachinery called from MainController");
	    machineryCtrl.giveChange(idx, numOfCoins);
	}
	
}//End of class MainController