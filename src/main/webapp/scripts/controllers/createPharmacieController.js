angular.module('pharma').controller('createPharmacieController', function ($scope, $location, locationParser, PharmacieResource ) {
    $scope.$location = $location;
    $scope.pharmacie = $scope.pharmacie || {};
    

    $scope.toCreate = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        PharmacieResource.save($scope.pharmacie, successCallback, errorCallback);

    };
    
    $scope.cancel = function() {
        $location.path("/Pharmacies");
    };
});