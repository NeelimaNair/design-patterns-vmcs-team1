package sg.edu.nus.iss.vmcs.customer;

import javax.swing.JOptionPane;

public class ChangeDispensePayNowStrategy implements ChangeDispenserStrategy {

	@Override
	public boolean dispenseChange(TransactionController txCtrl, int changeRequired) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, 
                "YOUR CHANGE HAS BEEN TRANSFERRED VIA PAYNOW", 
                "From PayNowStrategy", 
                JOptionPane.WARNING_MESSAGE);
		return true;
	}

}
