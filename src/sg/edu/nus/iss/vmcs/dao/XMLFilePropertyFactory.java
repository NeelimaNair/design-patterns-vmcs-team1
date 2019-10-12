package sg.edu.nus.iss.vmcs.dao;
import sg.edu.nus.iss.vmcs.store.PropertyLoader;
import sg.edu.nus.iss.vmcs.system.CashPropertyLoader;
import sg.edu.nus.iss.vmcs.system.CashXMLPropertyLoader;
import sg.edu.nus.iss.vmcs.system.DrinkPropertyLoader;
import sg.edu.nus.iss.vmcs.system.DrinkXMLPropertyLoader;
import sg.edu.nus.iss.vmcs.system.Environment;

public class XMLFilePropertyFactory extends PropertyLoaderFactory{

	@Override
	public PropertyLoader getCashProperty() {
		return new CashXMLPropertyLoader(Environment.getCashXMLPropFile());
	}

	@Override
	public PropertyLoader getDrinksProperty() {
		return new DrinkXMLPropertyLoader(Environment.getDrinkXMLPropFile());
	}
	
	
}