package com.nag.auctions.services.server.request;

import com.nag.auctions.services.server.impl.BaseServerRequest;

public class CreateBidRequest extends BaseServerRequest {

	private static final long serialVersionUID = 1L;
	
	private CreateBidRequestParam requestParam;

	public CreateBidRequestParam getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(CreateBidRequestParam requestParam) {
		this.requestParam = requestParam;
	}

}
