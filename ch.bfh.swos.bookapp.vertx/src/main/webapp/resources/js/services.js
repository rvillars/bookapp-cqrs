'use strict';

var services = angular.module('services', ['ngResource']);

services.factory('Book', ['$resource', function ($resource) {
    return $resource('rest/books/:bookId', {bookId:'@id'}, {
        'update': {method: 'PUT'}
    });
}]);

services.factory('Author', ['$resource', function ($resource) {
    return $resource('rest/authors/:authorId', {authorId:'@id'}, {
        'update': {method: 'PUT'}
    });
}]);

services.factory('VertXEventBus', [function () {
    return new vertx.EventBus("http://localhost:7777/eventbus");
}]);

services.factory('EventBus', ['VertXEventBus', '$rootScope', function (VertXEventBus, $rootScope) {
    var eventHandlers = [];
    $rootScope.connectionState = "WAITING...";
    $rootScope.connectionStateClass = "badge-warning";
    VertXEventBus.onopen = function() {
        angular.forEach(eventHandlers, function(eventHandler) {
            VertXEventBus.registerHandler(eventHandler.eventId, eventHandler.callback);
        });
        $rootScope.connectionState = "CONNECTED";
        $rootScope.connectionStateClass = "badge-success";
        $rootScope.$digest();
    }
    VertXEventBus.onclose = function() {
        $rootScope.connectionState = "NOT CONNECTED";
        $rootScope.connectionStateClass = "badge-important";
        $rootScope.$digest();
    }
    return {
        on: function(eventId, callback) {
            eventHandlers.push({eventId:eventId, callback:callback});
        },

        emit: function(commandId, payload) {
            VertXEventBus.send(commandId, payload, function (reply) {
                $.bootstrapGrowl(reply, {type:'info', offset: {from: 'top', amount: 50},width: 'auto'});
            });
        }
    };
}]);