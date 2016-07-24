var postApp = angular.module('postApp', []);

postApp.controller('postController', function($scope, $http) {
	$scope.deletePost = function(postId) {
		$http.delete('/forum/post/22').then(function() {
			alert("Deleted post" + $scope.postId);
		});
	};
});