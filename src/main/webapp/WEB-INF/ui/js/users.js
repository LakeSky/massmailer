/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var users = angular.module('users', ['entityService']);


// Controller
// ----------

users.controller('LoginCtrl', ['$scope', '$rootScope', '$location', '$cookieStore', 'loginService', function($scope, $rootScope, $location, $cookieStore, loginService) {

        $scope.login = function() {
            loginService.authenticate($.param({username: $scope.username, password: $scope.password}), function(authenticationResult) {
                var authToken = authenticationResult.token;
                $rootScope.authToken = authToken;
                if(authToken!==undefined) {
                     $rootScope.isLogedIn = true;
                    
                }else {
                 $rootScope.isLogedIn = false;
                    
                }

                $cookieStore.put('authToken', authToken);


            });
        };

    }]);

users.controller('UserListCtrl', ['$scope', 'Entity', function($scope, Entity) {

        $scope.users = Entity.User.query();

    }]);



// Controller
// ----------
users.controller('UserEditCtrl', ['$scope', '$routeParams', '$location', 'Entity', function($scope, $routeParams, $location, Entity) {

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
users.controller('UserCreateController', ['$scope', '$routeParams', '$location', 'Entity', function($scope, $routeParams, $location, Entity) {

        $scope.User = new Entity.User();
        $scope.save = function() {
            $scope.form = $scope.$eval("saveUser");

            $scope.User.$save(
                    function(User, headers) {
                        handleFormSucces("Nový uživatel vytvořen", $location, '/users');
                    }, function(error) {

                handleFormError($scope, error.data);

            });
        };

    }]);


