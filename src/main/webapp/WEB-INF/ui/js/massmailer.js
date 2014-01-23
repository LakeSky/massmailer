

'use strict';

var appMassMailer = angular.module('appMassMailer', ['ngRoute', 'ngResource', 'directives', 'entityService', 'users', 'dataSource']);


angular.module('appMassMailer').config(
        ['$routeProvider',
            function($routeProvider) {
                $routeProvider.
                        when('/users', {
                            templateUrl: 'views/user/list.html',
                            controller: 'UserListCtrl'
                        }).
                        when('/users/new', {
                            templateUrl: 'views/user/edit.html',
                            controller: 'UserCreateController'
                        }).
                        when('/users/:userId', {
                            templateUrl: 'views/user/edit.html',
                            controller: 'UserEditCtrl'
                        }).
                        when('/file/new', {
                            templateUrl: 'views/file/edit.html'

                        }).
                        when('/datasource', {
                            templateUrl: 'views/datasource/list.html',
                            controller: 'DataSourceListCtrl'

                        }).
                        when('/datasource/new', {
                            templateUrl: 'views/datasource/edit.html',
                            controller: 'DataSourceCreateController'

                        }).
                        when('/datasource/:dataSourceId', {
                            templateUrl: 'views/datasource/edit.html',
                            controller: 'DataSourceCreateController'
                        }).
                        otherwise({
                            redirectTo: 'views/user/list.html'
                        });
            }]);





function handleFormError($scope, data) {
    $scope.errors = {};
    toastr.error("Zkontrolujte formulář");

    return angular.forEach(data, function(serverError, key) {
        console.log(serverError);
        $scope.form[serverError['field']].$setValidity('server', false);
        $scope.errors[serverError['field']] = serverError['code'];

    });



}

function handleFormSucces(message, $location, path) {

    toastr.success(message);
    $location.path(path);
}



appMassMailer.directive('interpolModel', function($interpolate) {
    return {
        priority: 0,
        restrict: 'AC',
        controller: function($scope, $element, $attrs) {
            $attrs.$set('name', $interpolate($attrs.interpolModel || $attrs.name)($scope));
            $element.data('$interpolModelController', null);
        }
    };
});



appMassMailer.directive('showInter', ['$interpolate', '$animate', function($interpolate, $animate) {
        return {
            priority: 0,
            restrict: 'AC',
            controller: function($scope, $element, $attrs) {
                if (undefined === $scope.form) {
                    var unregister = $scope.$watch('form', function(newValue) {
                        // Only act when our property has changed.
                        if (undefined !== newValue) {
                            procces();
                            unregister();
                        }

                    });

                } else {
                    procces();

                }

                function procces() {
                    console.log('Controller: $scope.form created');
                    var fna = $scope.form[$interpolate($attrs.showInter)($scope)];

                    $scope.$watch(function() {
                        return fna.$invalid;
                    }
                    , function ngShowWatchAction(value, newValue) {
                        $animate[value ? 'removeClass' : 'addClass']($element, 'ng-hide');
                    });
                }
            }
        };




    }]);


appMassMailer.directive('hideInter', ['$interpolate', '$animate', function($interpolate, $animate) {
        return {
            priority: 0,
            restrict: 'AC',
            controller: function($scope, $element, $attrs) {
                var fna = $scope.form[$interpolate($attrs.showInter)($scope)];
                $scope.$watch(function() {
                    return fna.$invalid;
                }
                , function ngShowWatchAction(value, newValue) {
                    $animate[value ? 'removeClass' : 'addClass']($element, 'ng-hide');
                });

            }
        };
    }]);



appMassMailer.directive('serverError', function($interpolate) {

    return {
        priority: 0,
        restrict: 'AC',
        controller: function($scope, $element, $attrs) {
            if (undefined === $scope.errors) {
                var unregister = $scope.$watch('form', function(newValue) {
                    // Only act when our property has changed.
                    if (undefined !== newValue) {
                        procces($element);
                        unregister();
                    }

                });

            } else {
                procces($element);

            }

            function procces($element) {
                console.log('Controller: $scope.form created');
                $element.bind('change', function(event) {
                    var fna = $scope.form[$interpolate($attrs.serverError)($scope)];
                    fna.$setValidity('server', true);
                });
            }
        }
    };











});



appMassMailer.directive('errorMessage', function($interpolate) {
    return {
        priority: 0,
        restrict: 'E',
        controller: function($scope, $element, $attrs) {
            if (undefined === $scope.errors) {
                var unregister = $scope.$watch('errors', function(newValue) {
                    // Only act when our property has changed.
                    if (undefined !== newValue) {
                      procces();

                    }

                });

            } else {
                procces();

            }

            function procces() {
              $scope.errorCode = $scope.errors[$interpolate($attrs.error)($scope)];
               
            }
        }
        ,
            template: '{{errorCode}}'
    };


});