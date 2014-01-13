

'use strict';

var appMassMailer = angular.module('appMassMailer', ['ngRoute','ngResource','appMassMailer.services']);



angular.module('appMassMailer.services', ['ngResource']).
    factory('User', function ($resource) {
        var User = $resource('../users/:userId', {userId: '@id'});
        User.prototype.isNew = function(){
            return (typeof(this.id) === 'undefined')
        };
        return User;
    });



appMassMailer.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/users', {
	templateUrl: 'views/user/list.html',
	controller: 'UserListCtrl'
      }).
      when('/users/new', {
	templateUrl: 'views/user/edit.html',
	controller: 'UserCreateController'
      }).
      when('/users/:userId', {
	templateUrl: 'views/user/edit.html',
	controller: 'UserEditCtrl'
      }).
      otherwise({
	redirectTo: '/AddNewOrder'
      });
}]);

function setErrors() {
  
}

function UserListCtrl($scope,User) {
      $scope.users = User.query();
    
}


function UserEditCtrl($scope,User) {

     $scope.User = User.get({userId: userId});
    
}

function UserCreateController($scope, $routeParams, $location, User) {
 
    $scope.User = new User();
 
    $scope.save = function () {
        $scope.User.$save(function (User, headers) {
            toastr.success("Nový uživatel vytvořen");
            $location.path('/');
        });
    };
}
