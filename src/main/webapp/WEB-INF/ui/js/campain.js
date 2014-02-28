/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var campain = angular.module('campain', ['ngRoute', 'entityService', 'upload', 'ui.bootstrap']);



// Controller
// ----------
dataSource.controller('CampainListCtrl', ['$scope', 'Entity', '$modal', '$routeParams', function($scope, Entity, $modal, $routeParams) {




        var campains = Entity.Campain.query(function() {

            $scope.campains = campains;
        });
        var modalInstance;
        $scope.openDeleteDialog = function(campainsId, name) {
            $routeParams.dataSourceId = dataSourceId;
            modalInstance = $modal.open({
                templateUrl: 'views/deleteDialog.html',
                controller: 'CampainDeleteController'
            });
            modalInstance.result.then(function() {
                $scope.campains = Entity.Campain.query();
            }, function() {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        $scope.openForm = function(campainId) {
            $routeParams.campainId = campainId;
            modalInstance = $modal.open({
                windowClass: 'modal-campain',
                templateUrl: 'views/campain/edit.html',
                resolve: {
                }
            });

            modalInstance.result.then(function() {
                $scope.datasources = Entity.DataSource.query();
            }, function() {
                ;
            });
        };
        $scope.openBrowse = function(dataSourceId) {
            $routeParams.dataSourceId = dataSourceId;
            modalInstance = $modal.open({
                controller: 'CampainRowsListCtrl',
                windowClass: 'modal-campain',
                templateUrl: 'views/campain/browse.html',
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

dataSource.controller('CampainRowsListCtrl', ['$scope', 'Entity', '$modal', '$routeParams', function($scope, Entity, $modal, $routeParams) {

        $scope.currentPage = 0;
        var dataSourceId = $routeParams.dataSourceId;
        $scope.filterParams = {
            page: 0,
            limit: 10,
            sort: '2',
            sortDir: 1,
            search: '0',
            searchString: '*'


        };
        function getData() {
            var dataSourceRows = Entity.DataSourceRow.query({dataSourceId: dataSourceId,
                page: $scope.filterParams.page,
                limit: $scope.filterParams.limit,
                sort: $scope.filterParams.sort,
                sortDir: $scope.filterParams.sortDir,
                search: $scope.filterParams.search,
                searchString: $scope.filterParams.searchString
            }, function() {

                $scope.dataSourceRows = dataSourceRows;
            });
        }

        $scope.search = function(searchField) {
            $scope.filterParams.sort = searchField.index;
            $scope.filterParams.searchString = searchField.searchString;
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

        $scope.cancel = function() {
            this.$dismiss('cancel');
        };
    }]);




// Controller
// ----------
dataSource.controller('CampainEditController', ['$rootScope', '$scope', '$routeParams', '$location', 'Entity', 'uploadService', '$q', function($rootScope, $scope, $routeParams, $location, Entity, uploadService, $q) {

        getDataSourceById = function(id) {
            var deferred = $q.defer();
            var dataSources = Entity.DataSource.get({
                dataSourceId: id

            }, function() {
                deferred.resolve(dataSources);

            });
            return deferred.promise;
        };
        var campainId = $routeParams.campainId;
        if (undefined === campainId) {
            $scope.Campain = new Entity.Campain();
        } else {
            Campain = Entity.Campain.get({campainId: campainId}
            , function() {

                if (Campain.dataSourceId !== null) {
                    var myDataPromise = getDataSourceById(Campain.dataSourceId);
                    myDataPromise.then(function(datasource) {  // this is only run after $http completes
                        $scope.datasourceSelected = datasource;
                    });

                }

            });

            $scope.Campain = Campain;

        }














        // 'files' is an array of JavaScript 'File' objects.
        $scope.files = [];
        $scope.fields = [];



        $scope.$watch('campainForm', function(value) {
            // Only act when our property has changed.
            if (undefined !== value) {

                $scope.form = $scope.$eval("campainForm");

            }
        }, true);

        $scope.$watch('UploadFile', function(newValue, oldValue) {
            // Only act when our property has changed.
            if (newValue !== oldValue) {
                console.log('Controller: $scope.files changed. Start upload.');
                uploadService.send($scope.UploadFile);

            }
        }, true);
 
     $scope.atcangeged = function customizeAttachment() {
       var TemplateFieldsPromise =    Entity.TemplateFields.get({fileId: $scope.Campain.attachmentName});
             
   
         TemplateFieldsPromise.$promise.then(function(result) {
  $scope.templateFields = result.placeHolders;
   
   });
   $scope.templateUrl = '../template/pdfpreview/'  + Campain.attachmentName + '/' + Campain.dataSourceId;

              
     };



        $rootScope.$on('upload:loadstart', function() {
            $scope.loadingfile = true;
            console.log('Controller: on `loadstart`');
        });
        $rootScope.$on('upload:succes', function(event, xhr) {

            $scope.$apply(function() {

                $scope.loadingfile = false;

                $scope.Campain.attachmentName = xhr.currentTarget.responseText;
            });


            toastr.success(xhr.currentTarget.responseText);




        });
        $rootScope.$on('upload:error', function() {
            console.log('Controller: on `error`');
        });




        $scope.save = function() {
            $scope.Campain.emailText = CKEDITOR.instances.rr.getData();
            var deferred = $q.defer();

            $scope.Campain.$save(
                    function(Campain, headers) {
                        deferred.resolve(DataSource);
                        return  handleFormSucces("Nový datový zdroj vytvořen", $location, '/campain');
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



        $scope.searchByName = function(val) {

            var deferred = $q.defer();
            var dataSources = Entity.DataSource.search({
                search: 'name',
                searchString: val

            }, function() {
                deferred.resolve(dataSources);

            });
            return deferred.promise;
        };



        $scope.searchDataSources = function(value) {

            var myDataPromise = $scope.searchByName(value);

            return   myDataPromise.then(function(datasource) {  // this is only run after $http completes

                var datasources = [];
                angular.forEach(datasource, function(item) {
                    datasources.push(item);
                });
                return datasources;

            });


        };




        $scope.onDatasourceSelected = function(datasource) {
            $scope.Campain.dataSourceId = datasource.id;

        };





        $scope.cancel = function() {
            this.$dismiss('cancel');
        };








    }]);









// Controller
// ----------
dataSource.controller('CampainDeleteController', ['$scope', '$routeParams', '$location', 'Entity', '$q', function($scope, $routeParams, $location, Entity, $q) {
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
