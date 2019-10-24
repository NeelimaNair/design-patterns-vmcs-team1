package sg.edu.nus.iss.vmcs.customer;

public abstract class TerminateTemplate {
    TransactionController txCtrl;
	
	public final void terminate(boolean allow) {
		allowSelection(allow);
		refreshMachineryDisplay();
		deleteObservers();
		customTerminate();
	};
	
	public TerminateTemplate() {
		System.out.println("Entering Terminate template method abstract class");
		txCtrl = TransactionController.getInstance();
	}
	
	private void allowSelection(boolean allow) {
		txCtrl.getDispenseController().allowSelection(allow);
	}
	
	private void refreshMachineryDisplay() {
		txCtrl.refreshMachineryDisplay();		
	}
	
	private void deleteObservers() {
		txCtrl.deleteObservers();		
	}
	
	abstract protected void customTerminate();
}
