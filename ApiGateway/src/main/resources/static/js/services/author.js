'use strict'

angular.module('app.services', ['ngResource'])

.factory('AuthorService', function($resource) {
	return $resource('/api/author/:authorId', {id:'@id'});
});
