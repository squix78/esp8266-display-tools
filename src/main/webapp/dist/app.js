angular.module('FontConverter', ['ngRoute', 'ngResource'])
.config(function($routeProvider) {
    $routeProvider
    .when('/home', {
    	controller : 'MainController',
    	templateUrl : 'partials/main.html'
    })
    .when('/xbm', {
    	controller : 'XbmController',
    	templateUrl : 'partials/xbm.html'
    })
    .otherwise({
       redirectTo: '/home'
    });
})
.factory('Ping', ['$resource', function($resource) {
    return $resource('/rest/ping');
}])
.factory('FontFamilies', ['$resource', function($resource) {
    return $resource('/rest/fontFamilies');
}])
.factory('Fonts', ['$resource', function($resource) {
    return $resource('/rest/fonts/:fontFamily');
}])
.factory('FontArray', ['$resource', function($resource) {
	return $resource('/rest/fontArray');
}])
.factory('XbmPreview', ['$resource', function($resource) {
	return $resource('/rest/xbmPreview');
}])
.filter('htmlEncode', function() {
  return window.encodeURIComponent;
});
;
angular.module('FontConverter').controller('MainController', 
	function($scope, $rootScope, $resource, $location, $routeParams, FontFamilies, Fonts, FontArray) {
	  $scope.styles = {
	                   "0": {name:"Plain"},
	                   "1": {name:"Bold"},
	                   "2": {name:"Italic"},
	                   "3": {name:"Bold & Italic"}
	  };
	  $scope.previewDisplay = "OLED96"
	  $scope.fontArray = new FontArray();
	  $scope.fontArray.name = "Dialog";
	  $scope.fontArray.style = "0";
	  $scope.fontArray.size = 16;
	  $scope.fontArray.libVersion = "3";
	  $scope.fontArray.fontArray = "";
	  $scope.isDataReady = false;
	  $scope.fontFamilies = FontFamilies.query();
	  $scope.fonts = Fonts.query({fontFamily: $scope.fontArray.fontFamily}, function() {
		  $scope.isDataReady = true;
	  });

	  $scope.getFontArray = function() {
	  	 $scope.fontArray.fontArray = "";
	  	 delete $scope.fontArray.$resolved;
	  	 console.log($scope.fontArray);
		 $scope.fontArray.$save();
	  };
  });
angular.module('FontConverter').controller('XbmController', 
	function($scope, $rootScope, $resource, $location, $routeParams, FontFamilies, Fonts, FontArray) {
	 	$scope.imageDefinition = {};
	 	$scope.imageDefinition.url = "https://upload.wikimedia.org/wikipedia/commons/8/81/2012_Suedchinesischer_Tiger.JPG";
  });