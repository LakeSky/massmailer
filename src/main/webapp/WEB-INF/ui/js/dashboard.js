/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var dashboard = angular.module('dashboard', ['ngRoute', 'entityService', 'ui.bootstrap']);

// Controller
// ----------
dashboard.controller('DashBoardCtrl', ['$scope', 'Entity', '$routeParams', '$http', function($scope, Entity, $routeParams, $http) {

        $scope.urlStats = '../stats';


        var getPromise = $http({method: 'GET', url: '../stats'}).success(function(data, status, headers, config) {
            $scope.data = data;
        }).error(function(data, status, headers, config) {

        });



    }]);

