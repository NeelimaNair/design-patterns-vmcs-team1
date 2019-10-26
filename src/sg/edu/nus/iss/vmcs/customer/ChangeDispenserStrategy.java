/**
 * 
 */
package sg.edu.nus.iss.vmcs.customer;

/**
 * @author liqui
 *
 */
public interface ChangeDispenserStrategy {

	public boolean dispenseChange(TransactionController txCtrl,int changeRequired);


	
}

