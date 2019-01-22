package com.nag.auctions.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nag.auctions.model.AuctionItem;
import com.nag.auctions.model.dao.AuctionItemDO;
import com.nag.auctions.model.dao.BidDO;
import com.nag.auctions.model.utils.Utils;
import com.nag.auctions.mongo.repository.core.AuctionItemRepository;
import com.nag.auctions.mongo.repository.core.BidRepository;
import com.nag.auctions.services.core.IAuctionsService;
import com.nag.auctions.services.server.core.IServerRequestParam;
import com.nag.auctions.services.server.core.IServerResponse;
import com.nag.auctions.services.server.request.CreateAuctionItemRequestParam;
import com.nag.auctions.services.server.response.CreateAuctionItemResponseResult;
import com.nag.auctions.services.server.response.GetAllAuctionItemsResponseResult;
import com.nag.auctions.services.server.response.GetAuctionItemResponseResult;

@Service("auctionsService")
public class AuctionsServiceImpl implements IAuctionsService {
	
	@Autowired
    AuctionItemRepository auctionItemsRepo;
	
	@Autowired
    BidRepository bidsRepo;

	@Override
	public IServerResponse getAllAuctionItems() {
		List<AuctionItemDO> auctionItemDOs = auctionItemsRepo.findAll();
		
		GetAllAuctionItemsResponseResult responseResult = new GetAllAuctionItemsResponseResult();
		if(auctionItemDOs == null || auctionItemDOs.isEmpty()) {
			responseResult.setResultMessage("There are no auction items at this point.");
			return Utils.generateResponse(responseResult);
		}
		
		List<AuctionItem> newAuctionItems = new ArrayList<AuctionItem>();
		for(AuctionItemDO auctionItem: auctionItemDOs) {
			AuctionItem newAuctionItem = new AuctionItem(auctionItem);

			populateAuctionItem(auctionItem, newAuctionItem);
			newAuctionItems.add(newAuctionItem);
		}		
		
		responseResult.setAllAuctionItems(newAuctionItems);
		return Utils.generateResponse(responseResult);
	}

	@Override
	public IServerResponse getAuctionItem(String auctionItemId) {
		AuctionItemDO auctionItem = auctionItemsRepo.findByAuctionItemId(auctionItemId);
		
		GetAuctionItemResponseResult responseResult = new GetAuctionItemResponseResult();
		if(auctionItem != null) {
			AuctionItem newAuctionItem = new AuctionItem(auctionItem);
			populateAuctionItem(auctionItem, newAuctionItem);
			
			responseResult.setAuctionItem(newAuctionItem);
		} else {
			responseResult.setResultMessage("No auction items associated with the id: " + auctionItemId);
		}
		
		return Utils.generateResponse(responseResult);
	}

	@Override
	public IServerResponse createAuctionItem(IServerRequestParam requestParam) {
		CreateAuctionItemRequestParam params = (CreateAuctionItemRequestParam)requestParam;
		AuctionItem auctionItem = params.getAuctionItem();
		
		AuctionItemDO auctionItemDO = new AuctionItemDO(auctionItem);
		String randomId = Utils.getGeneratedId();
		auctionItemDO.setAuctionItemId(randomId);
		auctionItemDO.setId(randomId);
		auctionItemsRepo.insert(auctionItemDO);
		
		CreateAuctionItemResponseResult responseResult = new CreateAuctionItemResponseResult(auctionItemDO.getAuctionItemId());
		return Utils.generateResponse(responseResult);
	}
	
	private void populateAuctionItem(AuctionItemDO oldAuctionItem, AuctionItem newAuctionItem) {
		BigDecimal maxBidAmount = new BigDecimal("0"); //default
		List<BidDO> bidDOs = bidsRepo.findByAuctionItemId(oldAuctionItem.getAuctionItemId());
		if(bidDOs != null && !bidDOs.isEmpty()) {
			for(BidDO bidDO: bidDOs) {
				BigDecimal newMaxBidAmount = bidDO.getMaxAutoBidAmount();
				if(newMaxBidAmount.compareTo(maxBidAmount)==1) {
					maxBidAmount = newMaxBidAmount;
				}
			}
		}
		newAuctionItem.setCurrentBid(maxBidAmount);
	}

}
