angular.module('pharma').factory('CommandResource', function($resource) {
	var resource = $resource('rest/commands/:CommandId', {
		CommandId : '@id'
	}, {
		'queryAll' : {
			method : 'GET',
			isArray : true
		},
		'query' : {
			method : 'GET',
			isArray : false
		},
		'update' : {
			method : 'PUT'
		}
	});
	return resource;
});