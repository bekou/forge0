

angular.module('pharma').controller('ClientCommandController', function($scope, $http, CommandResource , ClientCommandResource, ProduitResource, ClientResource,$routeParams) {

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    
    /*
     * Afficher les commandes d'un client.
     */
    function loadCommands(){
    	var clientId = $routeParams.ClientId;
    	ClientCommandResource.findByClientId(clientId).success(function(results){
    		$scope.searchResults = results;
    		$scope.numberOfPages();
    	}).error(function(error){
    		console.log(error);
    	});
    }
    $scope.numberOfPages = function() {
        var result = Math.ceil($scope.filteredResults.length/$scope.pageSize);
        var max = (result == 0) ? 1 : result;
        $scope.pageRange = [];
        for(var ctr=0;ctr<max;ctr++) {
            $scope.pageRange.push(ctr);
        }
        return max;
    };
    $scope.clientList = ClientResource.queryAll();

    $scope.performSearch = function() {
    	loadCommands();
    };
    
    $scope.previous = function() {
       if($scope.currentPage > 0) {
           $scope.currentPage--;
       }
    };
    
    $scope.next = function() {
       if($scope.currentPage < ($scope.numberOfPages() - 1) ) {
           $scope.currentPage++;
       }
    };
    
    $scope.setPage = function(n) {
       $scope.currentPage = n;
    };

    $scope.performSearch();
    
    
});