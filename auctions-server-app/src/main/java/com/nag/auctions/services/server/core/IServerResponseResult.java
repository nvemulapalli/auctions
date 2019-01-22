package com.nag.auctions.services.server.core;

import java.io.Serializable;

public interface IServerResponseResult extends Serializable {
	
	String getResultMessage();
	void setResultMessage(String resultMessage);
	
	int getResultCode();
	void setResultCode(int resultCode);

}
