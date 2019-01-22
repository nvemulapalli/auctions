package com.nag.auctions.services.core;

import com.nag.auctions.services.common.IService;
import com.nag.auctions.services.server.core.IServerRequestParam;
import com.nag.auctions.services.server.core.IServerResponse;

public interface IAuctionsService extends IService {
	
	IServerResponse getAllAuctionItems();
	IServerResponse getAuctionItem(String auctionItemId);
	
	IServerResponse createAuctionItem(IServerRequestParam requestParam);

}
