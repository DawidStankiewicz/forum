var topicApp = angular.module('topicApp', ['ngRoute'])

.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
	  $routeProvider.when('/rest/topic/:topicId', {  
		    controller: 'topicController'
		});

	}]);

topicApp.controller('postController', function($scope, $http) {
	$scope.deletePost = function(postId) {
		$http.delete('/forum/post/22').then(function() {
			alert("Deleted post" + $scope.postId);
		});
	};
});

topicApp.controller('topicController', function($scope, $routeParams, $http) {
	console.log("test1:  " + $scope.topicId);
	console.log("test2:  " + $routeParams.$topicId);
	
	
	$scope.initTopic = function(topicId) {
		$http.get('/forum/rest/topic/1').success(function(data) {
			$scope.topic = data;
			console.log("topic: " + $scope.topic);
		});
	}
});