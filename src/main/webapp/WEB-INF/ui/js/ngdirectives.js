/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var directives = angular.module('directives', []);
directives.directive('serverError', function() {
    return {
        restrict: 'A',
        require: '?ngModel',
        link: function(scope, element, attrs, ctrl) {
            return element.on('change', function() {
                return scope.$apply(function() {
                    return ctrl.$setValidity('server', true);
                });
            });
        }
    };
});
directives.directive('emailAttachment', function($compile) {
    var getTemplate = function(type) {
        if (type === 'pdf') {

            return  '{{current}}<embed class="col-lg-7" embed-src="{{previewUrl}}&emailId={{current}}"  src="{{previewUrl}}"  type="application/pdf"   height="400px" />';

        } else {
            return  '{{current}}<embed class="col-lg-7" embed-src="{{previewUrl}}&emailId={{current}}"  src="{{previewUrl}}"  type="application/pdf"   height="400px" />';
        }
    };
    var linkFunction = function(scope, element, attrs) {
        element.html(getTemplate(scope.attachment.attachmentFileType)).show();
        $compile(element.contents())(scope);
    };
    return {
        restrict: 'E',
        rep1ace: true,
        scope: {
            attachment: '=',
            campain: '=',
            current:'='
        },
        link: linkFunction,
        controller: function($scope) {
           
            $scope.previewUrl = '../template/preview/pdf/' + '?datasourceId=' + encodeURIComponent($scope.campain.dataSourceId) + '&fileId=' + encodeURIComponent($scope.attachment.attachmentFileSystemName) ;
            ;
            
        }
    };
});