

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
                when('/datasource/:dataSourceId', {
                    templateUrl: 'views/datasource/edit.html',
                    controller: 'DataSourceEditCtrl'
                }).
                when('/datasource/new', {
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
        $scope.form[serverError['field']].$setValidity('server', false);
        $scope.errors[serverError['field']] = serverError['code'];

    });



}

function handleFormSucces(message, $location, path) {

    toastr.success(message);
    $location.path(path);
}



      