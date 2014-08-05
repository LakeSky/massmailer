/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var campain = angular.module('campain', ['ngRoute', 'entityService', 'upload', 'ui.bootstrap']);



// Controller
// ----------
campain.controller('CampainListCtrl', ['$scope', 'Entity', '$modal', '$routeParams', '$location', function($scope, Entity, $modal, $routeParams, $location) {
        $scope.ctype = $routeParams.type;





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
        var modalInstance;

        $scope.openDeleteDialog = function(campainsId, name) {

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

        $scope.send = function(campain) {
            $location.path('/campain/preview/' + campain.id);

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
campain.controller('CampainEditController', ['$rootScope', '$scope', '$routeParams', '$location', 'Entity', 'uploadService', '$q', '$modal', function($rootScope, $scope, $routeParams, $location, Entity, uploadService, $q, $modal) {




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
            $scope.Campain.id = -1;

        } else {
            $scope.Campain = Entity.Campain.get({campainId: campainId}
            , function() {
                if ($scope.Campain.dataSourceId !== null) {
                    var dataSourcePromise = Entity.DataSource.get({
                        dataSourceId: $scope.Campain.dataSourceId

                    });
                    dataSourcePromise.$promise.then(function(dataSource) {
                        $scope.datasourceSelected = dataSource;
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







        $scope.sent = function() {

      
            angular.forEach($scope.Campain.campainAttachments, function(atta) {
                delete atta.preview;

            });

            var campainPromise = $scope.Campain.$save(
                    function(Campain, headers) {

                        return  handleFormSucces("Kampaň uspěšně uložena", $location, '/campain/preview/' + Campain.id);

                    }, function(error) {
                return   handleFormError($scope, error.data);

            });



        };
        $scope.ok = function(redirect) {

            angular.forEach($scope.Campain.campainAttachments, function(atta) {
                delete atta.preview;

            });

            var campainPromise = $scope.Campain.$save(
                    function(Campain, headers) {
                        if (redirect == undefined) {
                            return  handleFormSucces("Kampaň uspěšně uložena", $location, '/campains/type/EDIT');
                        } else {

                            return  handleFormSucces("Kampaň uspěšně uložena", $location, redirect);
                        }
                    }, function(error) {
                return   handleFormError($scope, error.data);

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


        };

        $scope.removeAttachment = function(att) {
            $scope.Campain.campainAttachments.splice(att.index, 1);

        };
        $scope.addAttachment = function(att) {
            var index = 0;
            var atObject = null;
            if (undefined === $scope.Campain.campainAttachments) {
                $scope.Campain.campainAttachments = [];
                index = 0;
            } else {
                index = $scope.Campain.campainAttachments.length;

            }
            if (undefined === att || null === att) {
                atObject = {
                    index: index

                };


            } else {
                $scope.form['campainAttachments[' + att.index + '].index'].$setValidity('server', true);
                atObject = att;


            }
            var dataStructureFields;
            if (undefined === $scope.datasourceSelected) {
                dataStructureFields = {};

            } else {
                dataStructureFields = $scope.datasourceSelected.dataStructure.dataStructureFields;

            }

            var attachmentForm = {
                attachment: atObject,
                datasourceId: $scope.Campain.dataSourceId,
                dataStructureFields: dataStructureFields
            };

            modalInstance = $modal.open({
                windowClass: 'modal-attachment',
                templateUrl: 'views/campain/attachment.html',
                controller: 'CampainAttachmentController',
                resolve: {
                    attachments: function() {
                        return attachmentForm;

                    }
                }
            });

            modalInstance.result.then(function(atta) {



                $scope.Campain.campainAttachments[atta.attachment.index] = atta.attachment;




            }, function() {
                ;
            });
        };


        $scope.attachmentPreview = function(atta) {

            if (atta.customizeAttachments) {

                atta.preview = '../template/preview/pdf/' + '?datasourceId=' + encodeURIComponent($scope.datasourceSelected.id) + '&fileId=' + encodeURIComponent(atta.attachmentFileSystemName) + '&emailId=1';
            } else {

                atta.preview = '../template/preview/pdf/' + '?fileId=' + encodeURIComponent(atta.attachmentFileSystemName);

            }




        };






    }]);













// Controller
// ----------
campain.controller('CampainAttachmentController', ['$rootScope', '$scope', 'Entity', 'uploadService', '$modalInstance', 'attachments', function($rootScope, $scope, Entity, uploadService, $modalInstance, attachments) {

        $scope.outputFormats = [
            {name: 'Word 97 (doc)', value: 'doc'},
            {name: 'Word 2007 (docx)', value: 'docx'},
            {name: 'PDF', value: 'pdf'}
        ];

        $scope.attachment = attachments.attachment;
        $scope.form = {};
        $scope.form.attachmentFileSystemName = attachments.attachment.attachmentFileSystemName;
        $scope.form.attachmentName = attachments.attachment.attachmentName;
        $scope.form.attachmentOutputName = attachments.attachment.attachmentOutputName;
        $scope.form.attachmentFileType = (undefined === attachments.attachment.attachmentName) ? '' : attachments.attachment.attachmentName.substring(attachments.attachment.attachmentName.lastIndexOf("."));
        $scope.form.attachmentOutputType = (attachments.attachment.attachmentOutputType === undefined) ? '' : attachments.attachment.attachmentOutputType;
        $scope.form.customizeAttachments = (attachments.attachment.customizeAttachments === undefined) ? false : attachments.attachment.customizeAttachments;
        $scope.dataSourceId = attachments.datasourceId;
        $scope.dataStructureFields = attachments.dataStructureFields;
        $scope.previewType = 'pdf';

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
        $rootScope.$on('upload:succes', function(event, xhr, req, name, type) {
            $rootScope.$apply(function() {
                $scope.loadingfile = false;
                $scope.form.attachmentFileSystemName = xhr.currentTarget.responseText;
                $scope.form.attachmentName = name;
                $scope.form.attachmentFileType = name.substring(name.lastIndexOf(".") + 1);
                $scope.form.UploadFile = undefined;
                $scope.files = [];
                $scope.form.attachmentOutputName = name;

                if (undefined === $scope.attachmentOutputType || '' === $scope.attachmentOutputType) {
                    $scope.form.attachmentOutputType = name.substring(name.lastIndexOf(".") + 1);

                }

            });
        });


        $rootScope.$on('upload:error', function() {
            console.log('Controller: on `error`');
        });


        $scope.customizeAttachment = function customizeAttachment() {
            if ($scope.attachment.attachmentFileSystemName !== undefined && $scope.attachment.attachmentFileSystemName !== null) {
                var path = '../template/preview/' + $scope.previewType;
                if ($scope.attachment.customizeAttachments) {
                    var TemplateFieldsPromise = Entity.TemplateFields.get({fileId: $scope.attachment.attachmentFileSystemName});
                    TemplateFieldsPromise.$promise.then(function(result) {
                        $scope.templateFields = result.placeHolders;
                    });
                }

            }
        };


        $scope.convert = function() {
            var value = $scope.form.attachmentOutputType;
            if (undefined !== $scope.form.attachmentOutputName) {
                var originaExt = $scope.form.attachmentOutputName.substring($scope.form.attachmentOutputName.lastIndexOf(".") + 1);
                if (originaExt !== value) {
                    var outputName = $scope.form.attachmentOutputName.substring(0, $scope.form.attachmentOutputName.lastIndexOf("."));

                    $scope.form.attachmentOutputName = outputName + "." + value;
                }


            }

        };





        $scope.ok = function() {
            attachments.attachment = $scope.attachment;
            attachments.attachment.attachmentFileSystemName = $scope.form.attachmentFileSystemName;
            attachments.attachment.attachmentName = $scope.form.attachmentName;
            attachments.attachment.attachmentFileType = $scope.form.attachmentFileType;
            attachments.attachment.attachmentOutputName = $scope.form.attachmentOutputName;
            attachments.attachment.attachmentOutputType = $scope.form.attachmentOutputType;
            attachments.attachment.customizeAttachments = $scope.form.customizeAttachments;
            $modalInstance.close({
                attachment: attachments.attachment

            });
        };



        $scope.cancel = function() {
            this.$dismiss('cancel');
        };

    }]);
// Controller
// ----------
campain.controller('CampainDeleteController', ['$scope', '$routeParams', '$location', 'Entity', '$q', function($scope, $routeParams, $location, Entity, $q) {
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
campain.controller('CampainPreviewController', ['$scope', '$routeParams', '$location', 'Entity', '$http', function($scope, $routeParams, $location, Entity, $http) {
        var campainId = $routeParams.campainId;

        var Campain = Entity.Campain.get({campainId: campainId}, function() {
            $scope.Campain = Campain;
            var DataSource = Entity.DataSource.get({dataSourceId: Campain.dataSourceId}, function() {
                $scope.dataSource = DataSource;
            });
        });






        $scope.send = function() {
            $http.post('../campain/send', {id: Campain.id}).success(function(response) {

            });


        };







        $scope.cancel = function() {
            this.$dismiss('cancel');
        };

    }]);