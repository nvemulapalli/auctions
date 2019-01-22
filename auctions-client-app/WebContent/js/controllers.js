app.controller('AuctionsCtrl', [ '$scope', 'AuctionServices',
		function($scope, AuctionServices) {
	
			$scope.showAuctions = false;
			$scope.showFindAuction = false;
			$scope.showCreateAuction = false;
			$scope.showCreateBid = false;
			$scope.showAuditLogBid = false;
			
			function clearMessages() {
				$scope.message = '';
				$scope.errorMessage = '';
				$scope.auctionItemId = '';
				$scope.bidId = '';
			}

			$scope.showFindAuctionElement = function() {
				$scope.showAuctions = false;
				$scope.showCreateAuction = false;
				$scope.showCreateBid = false;
				$scope.showAuditLogBid = false;
				$scope.showFindAuction = true;
				clearMessages();
			}
		
			$scope.showCreateAuctionElement = function() {
				$scope.showAuctions = false;
				$scope.showFindAuction = false;
				$scope.showCreateBid = false;
				$scope.showAuditLogBid = false;
				$scope.showCreateAuction = true;
				clearMessages();
			}
		
			$scope.showCreateBidElement = function() {
				$scope.showAuctions = false;
				$scope.showFindAuction = false;
				$scope.showCreateAuction = false;
				$scope.showAuditLogBid = false;
				$scope.showCreateBid = true;
				clearMessages();
			}
			
			$scope.showAuditLogBidElement = function() {
				$scope.showAuctions = false;
				$scope.showFindAuction = false;
				$scope.showCreateAuction = false;
				$scope.showCreateBid = false;
				$scope.showAuditLogBid = true;
				clearMessages();
			}
	
			$scope.getAuctions = function() {
				clearMessages();
				AuctionServices.getAuctions().then(function success(response) {
					$scope.showAuctions = true;
					$scope.showFindAuction = false;
					$scope.showCreateAuction = false;
					$scope.showCreateBid = false;
					$scope.showAuditLogBid = false;
					
					$scope.auctionItems = response.data.result.allAuctionItems;
					$scope.message = response.data.result.resultMessage
					$scope.errorMessage = '';
				}, function error(response) {
					$scope.showAuctions = false;
					$scope.message = '';
					$scope.errorMessage = response.statusText;
				});
			};
			
			$scope.getBids = function() {
				clearMessages();
				$scope.bids = '';
				if(angular.isDefined($scope.auctionItemIdForBids) && $scope.auctionItemIdForBids != '') {
					AuctionServices.getBidAuditLog($scope.auctionItemIdForBids).then(function success(response) {					
						$scope.bids = response.data.result.allBids;
						$scope.message = response.data.result.resultMessage
						$scope.errorMessage = '';
					}, function error(response) {
						$scope.message = '';
						$scope.errorMessage = response.statusText;
					});
				} else {
					$scope.message = 'Please enter a valid auction item id.';
					$scope.auctionItem = '';
				}
			};
			
			$scope.findAuction = function() {
				if(angular.isDefined($scope.auctionItemIdToFind) && $scope.auctionItemIdToFind != '') {
					AuctionServices.getAuction($scope.auctionItemIdToFind).then(function success(response) {
						$scope.auctionItem = response.data.result.auctionItem;
						$scope.message = response.data.result.resultMessage;
						$scope.errorMessage = '';
					}, function error(response) {
						$scope.message = '';
						$scope.errorMessage = response.statusText;
					});
				} else {
					$scope.message = 'Please enter a valid auction item id.';
					$scope.auctionItem = '';
				}
			};
			
			$scope.createAuction = function() {
				if(!angular.isDefined($scope.reservePriceToCreate) || $scope.reservePriceToCreate == '') {
					$scope.message = 'Please enter a valid reserve price.';
					return;
				} else if(!angular.isDefined($scope.itemIdToCreate) || $scope.itemIdToCreate == '') {
					$scope.message = 'Please enter a valid item id.';
					return;
				} 
				
				AuctionServices.createAuction($scope.reservePriceToCreate, $scope.itemIdToCreate, $scope.descriptionToCreate).then(function success(response) {
					$scope.auctionItemId = response.data.result.auctionItemId;
					$scope.message = response.data.result.resultMessage;
					$scope.errorMessage = '';
				}, function error(response) {
					$scope.message = '';
					$scope.errorMessage = response.statusText;
				});
			};
			
			$scope.createBid = function() {
				if(!angular.isDefined($scope.bidAuctionItemIdToCreate) || $scope.bidAuctionItemIdToCreate == '') {
					$scope.message = 'Please enter a valid item id.';
					return;
				} else if(!angular.isDefined($scope.maxAutoBidAmountToCreate) || $scope.maxAutoBidAmountToCreate == '') {
					$scope.message = 'Please enter a valid max auto bid amount.';
					return;
				} else if(!angular.isDefined($scope.bidderNameToCreate) || $scope.bidderNameToCreate == '') {
					$scope.message = 'Please enter a valid bidder name.';
					return;
				} 
				
				AuctionServices.createBid($scope.bidAuctionItemIdToCreate, $scope.maxAutoBidAmountToCreate, $scope.bidderNameToCreate).then(function success(response) {
					if(response.data.error == null) {
						$scope.bidId = response.data.result.bidId;
						$scope.message = response.data.result.resultMessage;
					} else {
						$scope.message = response.data.error.errorMessage;
					}
					$scope.errorMessage = '';
				}, function error(response) {
					$scope.message = '';
					$scope.errorMessage = response.statusText;
				});
			};
			
} ]);