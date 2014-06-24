/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var email = angular.module('email', ['ngRoute', 'entityService', 'ui.bootstrap']);

// Controller
// ----------
email.controller('EmailPreviewCtrl', ['$scope', 'Entity', '$routeParams','$sce', function($scope, Entity, $routeParams,$sce) {

        $scope.emailPreview = {};
        $scope.emailPreview.currentPosition = 1;
        getEmail();
        $scope.paginate = function(value) {



        };

$scope.to_trusted = function(html_code) {
    return $sce.trustAsHtml(html_code);
};

        function getEmail() {
            if ($scope.Campain === undefined) {
                var unregister = $scope.$watch('Campain', function(newValue, oldValue) {
                    // Only act when our property has changed.
                    if (newValue !== oldValue) {
                        var email = Entity.Email.get({campainId: $scope.Campain.id, emailId: $scope.emailPreview.currentPosition},
                        function() {
                            $scope.email = email;

                        });

                        unregister();
                    }
                }, true);
            } else {
                var email = Entity.Email.get({campainId: $scope.Campain.id, emailId: $scope.emailPreview.currentPosition},
                function() {
                    $scope.email = email;

                });

            }
        }






    }]);

