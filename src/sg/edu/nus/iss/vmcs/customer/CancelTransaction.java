package sg.edu.nus.iss.vmcs.customer;

public class CancelTransaction extends TerminateTemplate {

	@Override
	protected void customTerminate() {
		System.out.println("Entering Cancel transaction template method concrete class");
		txCtrl.getCoinReceiver().stopReceive();
		txCtrl.getCoinReceiver().refundCash();
	}
}
