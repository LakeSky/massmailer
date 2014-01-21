/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var entityService = angular.module('entityService', ['ngResource']).
        factory('Entity', function($resource) {
           
            return {
                User: $resource('../users/:userId', {userId: '@id'}),
                DataSource: $resource('../datasource/:dataSourceId', {dataSourceId: '@id'}),
                DataStructure: $resource('../datasource/structure/:fileId', {fileId: '@id'})
            };
        });
