'use strict';

var controllers = angular.module('controllers', ['services']);

controllers.controller('BookController', ['$scope', 'Book', 'Author', 'EventBus', function($scope, Book, Author, EventBus) {
    $scope.currentBook = new Book();
    $scope.currentBook.releaseDate = new Date().getTime();
    $scope.books = Book.query();
    $scope.authors = Author.query();
    $scope.showId = false;

    // Book added event
    $scope.$on('event.book.added', function(event, msg) {
        $.bootstrapGrowl("Received book added event for: "+msg.bookTitle, {offset: {from: 'top', amount: 50},width: 'auto'});
        var book = new Book();
        book.bookId = msg.bookId;
        book.title = msg.bookTitle;
        book.releaseDate = msg.releaseDate;
        book.author = filterAuthorsById($scope.authors, msg.authorId);
        $scope.$apply( function() {
            $scope.books.push(book);
        });
    });

    // Book removed event
    $scope.$on('event.book.removed', function(event, msg) {
        $.bootstrapGrowl("Received book removed event for: "+msg.bookId, {offset: {from: 'top', amount: 50},width: 'auto'});
        var i = $scope.books.length;
        while (i--){
            if ($scope.books[i].bookId == msg.bookId){
                $scope.$apply( function() {
                    $scope.books.splice(i, 1);
                });
            }
        }
    });

    $scope.cancel = function () {
        $scope.currentBook = new Book();
        $scope.currentBook.releaseDate = new Date().getTime();
    };

    $scope.edit = function (book) {
        $('#dialog').dialog();
    };

    $scope.sendAddBookCommand = function () {
        EventBus.emit("command.add.book", {bookTitle: $scope.currentBook.title, releaseDate: $scope.currentBook.releaseDate, authorId: $scope.currentBook.author.authorId});
        $scope.cancel();
    };

    $scope.sendChangeBookTitleCommand = function (bookId, newBookTitle) {
        EventBus.emit("command.change.book.title", {bookId: bookId, newBookTitle: newBookTitle});
    };

    $scope.sendRemoveBookCommand = function (bookId) {
        EventBus.emit("command.remove.book", {bookId: bookId});
    };
}]);

controllers.controller('AuthorController', ['$scope', 'Author', 'EventBus', '$rootScope', function($scope, Author, EventBus, $rootScope) {
    $scope.currentAuthor = new Author();
    $scope.authors = Author.query();
    $scope.showId = false;

    // Author added event
    $scope.$on('event.author.added', function(event, msg) {
        $.bootstrapGrowl("Received author added event for: "+msg.firstname+" "+msg.lastname, {offset: {from: 'top', amount: 50},width: 'auto'});
        var author = new Author();
        author.authorId = msg.authorId;
        author.firstname = msg.firstname;
        author.lastname = msg.lastname;
        $scope.$apply( function() {
            $scope.authors.push(author);
        });
    });

    // Author removed event
    $scope.$on('event.author.removed', function(event, msg) {
        $.bootstrapGrowl("Receifed author removed event for: "+msg.authorId, {offset: {from: 'top', amount: 50},width: 'auto'});
        var i = $scope.authors.length;
        while (i--){
            if ($scope.authors[i].authorId == msg.authorId){
                $scope.$apply( function() {
                    $scope.authors.splice(i, 1);
                });
            }
        }
    });

    $scope.cancel = function () {
        $scope.currentAuthor = new Author();
    };

    $scope.sendAddAuthorCommand = function () {
        EventBus.emit("command.add.author", {firstname: $scope.currentAuthor.firstname, lastname: $scope.currentAuthor.lastname});
        $scope.cancel();
    };

    $scope.sendRemoveAuthorCommand = function (authorId) {
        EventBus.emit("command.remove.author", {authorId: authorId});
    };
}]);

controllers.controller('NavController', ['$scope', '$rootScope', '$route', function($scope, $rootScope, $route) {
    $rootScope.route = $route;
}]);

function filterAuthorsById(array, id) {
    return array.filter(function (author) {
        return author.authorId == id;
    })[0];
}