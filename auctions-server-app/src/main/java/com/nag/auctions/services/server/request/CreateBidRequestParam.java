package com.nag.auctions.services.server.request;

import com.nag.auctions.model.Bid;
import com.nag.auctions.services.server.core.IServerRequestParam;

public class CreateBidRequestParam implements IServerRequestParam {

	private static final long serialVersionUID = 1L;
	
	private Bid bid;

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

}
