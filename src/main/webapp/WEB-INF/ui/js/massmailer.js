

'use strict';

var appMassMailer = angular.module('appMassMailer', ['ngRoute', 'ngResource','directives', 'entityService' ,'users','dataSource']);


angular.module('appMassMailer') .config(
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
                    controller: 'DataSourceEditCtrl'
                }).
                otherwise({
                    redirectTo: 'views/user/list.html'
                });
    }]);





function handleFormError($scope, data) {
    $scope.errors = {};
    toastr.error("Zkontrolujte formulář");

    return angular.forEach(data, function(serverError, key) {

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
      priority: 10000,
      restrict: 'AC',
      controller: function($scope, $element, $attrs) {
        $attrs.$set('name', $interpolate($attrs.interpolModel || $attrs.name)($scope));
        
        $element.data('$interpolModelController', null);
      }
    };
  }) ;
  
  appMassMailer.directive('interpolShow', function($interpolate) {
    return {
      priority: 10000,
      restrict: 'AC',
      controller: function($scope, $element, $attrs) {
       
        var ds =  $scope.$eval();
       
        $element.data('$interpolModelController', null);
      }
    };
  }) ;