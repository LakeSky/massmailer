/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
                    templateUrl: 'views/datasource/list.html'

                }).
                when('/datasource/:dataSourceId', {
                    templateUrl: 'views/datasource/edit.html',
                    controller: 'DataSourceEditCtrl'
                }).
                when('/datasource/new', {
                    templateUrl: 'views/datasource/edit.html',
                     controller: 'DataSourceCreateCtrl'

                }).
                otherwise({
                    redirectTo: 'views/user/list.html'
                });
    }]);
