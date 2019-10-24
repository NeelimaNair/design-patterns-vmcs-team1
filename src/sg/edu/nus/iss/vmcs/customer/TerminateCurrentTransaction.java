package sg.edu.nus.iss.vmcs.customer;

public class TerminateCurrentTransaction extends TerminateTemplate{

	@Override
	protected void customTerminate() {
		System.out.println("Entering Terminate current transaction template method concrete class");
		txCtrl.getCoinReceiver().stopReceive();
		txCtrl.getCoinReceiver().refundCash();
		if(txCtrl.getCustomerPanel()!=null){
			txCtrl.getCustomerPanel().setTerminateButtonActive(false);
		}		
	}
}
