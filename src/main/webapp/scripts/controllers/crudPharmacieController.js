
angular.module('pharma').controller('crudPharmacieController', function ($scope, $location, locationParser, ProduitResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    


    $scope.create = function() {
        $location.path("/Pharmacies/create");
    };
    $scope.read= function() {
        $location.path("/Pharmacies/read");
    };
    $scope.cancel = function() {
        $location.path("/Pharmacies");
    };
    $scope.cancel = function() {
        $location.path("/Pharmacies");
    };    
    $scope.cancel = function() {
        $location.path("/Pharmacies");
    };
});