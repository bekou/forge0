angular.module('pharma').factory('ClientCommandResource',['$http', function($http){
    var service = {};
    
    service.findByClientId = function (clientId) {
    	return $http.get('rest/commands/findByClientId/'+clientId);
    };
    return service;
}]);