package sg.edu.nus.iss.vmcs.customer;

public interface TerminateFactory {
	public static enum TerminateType {
		CANCEL_TRANSACTION,
		TERMINATE_FAULT,
		TERMINATE_TRANSACTION
	}
	public static TerminateTemplate create(TerminateType type) {
		System.out.println("Entering Terminate factory method");
		
		switch (type) {
			case TERMINATE_FAULT: 
				return (new TerminateCurrentTransaction());
			case CANCEL_TRANSACTION:
				return (new CancelTransaction());
			case TERMINATE_TRANSACTION:
				return (new TerminateTransactionFault());
		}
		return null;
	}
}
