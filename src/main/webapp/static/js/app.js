'use strict';

var app = angular.module('rent', [
	'ngRoute','rentControllers'                                               ]);

app.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
			when('/menu', {
				templateUrl: 'partials/menu.html'
			}).
			when('/rent', {
				templateUrl: 'partials/rent.html',
				controller: 'rentCtrl'
			}).
			/*when('/getBack', {
				templateUrl: 'partials/getBack.html',
				controller: 'getBackCtrl'
			}).*/
			otherwise({
				redirectTo: '/menu'
			});
}]);
