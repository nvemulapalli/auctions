package com.nag.auctions.services.server.core;

import java.io.Serializable;

public interface IServerResponseError extends Serializable {

	String getErrorMessage();
	void setErrorMessage(String errorMessage);

}
