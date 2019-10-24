package sg.edu.nus.iss.vmcs.customer;

public class TerminateTransactionFault extends TerminateTemplate {

	@Override
	protected void customTerminate() {
		System.out.println("Entering Terminate transaction fault template method concrete method");
		txCtrl.getCoinReceiver().refundCash();
	}
}
