angular.module('services.breadcrumbs', ['ngRoute']).factory('services.breadcrumbs', ['$rootScope', '$location', function($rootScope, $location) {

        var breadcrumbs = [];
        var breadcrumbsService = {};
        var submenu = [];

        //we want to update breadcrumbs only when a route is actually changed
        //as $location.path() will get updated imediatelly (even if route change fails!)
        $rootScope.$on('$routeChangeSuccess', function(event, current) {

            var pathElements = $location.path().split('/'), result = [], i;
            var breadcrumbPath = function(index) {
                return '/' + (pathElements.slice(0, index + 1)).join('/');
            };

            pathElements.shift();
            for (i = 0; i < pathElements.length; i++) {
                result.push({name: pathElements[i], path: breadcrumbPath(i)});
            }

            breadcrumbs = result;
        });

        breadcrumbsService.getAll = function() {
            return breadcrumbs;
        };

        breadcrumbsService.getFirst = function() {
            return breadcrumbs[0] || {};
        };
        breadcrumbsService.getSubmenu = function() {
            return submenu;

        };
        breadcrumbsService.resetSubmenu = function() {
            submenu = [];
            return true;
        };

        breadcrumbsService.addToSubmenu = function(name,link, glyph) {
            var submenuItemDashboard = {
                name: name,
                link: link,
                glyph: glyph
            };
            submenu.push(submenuItemDashboard);
        };

        return breadcrumbsService;
    }]);


