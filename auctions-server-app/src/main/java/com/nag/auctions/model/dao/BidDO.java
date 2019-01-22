package com.nag.auctions.model.dao;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.nag.auctions.model.Bid;

@Document(collection="Bids")
public class BidDO {
	
    @Id
    private String id;

    @Field(value="AuctionItemId")
    private String auctionItemId;
    @Field(value="MaxAutoBidAmount")
    private BigDecimal maxAutoBidAmount;
    @Field(value="BidderName")
	private String bidderName;
    @Field(value="BidId")
	private String bidId;

	public BidDO() {
    	
    }
	
	public BidDO(Bid bid) {
    	this.auctionItemId = bid.getAuctionItemId();
        this.maxAutoBidAmount = bid.getMaxAutoBidAmount();
        this.bidderName = bid.getBidderName();
        this.bidId = bid.getBidId();
    }

    public BidDO(String auctionItemId, BigDecimal maxAutoBidAmount, String bidderName, String bidId) {
    	this.auctionItemId = auctionItemId;
        this.maxAutoBidAmount = maxAutoBidAmount;
        this.bidderName = bidderName;
        this.bidId = bidId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return new StringBuilder("Bid { \n")
        			.append("Auction Item Id: ").append(getAuctionItemId()).append(", ")
        			.append("Max Bid Amount: ").append(getMaxAutoBidAmount()).append(", ")
        			.append("Bid Id: ").append(getBidId()).append(", ")
        			.append("Bidder: ").append(getBidderName()).append("\n }").toString();
    }
    
}
