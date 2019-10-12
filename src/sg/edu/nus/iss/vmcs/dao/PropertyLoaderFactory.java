package sg.edu.nus.iss.vmcs.dao;
import sg.edu.nus.iss.vmcs.store.PropertyLoader;

// Abstract class DAO Factory, in vmcs DAO == Property
public abstract class PropertyLoaderFactory {

  // List of DAO types supported by the factory
  public static final int TEXTFILE = 1;
  public static final int XMLFILE = 2;
  
  // There will be a method for each Property that can be created. 
  //The concrete factories will have to implement these methods.
  public abstract PropertyLoader getCashProperty();
  public abstract PropertyLoader getDrinksProperty();
  
  public static PropertyLoaderFactory getPropertyFactory(
      int whichFactory) {
  
    switch (whichFactory) {
      case TEXTFILE: 
          return new TextFilePropertyFactory();
      case XMLFILE: 
          return new XMLFilePropertyFactory();      
      default : 
          return null;
    }
  }
}