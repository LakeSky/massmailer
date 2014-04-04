/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var campain = angular.module('campain', ['ngRoute', 'entityService', 'upload', 'ui.bootstrap']);



// Controller
// ----------
campain.controller('CampainListCtrl', ['$scope', 'Entity', '$modal', '$routeParams','services.breadcrumbs', function($scope, Entity, $modal, $routeParams,menu) {

        menu.resetSubmenu();
        menu.addToSubmenu('Hromadné emaily','#/campains', 'glyphicon-th-list');
        menu.addToSubmenu('Nový email','#/campain/new', 'glyphicon-plus');


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

campain.controller('CampainRowsListCtrl', ['$scope', 'Entity', '$modal', '$routeParams', function($scope, Entity, $modal, $routeParams) {

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
campain.controller('CampainEditController', ['$rootScope', '$scope', '$routeParams', '$location', 'Entity', 'uploadService', '$q', function($rootScope, $scope, $routeParams, $location, Entity, uploadService, $q) {


        $scope.previewType = 'pdf';

        $scope.toglePreviewType = function(val) {
            $scope.previewType = val;
        };

        $scope.$watch('previewType', function(newValue, oldValue) {
            if (newValue !== oldValue) {
                $scope.customizeAttachment();
            }


        }, true);
        var campainId = $routeParams.campainId;

        if (undefined === campainId) {
            $scope.Campain = null;
            $scope.errors = {};
            $scope.Campain = new Entity.Campain();
        } else {
            $scope.Campain = Entity.Campain.get({campainId: campainId}
            , function() {
                if ($scope.Campain.dataSourceId !== null) {
                    var dataSourcePromise = Entity.DataSource.get({
                        dataSourceId: $scope.Campain.dataSourceId

                    });
                    dataSourcePromise.$promise.then(function(dataSource) {
                        $scope.datasourceSelected = dataSource;
                        $scope.customizeAttachment();
                    });
                }
            });
        }

        $scope.$watch('campainForm', function(value) {
            // Only act when our property has changed.
            if (undefined !== value) {
                $scope.form = $scope.$eval("campainForm");

            }
        }, true);

        // 'files' is an array of JavaScript 'File' objects.
        $scope.files = [];
        $scope.$watch('UploadFile', function(newValue, oldValue) {
            if (newValue !== undefined) {
                console.log('Controller: $scope.files changed. Start upload.');
                uploadService.send($scope.UploadFile);
            }


        }, true);


        $rootScope.$on('upload:loadstart', function() {
            $scope.loadingfile = true;

        });
        $rootScope.$on('upload:succes', function(event, xhr) {
            $scope.$apply(function() {
                $scope.loadingfile = false;
                $scope.Campain.attachmentFileSystemName = xhr.currentTarget.responseText;
                $scope.Campain.attachmentName = $scope.UploadFile.name;
                $scope.Campain.attachmentFileType = $scope.UploadFile.type;
                $scope.customizeAttachment();
            });
        });
        $rootScope.$on('upload:error', function() {
            console.log('Controller: on `error`');
        });





        $scope.sent = function() {
            $scope.Campain.status = 'READY';
            $scope.ok();
        };
        $scope.ok = function() {
            $scope.Campain.emailText = CKEDITOR.instances.rr.getData();
            var campainPromise = $scope.Campain.$save(
                    function(Campain, headers) {
                        return  handleFormSucces("Kampaň uspěšně uložena", $location, '/campain');
                    }, function(error) {
                return   handleFormError($scope, error.data);

            });
            var contrr = this;
            campainPromise.then(function(campain) {
                contrr.$close();
            });
        };


        $scope.cancel = function() {
            this.$dismiss('cancel');
        };




        $scope.searchDataSources = function(value) {

            var dataSourcesPromise = Entity.DataSource.search({
                search: 'name',
                searchString: value

            });

            return       dataSourcesPromise.$promise.then(function(datasource) {  // this is only run after $http completes
                var datasources = [];
                angular.forEach(datasource, function(item) {
                    datasources.push(item);
                });
                return datasources;

            });

        };

        $scope.getEmailFields = function(value) {
            var emailFields = [];
            angular.forEach($scope.datasourceSelected.dataStructure.dataStructureFields, function(item) {
                if (item.dataType === 'EMAIL') {
                    emailFields.push(item.name);
                }
            });

            return emailFields;
        };


        $scope.onDatasourceSelected = function(datasource) {
            $scope.Campain.dataSourceId = datasource.id;
            $scope.customizeAttachment();

        };


        $scope.addAttachment = function(attachment) {
           var attacmentForm ;
           if(undefined===attachment || null===attachment) {
               
               attachment = {};
           }else {
               attacmentForm = attachment;
               
           }
            modalInstance = $modal.open({
                windowClass: 'modal-campain',
                templateUrl: 'views/campain/attachment.html',
                resolve: {
                    attachment: function() {
                        return attacmentForm;
                        
                    }
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



// Controller
// ----------
dataSource.controller('CampainAttachmentController', ['$scope', function($scope, attachment) {
  $scope.attachment = attachment;





        $scope.customizeAttachment = function customizeAttachment() {
            if ($scope.Campain.attachmentFileSystemName !== undefined && $scope.Campain.attachmentFileSystemName !== null) {
                var path = '../template/preview/' + $scope.previewType;
                if ($scope.Campain.customizeAttachments) {
                    var TemplateFieldsPromise = Entity.TemplateFields.get({fileId: $scope.Campain.attachmentFileSystemName});
                    TemplateFieldsPromise.$promise.then(function(result) {
                        $scope.templateFields = result.placeHolders;
                    });


                }



            }

  $scope.previewUrl = ''; 

 var query =  '';
 if(undefined!==$scope.Campain.customizeAttachments && $scope.Campain.customizeAttachments === true) {
     
  query = query +  '?datasourceId=' + encodeURIComponent($scope.Campain.dataSourceId) + '&fileId=' + encodeURIComponent($scope.Campain.attachmentFileSystemName);
     
 }else {
 query = query +  '?fileId=' + encodeURIComponent($scope.Campain.attachmentFileSystemName);
     
 }
                  
                 
            
                       $scope.previewUrl = path + query;

        };

       






        $scope.cancel = function() {
            this.$dismiss('cancel');
        };

    }]);
