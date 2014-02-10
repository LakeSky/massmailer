/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var dataSource = angular.module('dataSource', ['ngRoute', 'entityService', 'upload', 'ui.bootstrap']);

// Controller
// ----------
dataSource.controller('DataSourceListCtrl', ['$scope', 'Entity', '$modal', function($scope, Entity, $modal) {

        $scope.datasources = Entity.DataSource.query();
        $scope.openForm = function() {

            var modalInstance = $modal.open({
                templateUrl: 'views/datasource/edit.html',
                resolve: {
                }
            });

            modalInstance.result.then(function(selectedItem) {
                $scope.selected = selectedItem;
            }, function() {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };



    }]);






// Controller
// ----------
dataSource.controller('DataSourceCreateController', ['$rootScope', '$scope', '$routeParams', '$location', 'Entity', 'uploadService' , function($rootScope, $scope, $routeParams, $location, Entity, uploadService) {
        var dataSourceId = $routeParams.dataSourceId;
        if (undefined === dataSourceId) {
            $scope.DataSource = new Entity.DataSource();
        } else {
            $scope.DataSource = Entity.DataSource.get({dataSourceId: dataSourceId});

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


                $scope.DataSource.fileName = xhr.currentTarget.responseText;

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


            $scope.DataSource.$save(
                    function(DataSource, headers) {
                        handleFormSucces("Nový uživatel vytvořen", $location, '/dataSource');
                    }, function(error) {

                handleFormError($scope, error.data);

            });
        };

        $scope.ok = function() {
             $scope.save();
            modalInstance.close();
        };

        $scope.cancel = function() {
            dismiss('cancel');
        };

    }]);








