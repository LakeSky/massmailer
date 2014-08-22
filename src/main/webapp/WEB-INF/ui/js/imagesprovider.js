/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var users = angular.module('menu', ['massmaller.service']);


// Controller
// ----------
users.controller('ImagesListCtrl', ['$scope', 'Entity', function($scope, Entity) {
   
    $scope.users = Entity.User.query();

    }]);



