package com.nag.auctions.services.server.impl;

import com.nag.auctions.services.server.core.IServerResponse;
import com.nag.auctions.services.server.core.IServerResponseError;
import com.nag.auctions.services.server.core.IServerResponseResult;

public class ServerResponse implements IServerResponse {

	private static final long serialVersionUID = 1L;
	
	private IServerResponseResult result;
	private IServerResponseError error;

	@Override
	public IServerResponseResult getResult() {
		return result;
	}

	@Override
	public void setResult(IServerResponseResult result) {
		this.result = result;
	}

	@Override
	public IServerResponseError getError() {
		return error;
	}

	@Override
	public void setError(IServerResponseError error) {
		this.error = error;
	}

}
