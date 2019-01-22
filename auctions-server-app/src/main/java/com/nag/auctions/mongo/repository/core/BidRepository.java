package com.nag.auctions.mongo.repository.core;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nag.auctions.model.dao.BidDO;

@Repository
public interface BidRepository extends MongoRepository<BidDO, String> {

	List<BidDO> findByAuctionItemId(String auctionItemId);
	
}
