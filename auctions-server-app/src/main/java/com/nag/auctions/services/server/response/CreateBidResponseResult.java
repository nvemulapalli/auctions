package com.nag.auctions.services.server.response;

import com.nag.auctions.services.server.impl.BaseServerResponseResult;

public class CreateBidResponseResult extends BaseServerResponseResult {

	private static final long serialVersionUID = 1L;
	
	private String bidId;
	
	public CreateBidResponseResult() {
	}

	public CreateBidResponseResult(String bidId) {
		this.bidId = bidId;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

}
