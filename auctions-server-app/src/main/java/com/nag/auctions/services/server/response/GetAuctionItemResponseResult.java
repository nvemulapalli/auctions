package com.nag.auctions.services.server.response;

import com.nag.auctions.model.AuctionItem;
import com.nag.auctions.services.server.impl.BaseServerResponseResult;

public class GetAuctionItemResponseResult extends BaseServerResponseResult {

	private static final long serialVersionUID = 1L;
	
	private AuctionItem auctionItem;
	
	public GetAuctionItemResponseResult() {
	}

	public GetAuctionItemResponseResult(AuctionItem auctionItem) {
		this.auctionItem = auctionItem;
	}
	
	public AuctionItem getAuctionItem() {
		return auctionItem;
	}

	public void setAuctionItem(AuctionItem auctionItem) {
		this.auctionItem = auctionItem;
	}

}
