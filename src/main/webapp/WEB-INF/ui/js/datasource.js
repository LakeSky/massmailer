/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var dataSource = angular.module('dataSource', ['ngRoute', 'entityService', 'upload']);

// Controller
// ----------
dataSource.controller('DataSourceListCtrl', ['$scope', 'Entity', function($scope, Entity) {

        $scope.datasources = Entity.DataSource.query();

    }]);



// Controller
// ----------
dataSource.controller('DataSourceEditCtrl', ['$scope', '$routeParams', '$location', 'Entity', function($scope, $routeParams, $location, Entity) {

        var dataSourceId = $routeParams.dataSourceId;
        $scope.DataSource = Entity.DataSource.get({dataSourceId: dataSourceId});
        $scope.save = function() {
            $scope.form = "dataSource";

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
dataSource.controller('DataSourceCreateController', ['$rootScope', '$scope', '$routeParams', '$location', 'Entity', 'uploadService', function($rootScope, $scope, $routeParams, $location, Entity, uploadService) {
   $scope.DataSource = new Entity.DataSource();
        // 'files' is an array of JavaScript 'File' objects.
        $scope.files = [];
        $scope.fields = [];
        $scope.$watch('UploadFile', function(newValue, oldValue) {
            // Only act when our property has changed.
            if (newValue !== oldValue) {
                console.log('Controller: $scope.files changed. Start upload.');

                uploadService.send($scope.UploadFile);

            }
        }, true);


        $rootScope.$on('upload:loadstart', function() {
            console.log('Controller: on `loadstart`');
        });
        $rootScope.$on('upload:succes', function(event, xhr) {

            $scope.$apply(function() {


                $scope.DataSource.fileName = xhr.currentTarget.responseText;
                
                var DataStructure = Entity.DataStructure.get({fileId: 1}, function() {

                  $scope.DataSource.dataStructure = DataStructure;
                    toastr.success( $scope.DataSource.dataStructure.firstRowCnames);

                });

            });


            toastr.success(xhr.currentTarget.responseText);




        });
        $rootScope.$on('upload:error', function() {
            console.log('Controller: on `error`');
        });




        $scope.save = function() {
                       $scope.form = $scope.$eval("dataSource");

            $scope.DataSource.$save(
                    function(DataSource, headers) {
                        handleFormSucces("Nový uživatel vytvořen", $location, '/dataSource');
                    }, function(error) {

                handleFormError($scope, error.data);

            });
        };


    }]);








