
angular.module('pharma').controller('NewProduitController', function ($scope, $location, locationParser, ProduitResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.produit = $scope.produit || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Produits/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ProduitResource.save($scope.produit, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Produits");
    };
});