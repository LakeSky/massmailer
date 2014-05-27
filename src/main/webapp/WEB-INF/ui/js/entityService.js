/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var entityService = angular.module('entityService', ['ngResource']).
        factory('Entity', function($resource) {

            return {
                User: $resource('../users/:userId', {userId: '@id'}),
                DataSource: $resource('../datasource/:dataSourceId', {dataSourceId: '@id'}, {search: {
                        method: 'GET',
                        params: {search: 'f', searchString: 's'}
                        , isArray: true
                    }}),
                Campain: $resource('../campain/:campainType/:campainId', {campainId: '@id',campainType:'all'}),
                DataStructure: $resource('../datasource/structure/:fileId\\/', {fileId: '@id'}),
                TemplateFields: $resource('../template/templatefields/:fileId\\/', {fileId: '@id'}),
                ImagesFolder: $resource('../images/templatefields/:fileId\\/', {fileId: '@id'}),
                DataSourceRow: $resource('../datasource/:dataSourceId/rows\\/', {dataSourceId: '@id'}, {browse: {
                        method:'GET',
                        params:{page: '1' ,
                        limit:null,
                        sort:null,
                        sortDir:null,
                        search:null,
                        searchString:null
                        }   , isArray: true
                        
                }})


            };
        });

