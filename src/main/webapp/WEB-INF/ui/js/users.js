/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var users = angular.module('users', ['entityService']);


// Controller
// ----------
users.controller('UserListCtrl', ['$scope', 'Entity', function($scope, Entity) {
   
    $scope.users = Entity.User.query();

    }]);



// Controller
// ----------
users.controller('UserEditCtrl', ['$scope', '$routeParams', '$location' , 'Entity', function($scope, $routeParams, $location, Entity) {
   
    var userId = $routeParams.userId;
    $scope.User = Entity.User.get({userId: userId});
    $scope.save = function() {
        $scope.form = $scope.$eval(User.prototype.formName());

        $scope.User.$save(
                function(Entity, headers) {
                    handleFormSucces("Nový uživatel vytvořen", $location, '/users');
                }, function(error) {
            handleFormError($scope, error.data);

        });
    };

    }]);



// Controller
// ----------
users.controller('UserCreateController', ['$scope', '$routeParams', '$location' , 'Entity', function($scope, $routeParams, $location, Entity) {
   
    $scope.User = new Entity.User();
    $scope.save = function() {
        $scope.form = "saveUser"
        $scope.User.$save(
                function(User, headers) {
                    handleFormSucces("Nový uživatel vytvořen", $location, '/users');
                }, function(error) {
                   
            handleFormError($scope, error.data);

        });
    };

    }]);


