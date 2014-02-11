/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var dataSource = angular.module('dataSource', ['ngRoute', 'entityService', 'upload', 'ui.bootstrap']);

// Controller
// ----------
dataSource.controller('DataSourceListCtrl', ['$scope', 'Entity', '$modal', '$routeParams', function($scope, Entity, $modal, $routeParams) {

        $scope.datasources = Entity.DataSource.query();


        var modalInstance;
        $scope.openDeleteDialog = function(dataSourceId, name) {
            $routeParams.dataSourceId = dataSourceId;


            modalInstance = $modal.open({
                templateUrl: 'views/deleteDialog.html',
                controller: 'DataSourceDeleteController'
            });

            modalInstance.result.then(function() {
                $scope.datasources = Entity.DataSource.query();
            }, function() {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        $scope.openForm = function(dataSourceId) {
            $routeParams.dataSourceId = dataSourceId;
            modalInstance = $modal.open({
                templateUrl: 'views/datasource/edit.html',
                resolve: {
                }
            });

            modalInstance.result.then(function() {
                $scope.datasources = Entity.DataSource.query();
            }, function() {
                ;
            });
        };



    }]);






// Controller
// ----------
dataSource.controller('DataSourceCreateController', ['$rootScope', '$scope', '$routeParams', '$location', 'Entity', 'uploadService', '$q', function($rootScope, $scope, $routeParams, $location, Entity, uploadService, $q) {
        var dataSourceId = $routeParams.dataSourceId;
        if (undefined === dataSourceId) {
            $scope.DataSource = new Entity.DataSource();
        } else {
            DataSource = Entity.DataSource.get({dataSourceId: dataSourceId});
            $scope.DataSource = DataSource;
        }


        // 'files' is an array of JavaScript 'File' objects.
        $scope.files = [];
        $scope.fields = [];

        $scope.$watch('dataSource', function(value) {
            // Only act when our property has changed.
            if (undefined !== value) {
                $scope.form = $scope.$eval("dataSource");

            }
        }, true);

        $scope.$watch('UploadFile', function(newValue, oldValue) {
            // Only act when our property has changed.
            if (newValue !== oldValue) {
                console.log('Controller: $scope.files changed. Start upload.');
                if (typeof $scope.DataSource.dataStructure !== 'undefined' && typeof $scope.DataSource.dataStructure.previewRows !== 'undefined') {
                    $scope.DataSource.dataStructure.previewRows = null;

                }

                uploadService.send($scope.UploadFile);

            }
        }, true);


        $rootScope.$on('upload:loadstart', function() {
            console.log('Controller: on `loadstart`');
        });
        $rootScope.$on('upload:succes', function(event, xhr) {

            $scope.$apply(function() {




                var DataStructure = Entity.DataStructure.get({fileId: xhr.currentTarget.responseText}, function() {

                    $scope.DataSource.dataStructure = DataStructure;

                 

                });

            });


            toastr.success(xhr.currentTarget.responseText);




        });
        $rootScope.$on('upload:error', function() {
            console.log('Controller: on `error`');
        });




        $scope.save = function() {
            var deferred = $q.defer();
            $scope.DataSource.$save(
                    function(DataSource, headers) {
                        deferred.resolve(DataSource);
                        return  handleFormSucces("Nový datový zdroj vytvořen", $location, '/datasource');
                    }, function(error) {
                return   handleFormError($scope, error.data);

            });
            return deferred.promise;
        };



        $scope.ok = function() {

            var myDataPromise = $scope.save();
            var contrr = this;
            myDataPromise.then(function(DataSource) {  // this is only run after $http completes
                contrr.$close();

            });


        };

        $scope.cancel = function() {
            this.$dismiss('cancel');
        };

    }]);









// Controller
// ----------
dataSource.controller('DataSourceDeleteController', ['$scope', '$routeParams', '$location', 'Entity', '$q', function($scope, $routeParams, $location, Entity, $q) {
        var dataSourceId = $routeParams.dataSourceId;

        if (undefined === dataSourceId) {

        } else {
            var DataSource = Entity.DataSource.get({dataSourceId: dataSourceId}, function() {
                $scope.deleteMessage = DataSource.name;
                $scope.DataSource = DataSource;
            });





        }







        $scope.delete = function() {
            var deferred = $q.defer();
            $scope.DataSource.$delete(
                    function(DataSource, headers) {
                        deferred.resolve(DataSource);
                        handleFormSucces("Smazano", $location, '/datasource');

                    }, function(error) {
                return   handleFormError($scope, error.data);

            });
            return deferred.promise;
        };



        $scope.okDelete = function() {

            var myDataPromise = $scope.delete();
            var contrr = this;
            myDataPromise.then(function(DataSource) {  // this is only run after $http completes
                $scope.datasources = Entity.DataSource.query();
                contrr.$close();

            });


        };






        $scope.cancel = function() {
            this.$dismiss('cancel');
        };

    }]);