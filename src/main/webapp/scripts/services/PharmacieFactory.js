angular.module('pharma').factory('PharmacieResource', function($resource) {
	var resource = $resource('rest/pharmacies/:PharmacieId', {
		PharmacieId : '@id'
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