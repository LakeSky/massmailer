/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var users = angular.module('users', ['massmaller.service']);


// Controller
// ----------

users.controller('LoginCtrl', ['$scope', '$rootScope', '$location', '$cookieStore', 'Entity', 'loginService', 'authNotifier', 'requestBuffer', '$http', function($scope, $rootScope, $location, $cookieStore, Entity, loginService, authNotifier, requestBuffer, $http) {


        var resendAll = function() {

            angular.forEach(requestBuffer.getAll(), function(req) {
                $http(req.config).then(function(response) {
                    req.deferred.resolve(response);
                });
            });

            requestBuffer.clear();

        };

        $scope.login = function() {
            $scope.loginerroroccurred = false;
            loginService.authenticate($.param({username: $scope.username, password: $scope.userpassword}), function(authenticationResult) {
                var authToken = authenticationResult.token;
                $rootScope.authToken = authToken;
                if (authToken !== undefined) {
                    $rootScope.isLogedIn = true;
                    Entity.User.get({userId: authenticationResult.userId})
                            .$promise.then(function(user) {
                                $scope.user = user;
                            });
                     
                    authNotifier.notifyConfirmed();
                    resendAll();
                } else {

                    $rootScope.isLogedIn = false;

                }

                $cookieStore.put('authToken', authToken);


            }, function(error) {
                $scope.loginerroroccurred = true;
                $scope.loginerror = "Přihlášení selhalo, zkontrolujte vaše přihlašovací údaje";

            });
        };
        $scope.logout = function() {
            delete  $rootScope.authToken;
            $rootScope.isLogedIn = false;

            $cookieStore.remove('authToken');
            authNotifier.notifyRequired();


            $cookieStore.remove('authToken');

        };

    }]);

users.controller('UserListCtrl', ['$scope', 'Entity', function($scope, Entity) {

        $scope.currentPage = 0;
        $scope.filterParams = {
            page: 0,
            limit: 10,
            sort: 'id',
            sortDir: 1,
            search: '0',
            searchString: ''


        };
        function getData() {
            var users = Entity.User.browse({
                page: $scope.filterParams.page,
                limit: $scope.filterParams.limit,
                sort: $scope.filterParams.sort,
                sortDir: $scope.filterParams.sortDir,
                searchString: $scope.filterParams.searchString
            }, function() {
                $scope.users = users;
            });
        }

        $scope.search = function(searchField) {
            $scope.filterParams.searchString = searchField;
            $scope.filterParams.page = 0;
            getData();

        };
        $scope.paginate = function(value) {
            // Only act when our property has changed.
            if (undefined !== value) {
                $scope.filterParams.page = value;
                getData();

            }
        };

        getData();

    }]);



// Controller
// ----------
users.controller('UserEditCtrl', ['$scope', '$routeParams', '$location', 'Entity', function($scope, $routeParams, $location, Entity) {

        var userId = $routeParams.userId;
        if (userId === "new") {
            $scope.user = new Entity.User();
            $scope.login = new Entity.Login();
            $scope.newuser = true;

        } else
        {
            var user = Entity.User.get({userId: userId})
                    .$promise.then(function(user) {
                        $scope.user = user;
                    });

            var login = Entity.Login.get({userId: userId})
                    .$promise.then(function(login) {
                        $scope.login = login;
                    });

            $scope.newuser = false;
        }
        var availableRoles = Entity.Role.query()
                .$promise.then(function(availableRoles) {
                    $scope.availableRoles = availableRoles;
                });




        $scope.userRoles = "";

        $scope.addRole = function(role) {
            role.isDisabled = true;
            if($scope.login.roles ===null) {
                  $scope.login.roles = [];
            }
            $scope.login.roles.push(role.authority);
            

        };


        $scope.removeRole = function(roleName) {



            angular.forEach($scope.login.roles, function(item, index) {
                if (roleName === item) {
                    $scope.login.roles.splice(index, 1);

                }

            });

            angular.forEach($scope.availableRoles, function(item, index) {
                if (roleName === item.authority) {
                    item.isDisabled = false;


                }

            });





        };

        $scope.saveUser = function() {

            $scope.user.$save(
                    function(user, headers) {
                        $scope.login.userId = user.id;
                        $scope.newuser = false;
                    }, function(error) {


                handleFormError($scope, error.data);

            });
        };

        $scope.saveLogin = function() {
            if ($scope.login.userId === undefined) {



            } else {

                $scope.login.$save(
                        function(Entity, headers) {

                        }, function(error) {
              
              $scope['loginErrors'] = [];
                    handleFormErrorNew($scope['loginErrors'],$scope, error.data);

                });
            }

        };



    }]);






