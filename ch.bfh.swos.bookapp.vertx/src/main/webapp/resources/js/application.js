'use strict';

var bookapp = angular.module('bookapp', ['controllers', 'services','directives']);

bookapp.config(function ($routeProvider) {
    $routeProvider.
        when('/', {templateUrl: 'pages/books.html', activeMenu: 'books'}).
        when('/books', {templateUrl: 'pages/books.html', activeMenu: 'books'}).
        when('/authors', {templateUrl: 'pages/authors.html', activeMenu: 'authors'}).
        otherwise({redirectTo: '/'});
});
