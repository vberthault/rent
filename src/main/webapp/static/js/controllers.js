'use strict';

var publicationControllers = angular.module('rentControllers', []);

publicationControllers.controller("rentCtrl", function($scope,$http,$routeParams) {

	$http.get('http://localhost:8080/rent/car/').
	  success(function(data, status, headers, config) {
	  $scope.carsList = data;
	  $scope.selected = data[0];
	  }).
	  error(function(data, status, headers, config) {
	  });

	$scope.rent = function(selected) {
		alert(selected.plateNumber);
	};
	        

});
