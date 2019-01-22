package com.nag.auctions.services.server.response;

import java.util.List;

import com.nag.auctions.model.AuctionItem;
import com.nag.auctions.services.server.impl.BaseServerResponseResult;

public class GetAllAuctionItemsResponseResult extends BaseServerResponseResult {

	private static final long serialVersionUID = 1L;
	
	private List<AuctionItem> allAuctionItems;
	
	public GetAllAuctionItemsResponseResult() {
	}
	
	public GetAllAuctionItemsResponseResult(List<AuctionItem> auctionItems) {
		this.allAuctionItems = auctionItems;
	}

	public List<AuctionItem> getAllAuctionItems() {
		return allAuctionItems;
	}

	public void setAllAuctionItems(List<AuctionItem> allAuctionItems) {
		this.allAuctionItems = allAuctionItems;
	}

}
