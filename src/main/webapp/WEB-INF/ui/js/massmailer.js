

'use strict';

var appMassMailer = angular.module('appMassMailer', ['ngRoute', 'ngResource', 'directives', 'entityService', 'menu', 'dataSource', 'campain','email','dashboard','stats']);


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
                        when('/datasource/edit/:dataSourceId', {
                            templateUrl: 'views/datasource/edit.html',
                            controller: 'DataSourceCreateController'

                        }).
                        when('/datasource/data/:dataSourceId', {
                            templateUrl: 'views/datasource/browse.html',
                            controller: 'DataSourceRowsListCtrl'

                        }).
                        when('/datasource/:dataSourceId', {
                            templateUrl: 'views/datasource/edit.html',
                            controller: 'DataSourceCreateController'
                        }).
                        when('/campains/dashboard', {
                            templateUrl: 'views/campain/dashboard.html',
                            controller: 'DashBoardCtrl'

                        }).when('/campains/type/:type', {
                            templateUrl: 'views/campain/list.html',
                            controller: 'CampainListCtrl'

                        }).
                        when('/campain/new', {
                            templateUrl: 'views/campain/edit.html',
                            controller: 'CampainEditController'

                        }).
                        when('/campain/:campainId', {
                            templateUrl: 'views/campain/edit.html',
                            controller: 'CampainEditController'
                        }).
                        when('/campain/preview/:campainId', {
                            templateUrl: 'views/campain/preview.html',
                            controller: 'CampainPreviewController'
                        }).
                        when('/stats', {
                            templateUrl: 'views/campain/stats.html',
                            controller: 'StatsCtrl'
                        }).
                        otherwise({
                            templateUrl: 'views/dashboard/dashboard.html' ,
                            controller: 'DashBoardCtrl'
                        });
            }]);






function handleFormError($scope, data) {
    $scope.errors = {};
    toastr.error("Zkontrolujte formulář");

    angular.forEach(data, function(serverError, key) {
        console.log(serverError);
        $scope.form[serverError['field']].$setValidity('server', false);
        $scope.errors[serverError['field']] = serverError['code'];

    });

    return false;



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
                    if (fna === undefined) {

                        $scope.$watch('form[' + $interpolate($attrs.showInter)($scope) + ']', function(value) {
                            fna = $scope.form[$interpolate($attrs.showInter)($scope)];
                            $scope.$watch(function() {
                                return fna.$invalid;
                            }
                            , function ngShowWatchAction(value, newValue) {
                                $animate[value ? 'removeClass' : 'addClass']($element, 'ng-hide');
                            });
                        });

                    } else {
                        $scope.$watch(function() {
                            return fna.$invalid;
                        }
                        , function ngShowWatchAction(value, newValue) {
                            $animate[value ? 'removeClass' : 'addClass']($element, 'ng-hide');
                        });

                    }




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

appMassMailer.directive('serverValidated', ['$interpolate', '$animate', function($interpolate, $animate) {
        return {
            restrict: 'A',
            scope: true,
            require: '?ngModel',
            link: function(scope, element, attrs, ctrl) {
                if (attrs.ngModel) {
                    var unregister = scope.$watch('hasErrors', function(newValue) {
                        // Only act when our property has changed.
                        if (undefined !== scope.errors[$interpolate(attrs.id)(scope)]) {
                            scope.form[$interpolate(attrs.id)(scope)].$setValidity('server', false); // <<< or whatever your error key is

                        }
                        element.bind('change', function(event) {
                            $ctrl.$setValidity('server', true);
                        });


                    });


                }
            }
        };
    }]);


appMassMailer.directive('serverError', function($interpolate) {

    return {
        priority: 0,
        restrict: 'AC',
        link: function(scope, element, attrs, ctrl) {
            if (attrs.ngModel) {
                var unregister = scope.$watch('hasErrors', function(newValue) {
                    // Only act when our property has changed.
                    if (undefined !== scope.errors && undefined !== scope.errors[$interpolate(attrs.serverError)(scope)]) {
                        element.bind('change', function(event) {
                            $ctrl.$setValidity('server', true);
                        });

                    }



                });


            }
        }
    };











});



appMassMailer.directive('errorMessage', function($interpolate) {
    return {
        priority: 0,
        restrict: 'E',
        scope: true,
        controller: function($scope, $element, $attrs) {
            if (undefined === $scope.form) {
                var unregister = $scope.$watch('form', function(newValue) {
                    // Only act when our property has changed.
                    if (undefined !== newValue) {
                        procces();

                    }

                });

            } else {
                procces();

            }

            function procces() {
                console.log('Controller: $scope.form created');
                var fna = $scope.form[$interpolate($attrs.error)($scope)];
                if (fna === undefined) {

                    $scope.$watch('errors', function(value) {
                        if (undefined !== value) {
                            fna = $scope.errors[$interpolate($attrs.error)($scope)];
                            if (fna !== undefined) {

                                $scope.errorCode = fna;
                            }
                        }
                    });

                } else {
                    $scope.$watch('errors', function(value) {
                        if (value !== undefined) {
                            fna = $scope.errors[$interpolate($attrs.error)($scope)];
                            $scope.errorCode = fna;

                        }

                    });



                }




            }
        }
        ,
        template: '{{errorCode}}'
    };


});


appMassMailer.directive('onBlurChange', function($parse) {
    return function(scope, element, attr) {

        var fn = $parse(attr['onBlurChange']);

        var hasChanged = false;
        element.on('change', function(event) {
            hasChanged = true;
        });

        element.on('blur', function(event) {
            if (hasChanged) {
                scope.$apply(function() {
                    fn(scope, {$event: event});
                });
                hasChanged = false;
            }
        });
    };
});

appMassMailer.directive('onEnterBlur', function() {
    return function(scope, element, attrs) {
        element.bind("keydown keypress", function(event) {
            if (event.which === 13) {
                element.blur();
                event.preventDefault();
            }
        });
    };
});

appMassMailer.filter('format', function() {
    return function(value, format) {

        if (format === 'DATE') {
            return new Date(value);
        } else {
            return    value;

        }

    };
});

appMassMailer.directive('embedSrc', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var current = element;
            scope.$watch(function() {
                return attrs.embedSrc;
            }, function() {
                var clone = element
                        .clone()
                        .attr('src', attrs.embedSrc);
                current.replaceWith(clone);
                current = clone;
            });
        }
    };
});