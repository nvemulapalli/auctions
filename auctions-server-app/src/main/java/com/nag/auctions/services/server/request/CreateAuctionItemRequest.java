package com.nag.auctions.services.server.request;

import com.nag.auctions.services.server.impl.BaseServerRequest;

public class CreateAuctionItemRequest extends BaseServerRequest {

	private static final long serialVersionUID = 1L;
	
	private CreateAuctionItemRequestParam requestParam;

	public CreateAuctionItemRequestParam getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(CreateAuctionItemRequestParam requestParam) {
		this.requestParam = requestParam;
	}

}
