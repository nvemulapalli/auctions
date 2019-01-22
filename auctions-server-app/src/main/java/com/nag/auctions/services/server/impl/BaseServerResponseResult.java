package com.nag.auctions.services.server.impl;

import com.nag.auctions.services.server.core.IServerResponseResult;

public class BaseServerResponseResult implements IServerResponseResult {
	
	private static final long serialVersionUID = 1L;
	
	private int resultCode = 0; //default
	private String resultMessage;

	@Override
	public String getResultMessage() {
		return resultMessage;
	}

	@Override
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	@Override
	public int getResultCode() {
		return resultCode;
	}

	@Override
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

}
