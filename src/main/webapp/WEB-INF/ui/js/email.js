/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var email = angular.module('email', ['ngRoute', 'massmaller.service', 'ui.bootstrap']);

// Controller
// ----------
email.controller('EmailPreviewCtrl', ['$scope', 'Entity', '$routeParams', '$sce', function($scope, Entity, $routeParams, $sce) {

        $scope.emailPreview = {};
        $scope.emailPreview.currentPosition = 1;
        $scope.emailPreview.recordsCount = 0;
        if ($scope.Campain !== undefined) {
            $scope.emailPreview.recordsCount = $scope.Campain.recordsCount;

        }

        getEmail();
        $scope.paginate = function() {

            getEmail();

        };

        $scope.to_trusted = function(html_code) {
            return $sce.trustAsHtml(html_code);
        };

        function getEmail() {
            if ($scope.Campain === undefined) {
                var unregister = $scope.$watch('Campain', function(newValue, oldValue) {
                    // Only act when our property has changed.
                    if (newValue !== oldValue) {
                        $scope.emailPreview.recordsCount = $scope.Campain.recordsCount;
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

