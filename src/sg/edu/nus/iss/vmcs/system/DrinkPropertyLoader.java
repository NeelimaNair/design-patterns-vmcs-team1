/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.system;


import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.*;

/**
 * This control object manages the initialization of the DrinksStore
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class DrinkPropertyLoader extends PropertyLoader {

	private static final String NAME_LABEL     = "Name";
	private static final String PRICE_LABEL    = "Price";
	private static final String QUANTITY_LABEL = "Quantity";

	/**
	 * This constructor creates an instance of the DrinkPropertyLoader object.
	 * @param filen the file name of the drink property file.
	 */
	public DrinkPropertyLoader(String filen) {
		super(filen);
		System.out.println("Entering concrete drinks property loader class");
	}

	/**
	 * This method reads the data from the hash table and creates a DrinkStoreItem.
	 * @param index the index of the store item.
	 * @return StoreItem the store item of the given index.
	 */
	public StoreItem getItem(int index) {
		int idx = index + 1;
		DrinksBrand brand = new DrinksBrand();

		String name = new String(NAME_LABEL + idx);
		String value = getValue(name);
		brand.setName(value);

		name = new String(PRICE_LABEL + idx);
		value = getValue(name);
		brand.setPrice(Integer.parseInt(value));

		name = new String(QUANTITY_LABEL + idx);
		value = getValue(name);
		int qty = Integer.parseInt(value);

		DrinksStoreItem item = new DrinksStoreItem(brand, qty);
		return item;

	}

	/**
	 * This method updates the hash table with the data from the DrinkStoreItem.
	 * @param index the index of the store item.
	 * @param drinksItem the store item of the given index.
	 */
	public void setItem(int index, StoreItem drinksItem) {
		int idx = index + 1;

		DrinksStoreItem item = (DrinksStoreItem) drinksItem;
		DrinksBrand brand = (DrinksBrand) item.getContent();
		String itn = new String(NAME_LABEL + idx);
		setValue(itn, brand.getName());

		itn = new String(PRICE_LABEL + idx);
		setValue(itn, String.valueOf(brand.getPrice()));

		itn = new String(QUANTITY_LABEL + idx);
		setValue(itn, String.valueOf(item.getQuantity()));

	}

	@Override
	public void setPropertyLoaderType(int propertyLoader) {
		switch (propertyLoader) {
			case 0:
				setPropertyLoaderImpl(new FilePropertyLoader(fileName));
				break;
			case 1:
				setPropertyLoaderImpl(new XMLPropertyLoader(fileName));
				break;
		}		
	}

	@Override
	public void load() throws IOException {
		// TODO Check if prop can be generic to support RDBMS
		getPropertyLoaderImpl().load(prop);
	}

	@Override
	public void save() throws IOException {
		// TODO Check if prop can be generic to support RDBMS
		getPropertyLoaderImpl().save(prop);
	}
}//End of class DrinkPropertyLoader