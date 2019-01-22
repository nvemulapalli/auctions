package com.nag.auctions.services.server.core;

import java.io.Serializable;

public interface IServerResponse extends Serializable {
	
	IServerResponseResult getResult();
	void setResult(IServerResponseResult result);
	
	IServerResponseError getError();
	void setError(IServerResponseError error);

}
