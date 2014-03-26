
// Module
// ------
var upload = angular.module('upload', []);



dataSource.directive('fileChange', function() {
     
    var linker = function($scope, element, attributes) {
        // onChange, push the files to $scope.files.
        element.bind('change', function(event) {
            var selectedFile = event.target.files[0];
            $scope.$parent.$apply(function() {
             $scope.$parent.UploadFile = selectedFile;
            });
        });
    };

    return {
        restrict: 'A',
        link: linker
    };

});

// Factory
// -------
upload.factory('uploadService', ['$rootScope', function($rootScope) {

        return {
            send: function(file) {
                var data = new FormData(),
             xhr = new XMLHttpRequest();


                xhr.onloadstart = function() {
                    console.log('Factory: upload started: ', file.name);
                    $rootScope.$emit('upload:loadstart', xhr);
                };

                // When the request has failed.
                xhr.onerror = function(e) {
                    $rootScope.$emit('upload:error', e);
                };
                xhr.onreadystatechange = function(e)
                {

                    if (xhr.readyState === 4 && xhr.status === 201)
                    {
                        $rootScope.$emit('upload:succes',e, xhr);


                    }
                };

                // Send to server, where we can then access it with $_FILES['file].
                data.append('file', file, file.name);
       
                xhr.open('POST', '../files');
        
                xhr.send(data);
                

            }
        };

    }]);


// Controller
// ----------
upload.controller('UploadCtrl', ['$scope', '$rootScope', 'uploadService', function($scope, $rootScope, uploadService) {

        // 'files' is an array of JavaScript 'File' objects.
        $scope.files = [];

        $scope.$watch('UploadFile', function(newValue, oldValue) {
            // Only act when our property has changed.
            if (newValue !== oldValue) {
                console.log('Controller: $scope.files changed. Start upload.');

                uploadService.send($scope.UploadFile);

            }
        }, true);






    }]);