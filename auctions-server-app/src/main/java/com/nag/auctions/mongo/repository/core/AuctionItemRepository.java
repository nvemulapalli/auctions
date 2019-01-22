package com.nag.auctions.mongo.repository.core;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nag.auctions.model.dao.AuctionItemDO;

@Repository
public interface AuctionItemRepository extends MongoRepository<AuctionItemDO, String> {

	AuctionItemDO findByAuctionItemId(String auctionItemId);
	
}
