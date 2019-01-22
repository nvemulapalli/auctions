package com.nag.auctions.services.server.response;

import java.util.List;

import com.nag.auctions.model.Bid;
import com.nag.auctions.services.server.impl.BaseServerResponseResult;

public class GetBidLogResponseResult extends BaseServerResponseResult {

	private static final long serialVersionUID = 1L;
	
	private List<Bid> allBids;

	public GetBidLogResponseResult() {
	}
	
	public GetBidLogResponseResult(List<Bid> allBids) {
		this.allBids = allBids;
	}
	
	public List<Bid> getAllBids() {
		return allBids;
	}

	public void setAllBids(List<Bid> allBids) {
		this.allBids = allBids;
	}

}
