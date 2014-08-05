/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var menu = angular.module('menu',  ['ngRoute', 'ui.bootstrap']);


// Controller
// ----------
menu.controller('MenuCtrl', ['$scope', '$location', function($scope,$location) {

   
  $scope.getClass = function(path) {
    if ($location.path().substr(0, path.length) === path) {
      return "active";
    } else {
      return "";
    }
};

$scope.getAllB = function () {
    
    return br.getAll();
};

$scope.getSubmenu = function () {
    
    return br.getSubmenu();
};



    }]);

