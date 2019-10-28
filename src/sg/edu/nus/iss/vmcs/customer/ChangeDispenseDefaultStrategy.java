/**
 * 
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * @author liqui
 *
 */
public class ChangeDispenseDefaultStrategy implements ChangeDispenserStrategy {

	public ChangeDispenseDefaultStrategy() {
		
	}
	
	
	@Override
	public boolean dispenseChange(TransactionController txCtrl,int changeRequired) {
		// TODO Auto-generated method stub

		if(changeRequired==0)
			return true;
		try{
			int changeBal=changeRequired;
			MainController mainCtrl=txCtrl.getMainController();
			int cashStoreSize=mainCtrl.getStoreSize(Store.CASH); 
			for(int i=cashStoreSize-1;i>=0;i--){
				StoreItem cashStoreItem=mainCtrl.getStore(Store.CASH).getStoreItem(i);
				int quantity=cashStoreItem.getQuantity();
				Coin coin=(Coin)cashStoreItem.getContent();
				int value=coin.getValue();
				int quantityRequired=0;
				while(changeBal>0&&changeBal>=value&&quantity>0){
					changeBal-=value;
					quantityRequired++;
					quantity--;
				}
				txCtrl.getMainController().giveChangeFromMachinery(i,quantityRequired);
			}
			txCtrl.getCustomerPanel().setChange(changeRequired-changeBal);
			if(changeBal>0)
				txCtrl.getCustomerPanel().displayChangeStatus(true);
		}
		catch(VMCSException ex){
			txCtrl.terminateFault();
			return false;
		}
		return true;
	}

}
