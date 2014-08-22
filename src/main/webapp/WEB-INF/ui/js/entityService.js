/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var entityService = angular.module('massmaller.service', ['ngResource']).
        factory('Entity', function($resource) {
            return {
                User: $resource('../users/:userId', {userId: '@id'}),
                DataSource: $resource('../datasource/:dataSourceId', {dataSourceId: '@id'}, {search: {
                        method: 'GET',
                        params: {search: 'f', searchString: 's'}
                        , isArray: true
                    }}),
                Campain: $resource('../campain/:campainId', {campainId: '@id'}, {browse: {
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
                DataStructure: $resource('../datasource/structure/:fileId/:timeStamp', {fileId: '@id', timeStamp: '1'}),
                TemplateFields: $resource('../template/templatefields/:fileId/:timeStamp', {fileId: '@id', timeStamp: '1'}),
                ImagesFolder: $resource('../images/templatefields/:fileId\\/', {fileId: '@id'}),
                DataSourceRow: $resource('../datasource/:dataSourceId/rows', {dataSourceId: '@id'}, {browse: {
                        method: 'GET',
                        params: {page: '1',
                            limit: null,
                            sort: null,
                            sortDir: null,
                            search: null,
                            searchString: null
                        }, isArray: true

                    }}),
                Email: $resource ('../email/:campainId/:emailId')


            };
        });


