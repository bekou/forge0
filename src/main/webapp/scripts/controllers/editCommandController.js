

angular.module('pharma').controller('EditCommandController', function($scope, $routeParams, $location, CommandResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.command = new CommandResource(self.original);
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
    
    
    $scope.get();
});