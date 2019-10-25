package sg.edu.nus.iss.vmcs.system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import sg.edu.nus.iss.vmcs.store.PropertyLoaderImpl;

public class XMLPropertyLoader extends PropertyLoaderImpl  {
	private Properties prop;
	private String fileName;

	/**
	 * This constructor creates an instance of the FilePropertyLoader object.
	 * @param fileName the filename of the property file.
	 */
	public XMLPropertyLoader(String fileName) {
		super(fileName);
		this.fileName = fileName;
		System.out.println("Entering concrete XML property loader class");
	}

	/**
	 * This method reads the properties file into a hash table.
	 * @throws IOException if fail to load properties from properties file.
	 */
	public void load(Properties prop) throws IOException {
		FileInputStream stream = new FileInputStream(fileName);
		prop.loadFromXML(stream);
		stream.close();
	}

	/**
	 * This method writes the properties from the hash table to the file.
	 * @throws IOException if fail to store properties to properties file.
	 */
	public void save(Properties prop) throws IOException {
		FileOutputStream stream = new FileOutputStream(fileName);
		prop.storeToXML(stream, "");
		stream.close();
	}
}//End of class XMLPropertyLoader
