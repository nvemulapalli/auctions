package com.nag.auctions.services.server.response;

import com.nag.auctions.services.server.impl.BaseServerResponseResult;

public class CreateAuctionItemResponseResult extends BaseServerResponseResult {

	private static final long serialVersionUID = 1L;
	
	private String auctionItemId;

	public CreateAuctionItemResponseResult(String auctionItemId) {
		this.auctionItemId = auctionItemId;
	}
	
	public String getAuctionItemId() {
		return auctionItemId;
	}

	public void setAuctionItemId(String auctionItemId) {
		this.auctionItemId = auctionItemId;
	}

}
