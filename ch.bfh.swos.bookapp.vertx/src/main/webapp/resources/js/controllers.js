'use strict';

var controllers = angular.module('controllers', ['services']);

controllers.controller('BookController', ['$scope', 'Book', 'Author', 'EventBus', function($scope, Book, Author, EventBus) {
    $scope.currentBook = new Book();
    $scope.currentBook.releaseDate = new Date().getTime();
    $scope.books = Book.query();
    $scope.authors = Author.query();
    $scope.showId = false;

    EventBus.onopen = function () {

        // Book added event
        EventBus.registerHandler("event.book.added", function (msg, replyTo) {
            $.bootstrapGrowl("Received book added event for: "+msg.bookTitle, {offset: {from: 'top', amount: 50},width: 'auto'});
            var book = new Book();
            book.bookId = msg.bookId;
            book.title = msg.bookTitle;
            book.releaseDate = msg.releaseDate;
            book.author = filterAuthorsById($scope.authors, msg.authorId);
            $scope.books.push(book);
            $scope.$digest();
        });

        // Book removed event
        EventBus.registerHandler("event.book.removed", function (msg, replyTo) {
            $.bootstrapGrowl("Received book removed event for: "+msg.bookId, {offset: {from: 'top', amount: 50},width: 'auto'});
            var i = $scope.books.length;
            while (i--){
                if ($scope.books[i].bookId == msg.bookId){
                    $scope.books.splice(i, 1);
                }
            }
            $scope.$digest();
        });
    }

    $scope.cancel = function () {
        $scope.currentBook = new Book();
        $scope.currentBook.releaseDate = new Date().getTime();
    };

    $scope.edit = function (book) {
        $('#dialog').dialog();
    };

    $scope.sendAddBookCommand = function () {
        sendCommand(EventBus, "command.add.book", {bookTitle: $scope.currentBook.title, releaseDate: $scope.currentBook.releaseDate, authorId: $scope.currentBook.author.authorId});
        $scope.cancel();
    };

    $scope.sendChangeBookTitleCommand = function (bookId, newBookTitle) {
        sendCommand(EventBus, "command.change.book.title", {bookId: bookId, newBookTitle: newBookTitle});
    };

    $scope.sendRemoveBookCommand = function (bookId) {
        sendCommand(EventBus, "command.remove.book", {bookId: bookId});
    };
}]);

controllers.controller('AuthorController', ['$scope', 'Author', 'EventBus', function($scope, Author, EventBus) {
    $scope.currentAuthor = new Author();
    $scope.authors = Author.query();
    $scope.showId = false;

    EventBus.onopen = function () {

        // Author added event
        EventBus.registerHandler("event.author.added", function (msg, replyTo) {
            $.bootstrapGrowl("Received author added event for: "+msg.firstname+" "+msg.lastname, {offset: {from: 'top', amount: 50},width: 'auto'});
            var author = new Author();
            author.authorId = msg.authorId;
            author.firstname = msg.firstname;
            author.lastname = msg.lastname;
            $scope.authors.push(author);
            $scope.$digest();
        });

        // Author removed event
        EventBus.registerHandler("event.author.removed", function (msg, replyTo) {
            $.bootstrapGrowl("Receifed author removed event for: "+msg.authorId, {offset: {from: 'top', amount: 50},width: 'auto'});
            var i = $scope.authors.length;
            while (i--){
                if ($scope.authors[i].authorId == msg.authorId){
                    $scope.authors.splice(i, 1);
                }
            }
            $scope.$digest();
        });
    }

    $scope.cancel = function () {
        $scope.currentAuthor = new Author();
    };

    $scope.sendAddAuthorCommand = function () {
        sendCommand(EventBus, "command.add.author", {firstname: $scope.currentAuthor.firstname, lastname: $scope.currentAuthor.lastname});
        $scope.cancel();
    };

    $scope.sendRemoveAuthorCommand = function (authorId) {
        sendCommand(EventBus, "command.remove.author", {authorId: authorId});
    };
}]);

controllers.controller('NavController', ['$scope', '$rootScope', '$route', function($scope, $rootScope, $route) {
    $rootScope.route = $route;
}]);

function sendCommand(eventbus, command, payload) {
    eventbus.send(command, payload, function (reply) {
        $.bootstrapGrowl(reply, {type:'info', offset: {from: 'top', amount: 50},width: 'auto'});
    });
}

function filterAuthorsById(array, id) {
    return array.filter(function (author) {
        return author.authorId == id;
    })[0];
}