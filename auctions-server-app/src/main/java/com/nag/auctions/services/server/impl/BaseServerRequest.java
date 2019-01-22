package com.nag.auctions.services.server.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nag.auctions.services.server.core.IServerRequest;

@Component
@Scope("request")
public abstract class BaseServerRequest implements IServerRequest {

	private static final long serialVersionUID = 1L;
	
	private String requestName;

	@Override
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	@Override
	public String getRequestName() {
		return requestName;
	}

}
