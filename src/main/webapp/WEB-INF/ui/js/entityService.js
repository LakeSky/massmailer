/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var entityService = angular.module('massmaller.service', ['ngResource']).
        factory('Entity', function($resource) {
            return {
                User: $resource('app/users/:userId', {userId: '@id'}, {browse: {
                        method: 'GET',
                        params: {page: 0,
                            limit: 10,
                            sort: 'id',
                            sortDir: 1,
                            search: null,
                            searchString: null
                        }, isArray: true

                    }}),
                DataSource: $resource('app/datasource/:dataSourceId', {dataSourceId: '@id'}, {search: {
                        method: 'GET',
                        params: {search: 'f', searchString: 's'}
                        , isArray: true
                    }}),
                Campain: $resource('app/campain/:campainId', {campainId: '@id'}, {browse: {
                        method: 'GET',
                        params: {page: 0,
                            limit: 10,
                            sort: 'id',
                            sortDir: 1,
                            search: null,
                            searchString: null,
                            ctype: 'all'

                        }, isArray: true

                    }}),
                Role: $resource('app/login/roles', null, {query: {
                        method: 'GET',
                        isArray: true

                    }}),
                Login: $resource('app/login/:userId', {userId: '@id'}),
                DataStructure: $resource('app/datasource/structure/:fileId/:timeStamp', {fileId: '@id', timeStamp: '1'}),
                TemplateFields: $resource('app/template/templatefields/:fileId/:timeStamp', {fileId: '@id', timeStamp: '1'}),
                ImagesFolder: $resource('app/images/templatefields/:fileId\\/', {fileId: '@id'}),
                DataSourceRow: $resource('app/datasource/:dataSourceId/rows', {dataSourceId: '@id'}, {browse: {
                        method: 'GET',
                        params: {page: '1',
                            limit: null,
                            sort: null,
                            sortDir: null,
                            search: null,
                            searchString: null
                        }, isArray: true

                    }}),
                Email: $resource('app/email/:campainId/:emailId')


            };
        });


