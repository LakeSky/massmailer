

'use strict';

var appMassMailer = angular.module('appMassMailer', ['ngRoute', 'ngResource', 'appMassMailer.services']);



angular.module('appMassMailer.services', ['ngResource']).
        factory('User', function($resource) {
            var User = $resource('../users/:userId', {userId: '@id'}, {
      
                
            });

            User.prototype.isNew = function() {
                return (typeof (this.id) === 'undefined')
            };
            User.prototype.formName = function() {
                return 'saveUser';
            };
            return User;
        });



appMassMailer.config(['$routeProvider',
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
                otherwise({
                    redirectTo: 'views/user/list.html'
                });
    }]);

angular.module('appMassMailer').directive('serverError', function() {
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


function UserListCtrl($scope, User) {
    $scope.users = User.query();

}


function UserEditCtrl($scope, $routeParams, $location, User) {
    var userId = $routeParams.userId;
    $scope.User = User.get({userId: userId});
    $scope.save = function() {
        $scope.form = $scope.$eval(User.prototype.formName());
      
        $scope.User.$save(
                function(User, headers) {
                    handleFormSucces("Nový uživatel vytvořen", $location, '/users');
                }, function(error) {
            handleFormError($scope, error.data);

        });
    };

}

function UserCreateController($scope, $routeParams, $location, User) {
    $scope.User = new User();
    $scope.save = function() {
        $scope.form = $scope.$eval(User.prototype.formName());

        $scope.User.$save(
                function(User, headers) {
                    handleFormSucces("Nový uživatel vytvořen", $location, '/users');
                }, function(error) {
            handleFormError($scope, error.data);

        });
    };
}

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




function addSpringFieldsErrorsErrors($scope) {


}