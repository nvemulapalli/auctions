package com.nag.auctions.services.server.core;

import java.io.Serializable;

public interface IServerRequest extends Serializable {
	
	void setRequestName(String requestName);
	String getRequestName();
	
}
