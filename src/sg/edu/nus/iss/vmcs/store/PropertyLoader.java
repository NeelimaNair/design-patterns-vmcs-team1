/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

import java.io.*;
import java.util.Properties;

/**
 * This interface provides the generic functionality required to initialize the stores.
 * 
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreController
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public abstract class PropertyLoader {
	public static final String PROP_NUM_ITEMS = "NumOfItems";
	protected Properties prop;	
	protected String fileName;
	
	public PropertyLoader(String filen) {
		System.out.println("Entering abstract property loader class");
		prop = new Properties(System.getProperties());
		this.fileName = filen;
	}
	
	//The bridge to implementation hierarchy
	private PropertyLoaderImpl propertyLoaderImpl = null;

	protected PropertyLoaderImpl getPropertyLoaderImpl() {
		return propertyLoaderImpl;
	}
	
	protected void setPropertyLoaderImpl (PropertyLoaderImpl propertyLoaderImpl) {
		this.propertyLoaderImpl = propertyLoaderImpl;
	}
	
	protected String getFileName() {
		return fileName;
	}
	
	protected void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public abstract void setPropertyLoaderType(int propertyLoader);

	/**
	 * This method reads the data from the hash table and creates a StoreItem.
	 * @param index the index of the store item.
	 * @return StoreItem the store item of the given index.
	 */
	public abstract StoreItem getItem (int index);

	/**
	 * This method updates the hash table with the data from the StoreItem.
	 * @param index the index of the item.
	 * @param item the item to be saved.
	 */
	public abstract void setItem (int index, StoreItem item);
	
	/**
	 * This method returns the number of items (either CashStoreItem or DrinkStoreItem)
	 * stored in the hash table.
	 * @return the number of items.
	 */
	public int getNumOfItems() {
		String nm = prop.getProperty(PROP_NUM_ITEMS);
		int nmi;
		nmi = Integer.parseInt(nm);
		return nmi;
	}

	/**
	 * This method sets the number of items (either CashStoreItem or DrinkStoreItem)
	 * stored in the hash table.
	 * @param numItems the number of items.
	 */
	public void setNumOfItems(int numItems) {
		prop.setProperty(PROP_NUM_ITEMS, String.valueOf(numItems));
	}
	
	/**
	 * This method retrieve the value from the hash table.
	 * @param key the key.
	 * @return the value of the given key.
	 */
	public String getValue(String key) {
		return prop.getProperty(key);
	}

	/**
	 * The method sets a value into the hash table.
	 * @param key the key.
	 * @param value the value.
	 */
	public void setValue(String key, String value) {
		prop.setProperty(key, value);
	}
	
	public abstract void load() throws IOException;
	
	public abstract void save() throws IOException;
}//End of interface PropertyLoader
