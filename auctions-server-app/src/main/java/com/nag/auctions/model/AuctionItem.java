package com.nag.auctions.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.nag.auctions.model.dao.AuctionItemDO;

public class AuctionItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String auctionItemId;
    private BigDecimal reservePrice; 
    private BigDecimal currentBid;

	private Item item;
	
	public AuctionItem() {
		
	}

    public AuctionItem(String auctionItemId, BigDecimal reservePrice, BigDecimal currentBid, String itemId, String description) {
    	this.auctionItemId = auctionItemId;
        this.reservePrice = reservePrice;
        this.currentBid = currentBid;
        this.item = new Item(itemId, description);
    }
    
    public AuctionItem(AuctionItemDO auctionItem) {
    	this.auctionItemId = auctionItem.getAuctionItemId();
        this.reservePrice = auctionItem.getReservePrice();
        
        this.item = new Item(auctionItem.getItemId(), auctionItem.getDescription());
    }
    
    public String getAuctionItemId() {
		return auctionItemId;
	}

	public void setAuctionItemId(String auctionItemId) {
		this.auctionItemId = auctionItemId;
	}

	public BigDecimal getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(BigDecimal reservePrice) {
		this.reservePrice = reservePrice;
	}
	
	public BigDecimal getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(BigDecimal currentBid) {
		this.currentBid = currentBid;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
    
}
