package com.nag.auctions.services.server.request;

import com.nag.auctions.model.AuctionItem;
import com.nag.auctions.services.server.core.IServerRequestParam;

public class CreateAuctionItemRequestParam implements IServerRequestParam {

	private static final long serialVersionUID = 1L;
	
	private AuctionItem auctionItem;

	public AuctionItem getAuctionItem() {
		return auctionItem;
	}

	public void setAuctionItem(AuctionItem auctionItem) {
		this.auctionItem = auctionItem;
	}

}
