/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var dashboard = angular.module('dashboard', ['ngRoute', 'massmaller.service', 'ui.bootstrap']);

// Controller
// ----------
dashboard.controller('DashBoardCtrl', ['$scope',  '$routeParams', '$http', function($scope, $routeParams, $http) {

        $scope.urlStats = '../stats';


        var getPromise = $http({method: 'GET', url: '../stats'}).success(function(data, status, headers, config) {
            $scope.data = data;
        }).error(function(data, status, headers, config) {

        });



    }]);

