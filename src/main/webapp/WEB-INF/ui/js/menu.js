/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var users = angular.module('users',  ['ngRoute', 'ui.bootstrap']);


// Controller
// ----------
users.controller('MenuCtrl', ['$scope', '$location', function($scope,$location) {
   
  $scope.getClass = function(path) {
    if ($location.path().substr(0, path.length) === path) {
      return "active";
    } else {
      return "";
    }
};

    }]);


