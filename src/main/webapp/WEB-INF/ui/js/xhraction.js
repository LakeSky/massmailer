
// Module
// ------
var xhraction = angular.module('xhraction', []);




// Factory
// -------
xhraction.factory('xhrActionService', ['$rootScope', function($rootScope) {
        return {
            simpleGetAction: function(path, paramName, paramValue) {
                var data = new FormData(),
                xhr = new XMLHttpRequest();
                xhr.onloadstart = function() {
                    console.log('Factory: xhraction started: ', file.name);
                    $rootScope.$emit('xhraction:start', xhr);
                };
                // When the request has failed.
                xhr.onerror = function(e) {
                    $rootScope.$emit('xhraction:error', e);
                };
                xhr.onreadystatechange = function(e)
                {
                    if (xhr.readyState === 4 && xhr.status === 201)
                    {
                        $rootScope.$emit('xhraction:succes', e, xhr);
                    }
                };

                // Send to server, where we can then access it with $_FILES['file].
                data.append(paramName, paramValue);
                xhr.open('GET', path);
                xhr.send(data);
            }
        };

    }]);

