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

            return  '<div class="panel"><h3>Název: {{attachment.attachmentOutputName}}</h3><p>Personalizovat: {{attachment.customizeAttachments}}</p></div><embed  embed-src="{{previewUrl}}&emailId={{current}}"  src="{{previewUrl}}"  type="application/pdf" width="100%";    height="400px" /></div>';

        } else {
            return  '<div class="panel"><h3>Název: {{attachment.attachmentOutputName}}</h3><p>Personalizovat: {{attachment.customizeAttachments}}</p></div><div class="well">Upozornění níže zobrazená příloha byla pevedena do formátu pdf. Příloha bude odeslána ve formátu {{attachment.attachmentOutputType}}</div>\n\
<embed embed-src="{{previewUrl}}&emailId={{current}}"  src="{{previewUrl}}"  type="application/pdf" width="100%";  height="400px" />';
        }
    };
    var linkFunction = function(scope, element, attrs) {
        element.html(getTemplate(scope.attachment.attachmentOutputType)).show();
        $compile(element.contents())(scope);
    };
    return {
        restrict: 'E',
        rep1ace: true,
        scope: {
            attachment: '=',
            campain: '=',
            current: '='
        },
        link: linkFunction,
        controller: function($scope) {

            $scope.previewUrl = '../template/preview/pdf/' + '?fileId=' + encodeURIComponent($scope.attachment.attachmentFileSystemName);
            if ($scope.attachment.customizeAttachments === true) {
                $scope.previewUrl =+ '&datasourceId=' + encodeURIComponent($scope.campain.dataSourceId);

            }
            ;

        }
    };
});

directives.directive('ckEditor', function() {
  return {
    require: '?ngModel',
    link: function(scope, elm, attr, ngModel) {
      var ck = CKEDITOR.replace(elm[0]);

      if (!ngModel) return;

      ck.on('pasteState', function() {
        scope.$apply(function() {
          ngModel.$setViewValue(ck.getData());
        });
      });

      ngModel.$render = function(value) {
        ck.setData(ngModel.$viewValue);
      };
    }
  };
});

directives.directive('springValid', function() {
    return {
              link: function(scope, element, attr) {
                   var form = element.inheritedData('$formController');
                    // no need to validate if form doesn't exists
                    if (!form) return;
                    // validation model
                    var validate = attr.springValid;
                    // watch validate changes to display validation
                    scope.$watch(validate, function(errors) {

                        // every server validation should reset others
                        // note that this is form level and NOT field level validation
                        form.$serverError = { };

                        // if errors is undefined or null just set invalid to false and return
                        if (!errors) {
                            form.$serverInvalid = false;
                            return;
                        }
                        // set $serverInvalid to true|false
                        form.$serverInvalid = (errors.length > 0);

                        // loop through errors
                        angular.forEach(errors, function(error, i) {                            
                                form.$serverError[error.key] = { $invalid: true, message: error.value };
                        });
                    });
                  
              }
        
        
    };
    
    
});