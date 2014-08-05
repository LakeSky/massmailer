/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var loginService = angular.module('loginService', ['ngResource']).
        factory('loginService', function($resource) {

            return $resource('../app/:action', {},
                    {
                        authenticate: {
                            method: 'POST',
                            params: {'action': 'login'},
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                        }
                    }
            );


        });
