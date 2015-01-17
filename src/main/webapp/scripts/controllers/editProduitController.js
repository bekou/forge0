

angular.module('pharma').controller('EditProduitController', function($scope, $routeParams, $location, ProduitResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.produit = new ProduitResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Produits");
        };
        ProduitResource.get({ProduitId:$routeParams.ProduitId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.produit);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.produit.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Produits");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Produits");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.produit.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});