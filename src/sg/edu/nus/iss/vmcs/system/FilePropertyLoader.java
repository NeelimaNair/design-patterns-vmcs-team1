/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.system;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.util.*;
import java.io.*;

import sg.edu.nus.iss.vmcs.store.*;

/**
 * This control object implements the interface, PropertyLoader, to provide the generic functionality
 * required to initialize the stores.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class FilePropertyLoader extends PropertyLoaderImpl {
	private Properties prop;
	private String fileName;

	/**
	 * This constructor creates an instance of the FilePropertyLoader object.
	 * @param fileName the filename of the property file.
	 */
	public FilePropertyLoader(String fileName) {
		super(fileName);
		this.fileName = fileName;
		System.out.println("Entering concrete file property loader class");
	}

	/**
	 * This method reads the properties file into a hash table.
	 * @throws IOException if fail to load properties from properties file.
	 */
	public void load(Properties prop) throws IOException {
		FileInputStream stream = new FileInputStream(fileName);
		prop.load(stream);
		stream.close();
	}

	/**
	 * This method writes the properties from the hash table to the file.
	 * @throws IOException if fail to store properties to properties file.
	 */
	public void save(Properties prop) throws IOException {
		FileOutputStream stream = new FileOutputStream(fileName);
		prop.store(stream, "");
		stream.close();
	}
}//End of class FilePropertyLoader