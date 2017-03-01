'use strict'

angular.module('app.controllers', [])

.controller('GetAuthorCtrl', function($scope, AuthorService, $stateParams) {
	AuthorService.get({authorId:$stateParams.authorId}, function(author) {
		$scope.author = author;
	});
});