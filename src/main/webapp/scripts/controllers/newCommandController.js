
angular.module('pharma').controller('NewCommandController', function ($scope, $location, locationParser, CommandResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.command = $scope.command || {};
    

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