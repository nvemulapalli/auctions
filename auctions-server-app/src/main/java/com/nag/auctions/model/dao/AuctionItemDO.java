package com.nag.auctions.model.dao;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.nag.auctions.model.AuctionItem;

@Document(collection="AuctionItems")
public class AuctionItemDO {
	
    @Id
    private String id;

    @NotBlank
    @Indexed(unique=true)
    @Field(value="AuctionItemId")
    private String auctionItemId;
    
    @Field(value="ReservePrice")
    private BigDecimal reservePrice; 
    
    @Field(value="ItemId")
	private String itemId;
    
    @Field(value="Description")
	private String description;

	public AuctionItemDO() {
    	
    }
	
	public AuctionItemDO(AuctionItem auctionItem) {
    	this.auctionItemId = auctionItem.getAuctionItemId();
        this.reservePrice = auctionItem.getReservePrice();
        this.itemId = auctionItem.getItem().getItemId();
        this.description = auctionItem.getItem().getDescription();
    }

    public AuctionItemDO(String auctionItemId, BigDecimal reservePrice, String itemId, String description) {
    	this.auctionItemId = auctionItemId;
        this.reservePrice = reservePrice;
        this.itemId = itemId;
        this.description = description;
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

	public BigDecimal getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(BigDecimal reservePrice) {
		this.reservePrice = reservePrice;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}    
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @Override
    public String toString() {
        return new StringBuilder("Auction Item { \n")
        			.append("Auction Item Id: ").append(getAuctionItemId()).append(", ")
        			.append("Reserve Price: ").append(getReservePrice()).append(", ")
        			.append("Item Id: ").append(getItemId()).append(", ")
        			.append("Item Description: ").append(getDescription()).append("\n }").toString();
    }
    
}
