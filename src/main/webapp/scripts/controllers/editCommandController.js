

angular.module('pharma').controller('EditCommandController', function($scope, $routeParams, $location, CommandResource , ProduitResource, ClientResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.command = new CommandResource(self.original);
            ProduitResource.queryAll(function(items) {
                $scope.produitSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.command.produit){
                        $.each($scope.command.produit, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.produitSelection.push(labelObject);
                                $scope.command.produit.push(wrappedObject);
                            }
                        });
                        self.original.produit = $scope.command.produit;
                    }
                    return labelObject;
                });
            });
            ClientResource.queryAll(function(items) {
                $scope.clientSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.command.client){
                        $.each($scope.command.client, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.clientSelection.push(labelObject);
                                $scope.command.client.push(wrappedObject);
                            }
                        });
                        self.original.client = $scope.command.client;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Commands");
        };
        CommandResource.get({CommandId:$routeParams.CommandId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.command);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.command.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Commands");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Commands");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.command.$remove(successCallback, errorCallback);
    };
    
    $scope.produitSelection = $scope.produitSelection || [];
    $scope.$watch("produitSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.command) {
            $scope.command.produit = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.command.produit.push(collectionItem);
            });
        }
    });
    $scope.clientSelection = $scope.clientSelection || [];
    $scope.$watch("clientSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.command) {
            $scope.command.client = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.command.client.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});