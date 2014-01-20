/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var dataSource = angular.module('dataSource', ['entityService']);


// Controller
// ----------
dataSources.controller('DataSourceListCtrl', ['$scope', 'Entity', function($scope, Entity) {
   
    $scope.datasources = Entity.DataSource.query();

    }]);



// Controller
// ----------
dataSources.controller('DataSourceEditCtrl', ['$scope', '$routeParams', '$location' , 'Entity', function($scope, $routeParams, $location, Entity) {
   
    var dataSourceId = $routeParams.dataSourceId;
    $scope.DataSource = Entity.DataSource.get({dataSourceId: dataSourceId});
    $scope.save = function() {
       $scope.form = "dataSource"

        $scope.DataSource.$save(
                function(Entity, headers) {
                    handleFormSucces("Nový uživatel vytvořen", $location, '/dataSource');
                }, function(error) {
            handleFormError($scope, error.data);

        });
    };

    }]);



// Controller
// ----------
dataSources.controller('DataSourceCreateController', ['$scope', '$routeParams', '$location' , 'Entity', function($scope, $routeParams, $location, Entity) {
   
    $scope.DataSource = new Entity.DataSource();
    $scope.save = function() {
        $scope.form = "dataSource"
        $scope.DataSource.$save(
                function(DataSource, headers) {
                    handleFormSucces("Nový uživatel vytvořen", $location, '/dataSource');
                }, function(error) {
                   
            handleFormError($scope, error.data);

        });
    };

    }]);


