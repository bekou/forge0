'use strict';

angular.module('pharma',['ngRoute','ngResource'])
.config(['$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
	.when('/Clients',{templateUrl:'views/Client/search.html',controller:'SearchClientController'})
	.when('/Clients/new',{templateUrl:'views/Client/detail.html',controller:'NewClientController'})
	.when('/Clients/edit/:ClientId',{templateUrl:'views/Client/detail.html',controller:'EditClientController'})
	.when('/Commands',{templateUrl:'views/Command/search.html',controller:'SearchCommandController'})
	.when('/Commands/new',{templateUrl:'views/Command/detail.html',controller:'NewCommandController'})
	.when('/Commands/edit/:CommandId',{templateUrl:'views/Command/detail.html',controller:'EditCommandController'})
	.when('/Produits',{templateUrl:'views/Produit/search.html',controller:'SearchProduitController'})
	.when('/Produits/new',{templateUrl:'views/Produit/detail.html',controller:'NewProduitController'})
	.when('/Produits/edit/:ProduitId',{templateUrl:'views/Produit/detail.html',controller:'EditProduitController'})
	.when('/Commands/client/:ClientId',{templateUrl:'views/Command/clientcommands.html',controller:'ClientCommandController'})
	.when('/Pharmacies',{templateUrl:'views/Pharmacie/crud.html',controller:'crudPharmacieController'})
	.when('/Pharmacies/create',{templateUrl:'views/Pharmacie/create.html',controller:'createPharmacieController'})
	.when('/Pharmacies/read',{templateUrl:'views/Pharmacie/read.html',controller:'readPharmacieController'})
	.otherwise({
		redirectTo: '/'
	});
}])
.controller('LandingPageController', function LandingPageController() {
})
.controller('NavController', function NavController($scope, $location) {
	$scope.matchesRoute = function(route) {
		var path = $location.path();
		return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
	};
});
