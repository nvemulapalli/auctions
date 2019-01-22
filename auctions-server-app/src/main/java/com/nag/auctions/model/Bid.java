package com.nag.auctions.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.nag.auctions.model.dao.BidDO;

public class Bid implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String auctionItemId;
    private BigDecimal maxAutoBidAmount;
	private String bidderName;
	private String bidId;
	
	public Bid() {
		
	}
	
	public Bid(BidDO bidDO) {
    	this.auctionItemId = bidDO.getAuctionItemId();
        this.maxAutoBidAmount = bidDO.getMaxAutoBidAmount();
        this.bidderName = bidDO.getBidderName();
        this.bidId = bidDO.getBidId();
    }

	public Bid(String auctionItemId, BigDecimal maxAutoBidAmount, String bidderName, String bidId) {
    	this.auctionItemId = auctionItemId;
        this.maxAutoBidAmount = maxAutoBidAmount;
        this.bidderName = bidderName;
        this.bidId = bidId;
    }
    
    public String getAuctionItemId() {
		return auctionItemId;
	}

	public void setAuctionItemId(String auctionItemId) {
		this.auctionItemId = auctionItemId;
	}
	
	public BigDecimal getMaxAutoBidAmount() {
		return maxAutoBidAmount;
	}

	public void setMaxAutoBidAmount(BigDecimal maxAutoBidAmount) {
		this.maxAutoBidAmount = maxAutoBidAmount;
	}

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	
    public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}
    
}
