package com.nag.auctions.services.core;

import com.nag.auctions.services.common.IService;
import com.nag.auctions.services.server.core.IServerRequestParam;
import com.nag.auctions.services.server.core.IServerResponse;

public interface IBidsService extends IService {
	
	IServerResponse createBid(IServerRequestParam requestParam);

	IServerResponse getBidAuditLog(String auctionItemId);

}
