
angular.module('pharma').controller('NewCommandController', function ($scope, $location, locationParser, CommandResource , ProduitResource, ClientResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.command = $scope.command || {};
    
    $scope.produitList = ProduitResource.queryAll(function(items){
        $scope.produitSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("produitSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.command.produit = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.command.produit.push(collectionItem);
            });
        }
    });
    
    $scope.clientList = ClientResource.queryAll(function(items){
        $scope.clientSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("clientSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.command.client = {};
            $scope.command.client.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Commands/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        CommandResource.save($scope.command, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Commands");
    };
});