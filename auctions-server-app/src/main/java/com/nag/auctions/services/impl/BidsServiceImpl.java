package com.nag.auctions.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nag.auctions.model.Bid;
import com.nag.auctions.model.dao.AuctionItemDO;
import com.nag.auctions.model.dao.BidDO;
import com.nag.auctions.model.utils.Utils;
import com.nag.auctions.mongo.repository.core.AuctionItemRepository;
import com.nag.auctions.mongo.repository.core.BidRepository;
import com.nag.auctions.services.core.IBidsService;
import com.nag.auctions.services.server.core.IServerRequestParam;
import com.nag.auctions.services.server.core.IServerResponse;
import com.nag.auctions.services.server.request.CreateBidRequestParam;
import com.nag.auctions.services.server.response.CreateBidResponseResult;
import com.nag.auctions.services.server.response.GetBidLogResponseResult;

@Service("bidsService")
public class BidsServiceImpl implements IBidsService {
	
	@Autowired
    BidRepository bidsRepo;
	
	@Autowired
    AuctionItemRepository auctionItemsRepo;
	
	@Override
	public IServerResponse createBid(IServerRequestParam requestParam) {
		CreateBidRequestParam params = (CreateBidRequestParam) requestParam;
		Bid bid = params.getBid();
		
		String auctionItemId = bid.getAuctionItemId();
		AuctionItemDO auctionItem = auctionItemsRepo.findByAuctionItemId(auctionItemId);
		if(auctionItem == null) {
			return Utils.generateErrorResponse("There is no auction item associated with the id: " + auctionItemId);
		}

		CreateBidResponseResult responseResult = new CreateBidResponseResult();
		
		BidDO currentBid = null;
		List<BidDO> currentBidDOs = bidsRepo.findByAuctionItemId(auctionItemId);
		if(currentBidDOs != null && !currentBidDOs.isEmpty()) {
			for(BidDO bidDO: currentBidDOs) {
				BigDecimal newMaxBidAmount = bidDO.getMaxAutoBidAmount();
				if(currentBid == null || newMaxBidAmount.compareTo(currentBid.getMaxAutoBidAmount())==1) {
					currentBid = bidDO;
				}
			}
		}
		
		BidDO newBid = new BidDO(bid);
		
		BigDecimal reservePrice = auctionItem.getReservePrice();
		BigDecimal maxBidAmount = bid.getMaxAutoBidAmount();
		
		if(maxBidAmount.compareTo(reservePrice) <= 0) {
			//reserve price has not been met.
			if(currentBid != null) {
				//currentBid exists. set the bid amount to maximum of current bid and max auto bid amount.
				newBid.setMaxAutoBidAmount(currentBid.getMaxAutoBidAmount().compareTo(maxBidAmount)==1?currentBid.getMaxAutoBidAmount():maxBidAmount);
			}
			
			responseResult.setResultMessage("The bid amount didn't meet the reserve price.");
		} else if(currentBid != null && currentBid.getMaxAutoBidAmount().compareTo(maxBidAmount)>=0) {
			//current bid is more than the max bid amount. Notify the bidder has been outbid.
			return Utils.generateErrorResponse("The current bid amount is equal or higher than the bid amount requested.");
		}
		
		String randomId = Utils.getGeneratedId();
		newBid.setBidId(randomId);
		newBid.setId(randomId);
		responseResult.setBidId(randomId);
		
		bidsRepo.insert(newBid);
		
		return Utils.generateResponse(responseResult);
	}

	@Override
	public IServerResponse getBidAuditLog(String auctionItemId) {
		List<BidDO> bidDOs = bidsRepo.findByAuctionItemId(auctionItemId);
		
		GetBidLogResponseResult responseResult = new GetBidLogResponseResult();
		if(bidDOs == null || bidDOs.isEmpty()) {
			responseResult.setResultMessage("There are no bids placed for the Auction Item: " + auctionItemId);
			return Utils.generateResponse(responseResult);
		}
		
		List<Bid> allBids = new ArrayList<Bid>();
		for(BidDO bidDO: bidDOs) {
			Bid newBid = new Bid(bidDO);
			allBids.add(newBid);
		}
		
		responseResult.setAllBids(allBids);
		return Utils.generateResponse(responseResult);
	}

}
