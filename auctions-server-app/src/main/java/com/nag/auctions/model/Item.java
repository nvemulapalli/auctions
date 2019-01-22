package com.nag.auctions.model;

import java.io.Serializable;

public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String itemId;
	private String description;
	
	public Item() {
		
	}

	public Item(String itemId, String description) {
		this.itemId = itemId;
		this.description = description;
	}	
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
