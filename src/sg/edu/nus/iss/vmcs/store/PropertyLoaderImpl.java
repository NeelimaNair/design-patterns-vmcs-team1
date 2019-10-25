package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;
import java.util.Properties;

public abstract class PropertyLoaderImpl {
	private String fileName;
	
	public PropertyLoaderImpl (String filen) {
		System.out.println("Entering abstract property impl loader class");
		this.fileName = filen;
	}
	/**
	 * This method reads the properties file into a hash table.
	 * @throws IOException if fail to read properties from properties file.
	 */
	public abstract void load(Properties prop) throws IOException;
	
	/**
	 * This method writes the properties from the hash table to the file.
	 * @throws IOException if fail to save properties to properties file.
	 */
	public abstract void save(Properties prop) throws IOException; 
	
}
