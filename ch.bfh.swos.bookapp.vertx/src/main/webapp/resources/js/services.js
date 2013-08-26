'use strict';

var services = angular.module('services', ['ngResource']);

services.factory('Book', function ($resource) {
    return $resource('rest/books/:bookId', {bookId:'@id'}, {
        'update': {method: 'PUT'}
    });
});

services.factory('Author', function ($resource) {
    return $resource('rest/authors/:authorId', {authorId:'@id'}, {
        'update': {method: 'PUT'}
    });
});

services.factory('EventBus', function () {
    return new vertx.EventBus("http://localhost:7777/eventbus");
});