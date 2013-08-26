'use strict';

var controllers = angular.module('controllers', ['services']);

function BookController($scope, Book, Author) {
    $scope.currentBook = new Book();
    $scope.currentBook.releaseDate = new Date();
    $scope.books = Book.query();
    $scope.authors = Author.query();
    $scope.showId = false;

    $scope.cancel = function () {
        $scope.currentBook = new Book();
        $scope.currentBook.releaseDate = new Date();
    };

    $scope.save = function () {
        var isNew = $scope.currentBook.id == null;
        if (isNew) {
            $scope.currentBook = Book.save($scope.currentBook);
            $scope.books.push($scope.currentBook);
        } else {
            $scope.currentBook = Book.update($scope.currentBook);
        }
        $scope.cancel();
    };

    $scope.edit = function (book) {
    	$scope.currentBook = book;
    	$scope.currentBook.author = filterById($scope.authors, book.author.id);
    };

    $scope.remove = function (index, id) {
		$scope.books.splice(index, 1);
		Book.remove({bookId:id});
    };
}

function filterById(array, id) {
    return array.filter(function (object) {
    	return object.id == id;
    })[0];
}

function AuthorController($scope, Author) {
    $scope.currentAuthor = new Author();
    $scope.authors = Author.query();
    $scope.showId = false;

    $scope.cancel = function () {
        $scope.currentAuthor = new Author();
    };

    $scope.save = function () {
        var isNew = $scope.currentAuthor.id == null;
        if (isNew) {
            $scope.currentAuthor = Author.save($scope.currentAuthor);
            $scope.authors.push($scope.currentAuthor);
        } else {
            $scope.currentAuthor = Author.update($scope.currentAuthor);
        }
        $scope.cancel();
    };

    $scope.edit = function (author) {
    	$scope.currentAuthor = author;
    };

    $scope.remove = function (index, id) {
		$scope.authors.splice(index, 1);
		Author.remove({authorId:id});
    };
}

function NavController($scope, $rootScope, $route) {
	$rootScope.route = $route;
}