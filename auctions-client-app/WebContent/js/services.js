app.service('AuctionServices', [ '$http', function($http) {
	 
	var baseURL = 'http://localhost:8080/auctions/';
	
    this.getAuctions = function getAuctions() {
        return $http({
            method : 'GET',
            url : baseURL + 'auctionItems'
        });
    }
    
    this.getAuction = function getAuction(auctionItemId) {
        return $http({
            method : 'GET',
            url : baseURL + 'auctionItems/' + auctionItemId
        });
    }
    
    this.createAuction = function createAuction(reservePrice, itemId, description) {
    	var reqInfo =  { 'requestName':'createAuction', 
    				     'requestParam': {
    				    	'auctionItem': {
    				    		'reservePrice':reservePrice, 
    				    		'item': {
    				    			'itemId':itemId, 
    				    			'description': description
    				    			}
    				    		}
    				    	}
    					};
    	
        return $http({
            method : 'POST',
            url : baseURL + 'auctionItems',
            data: reqInfo
        });
    }
    
    this.createBid = function createBid(auctionItemId, maxAutoBidAmount, bidderName) {
    	var reqInfo =  { 'requestName':'createBid', 
    					 'requestParam': {
    						'bid': {
    							'auctionItemId':auctionItemId, 
    							'maxAutoBidAmount':maxAutoBidAmount, 
    							'bidderName':bidderName
    							}
    						}
    					};
    	
        return $http({
            method : 'POST',
            url : baseURL + 'bids',
            data: reqInfo
        });
    }
    
    this.getBidAuditLog = function getBidAuditLog(auctionItemId) {
        return $http({
            method : 'GET',
            url : baseURL + 'bids/' + auctionItemId
        });
    }
    
} ]);