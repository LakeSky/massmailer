

'use strict';

var appMassMailer = angular.module('appMassMailer', ['ngRoute','ngResource','appMassMailer.services']);



angular.module('appMassMailer.services', ['ngResource']).
    factory('User', function ($resource) {
        var User = $resource('users/:userId', {userId: '@id'});
        User.prototype.isNew = function(){
            return (typeof(this.id) === 'undefined')
        };
        return User;
    });



angular.module('appMassMailer', ['ngRoute','appMassMailer.services'])
  .config(function($routeProvider) {
    $routeProvider.
    when('/users', {templateUrl: '/MassMailMailer/static/views/user/list.html', controller: IndexUserCtrl}).
        otherwise({
           
        redirectTo: '/MassMailMailer/'
      });;

  })
  .config(function($locationProvider) {
    $locationProvider.html5Mode(true);
  }
);

function IndexCtrl($scope) {
      $scope.Test = 'User.query()';
    
}
function IndexUserCtrl($scope,User) {
    $scope.Test = User.query();
    
}
