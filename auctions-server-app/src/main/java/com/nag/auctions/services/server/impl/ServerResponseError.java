package com.nag.auctions.services.server.impl;

import com.nag.auctions.services.server.core.IServerResponseError;

public class ServerResponseError implements IServerResponseError {

	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
