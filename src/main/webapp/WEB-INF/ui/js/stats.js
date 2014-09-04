/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var stats = angular.module('stats', ['ngRoute', 'massmaller.service', 'ui.bootstrap']);

// Controller
// ----------
stats.controller('StatsCtrl', ['$scope', 'Entity', '$routeParams', '$http', function($scope, Entity, $routeParams, $http) {

        $scope.separated = true;
        $scope.ctype = "FINISHED";
        function getData() {
            var campains = Entity.Campain.browse({
                page: $scope.filterParams.page,
                limit: $scope.filterParams.limit,
                sort: $scope.filterParams.sort,
                sortDir: $scope.filterParams.sortDir,
                search: $scope.filterParams.search,
                searchString: $scope.filterParams.searchString,
                ctype: $scope.ctype
            }, function() {

                $scope.campins = campains;
            });
        }

        $scope.search = function(searchString) {
            $scope.filterParams.sort = 'campainName';
            $scope.filterParams.searchString = searchString;
            $scope.filterParams.page = 0;
            getData();
        };
        $scope.paginate = function(value) {
            // Only act when our property has changed.
            if (undefined !== value) {
                $scope.filterParams.page = value;
                getData();
            }
        };
        var campains = Entity.Campain.browse({
            ctype: $scope.ctype
        }, function(

                ) {
            $scope.campains = campains;
        });


        $scope.selectedCampains = [];

        $scope.addToStats = function(c) {
            c.isDisabled = true;
            $scope.selectedCampains.push(c);
            more();


        };



        $scope.$watch('separated', function(newValue, oldValue) {
            if (newValue !== oldValue) {
                more();

            }
        });
        $scope.removeFromStats = function(c) {
            c.isDisabled = false;

            angular.forEach($scope.selectedCampains, function(item, index) {
                if (c.id === item.id) {
                    $scope.selectedCampains.splice(index, 1);

                }

            });

            more();


        };



        function more() {

            var selectedIds = {
                campainIds: [],
                separated: $scope.separated
            };

            angular.forEach($scope.selectedCampains, function(c) {
                selectedIds.campainIds.push(c.id);

            });
            if (selectedIds.campainIds.length !== 0) {
                var getPromise = $http({method: 'GET',
                    url: 'stats/campains/combined',
                    params: selectedIds
                }).success(function(data, status, headers, config) {
                    $scope.campainStats = data;
                }).error(function(data, status, headers, config) {

                });
            }else {
                  $scope.campainStats.length = 0;
                
            }

        }



    }]);

