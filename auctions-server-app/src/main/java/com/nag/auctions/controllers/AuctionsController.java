package com.nag.auctions.controllers;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nag.auctions.model.utils.Utils;
import com.nag.auctions.services.core.IAuctionsService;
import com.nag.auctions.services.core.IBidsService;
import com.nag.auctions.services.server.core.IServerResponse;
import com.nag.auctions.services.server.request.CreateAuctionItemRequest;
import com.nag.auctions.services.server.request.CreateBidRequest;

@RestController
@RequestMapping("/auctions")
@CrossOrigin("*")
public class AuctionsController {

    @Autowired
    IAuctionsService auctionsService;
    
    @Autowired 
    IBidsService bidsService;

    @GetMapping("/auctionItems")
    public @ResponseBody IServerResponse getAllAuctionItems() {
        return auctionsService.getAllAuctionItems();
    }

    @PostMapping("/auctionItems")
    public @ResponseBody IServerResponse createAuctionItem(@Valid @RequestBody CreateAuctionItemRequest request) {

    	BigDecimal reservePrice = request.getRequestParam().getAuctionItem().getReservePrice();
    	if(reservePrice == null || reservePrice.compareTo(new BigDecimal(0)) != 1) {
    		return Utils.generateErrorResponse("Reserve Price must be greater than 0.");
    	}
    	
    	String itemId = request.getRequestParam().getAuctionItem().getItem().getItemId();
    	if(itemId == null || itemId.trim().isEmpty()) {
    		return Utils.generateErrorResponse("Item Id is required.");
    	}
    	
        return auctionsService.createAuctionItem(request.getRequestParam());
    }

    @GetMapping(value="/auctionItems/{auctionItemId}")
    public @ResponseBody IServerResponse getAuctionItem(@PathVariable("auctionItemId") String auctionItemId) {
    	
    	if(auctionItemId == null || auctionItemId.trim().isEmpty()) {
    		return Utils.generateErrorResponse("Auction Item Id is required.");
    	}
    	
        return auctionsService.getAuctionItem(auctionItemId);
    }
    
    @PostMapping("/bids")
    public @ResponseBody IServerResponse createBid(@Valid @RequestBody CreateBidRequest request) {
    	
    	String auctionItemId = request.getRequestParam().getBid().getAuctionItemId();
    	if(auctionItemId == null || auctionItemId.trim().isEmpty()) {
    		return Utils.generateErrorResponse("Auction Item Id is required.");
    	}
    	
    	BigDecimal maxBidAmount = request.getRequestParam().getBid().getMaxAutoBidAmount();
    	if(maxBidAmount == null || maxBidAmount.compareTo(new BigDecimal(0)) != 1) {
    		return Utils.generateErrorResponse("Maximum bid amount must be greater than 0.");
    	}
    	
    	String bidderName = request.getRequestParam().getBid().getBidderName();
    	if(bidderName == null || bidderName.trim().isEmpty()) {
    		return Utils.generateErrorResponse("Bidder Name is required.");
    	}
    	
        return bidsService.createBid(request.getRequestParam());
    }
    
    @GetMapping("/bids/{auctionItemId}")
    public @ResponseBody IServerResponse getBidAuditLog(@PathVariable("auctionItemId") String auctionItemId) {
    	
    	if(auctionItemId == null || auctionItemId.trim().isEmpty()) {
    		return Utils.generateErrorResponse("Auction Item Id is required.");
    	}
    	
    	return bidsService.getBidAuditLog(auctionItemId);
    }

}
