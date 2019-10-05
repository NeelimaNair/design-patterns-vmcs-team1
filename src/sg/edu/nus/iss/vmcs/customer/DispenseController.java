/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.util.Observable;
import java.util.Observer;

import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.store.StoreObject;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This control object is for handling the dispense drink use case.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
/*
 * File amended to include the observer pattern
 */
public class DispenseController implements Observer {
    private TransactionController txCtrl;
    private MainController mainCtrl;  
    private CustomerPanel custPanel; 
    private int selection=0;
    private int selectedBrand; 
	
    /**
     * This constructor creates an instance of the object.
     * @param txCtrl the Transaction Controller
     */
    public DispenseController(TransactionController txCtrl){
    	this.txCtrl=txCtrl;
    }
    
    private void initialize() {
    	mainCtrl=txCtrl.getMainController();
		custPanel=txCtrl.getCustomerPanel();
		if(custPanel==null){
			return;
		}
    }
    
    /**
     * This method updates the whole Drink Selection Box with current names, stocks and prices.
     */
	public void updateDrinkPanel(){
		initialize();
		updateDrinkSelection(-1);
		int storeSize=mainCtrl.getStoreSize(Store.DRINK);
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=mainCtrl.getStoreItem(Store.DRINK,i);
			int quantity=storeItem.getQuantity();
			DrinksBrand drinksBrand=(DrinksBrand)storeItem.getContent();
			String name=drinksBrand.getName();
			int price=drinksBrand.getPrice();
			custPanel.getDrinkSelectionBox().update(i, quantity, price, name);
		}
	}
	
	/**
     * This method is used to display the latest stock and price information on the Drink Selection Box.
	 * @param index
	 */
	public void updateDrinkSelection(int index){
		this.selection=index;
	}
	
	/**
	 * This method will be used to activate or deactivate (as indicated through a parameter)
	 * the Drink Selection Box so that transactions can continue or will be disallowed.
	 * @param allow TRUE to activate, FALSE to deactivate the Drink Selection Box.
	 */
	public void allowSelection(boolean allow){
		DrinkSelectionBox drinkSelectionBox=custPanel.getDrinkSelectionBox();
		int storeSize=mainCtrl.getStoreSize(Store.DRINK);
		for(int i=0;i<storeSize;i++){
			drinkSelectionBox.setState(i,allow);
			StoreItem storeItem=mainCtrl.getStoreItem(Store.DRINK, i);
			int quantity=storeItem.getQuantity();
			if(quantity==0)
				drinkSelectionBox.setItemState(i,true);
		}
	}
	
	/**
	 * This method will be used to instruct the Can Collection Box to remove the 
	 * drinks can shape or drink brand name from being displayed.
	 */
	public void ResetCan(){
		selection=-1;
		custPanel.resetCan();
	}
	
	/**
	 * This method will be used to dispense a drink&#46;  It will:
	 * <br>
	 * 1- Instruct the Drinks Store to dispense the drink&#46; 
	 * <br>
	 * 2- In case of fault detection, it will send a "fault detected" response to the 
	 * Transaction Controller&#46;
	 * @param selectedBrand the selected brand&#46;
	 */
	public boolean dispenseDrink(int selectedBrand){
		try{
			this.selectedBrand = selectedBrand; 
			mainCtrl.getMachineryController().dispenseDrink(selectedBrand);
		}
		catch(VMCSException ex){
			txCtrl.terminateFault();
			return false;
		}
		return true;
	}

	/**
	 * This method will be used aid the dispense drink method&#46;  
	 * 1- It will instruct the
	 * Can Collection Box to display a can shape&#46;
	 * <br>
	 * 2- Instruct the Store Controller to update the Drink Store Display on the
	 * Machinery Simulator Panel&#46;
	 * @param Observable obs, Object quantity&#46;
	 */
	@Override
	public void update(Observable obs, Object quantity) {
		if (obs instanceof StoreItem) {
			System.out.println("State change reported by StoreItem in DispenseController.");
			StoreItem drinkStoreItem=mainCtrl.getStore(Store.DRINK).getStoreItem(selectedBrand);
			StoreObject storeObject=drinkStoreItem.getContent();
			DrinksBrand drinksBrand=(DrinksBrand)storeObject;
			String drinksName=drinksBrand.getName();
			int price=drinksBrand.getPrice();
			custPanel.setCan(drinksName);
			updateDrinkSelection(selectedBrand);
			custPanel.getDrinkSelectionBox().update(selectedBrand, (int)quantity, price, drinksName);			
		}
	}
}//End of class DispenseController