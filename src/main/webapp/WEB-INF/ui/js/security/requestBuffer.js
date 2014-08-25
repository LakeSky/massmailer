 var requestBuffer  =  angular.module('massmaller.service').
         factory('requestBuffer', function(){
    function RequestBuffer() {
    this.requests = [];
    }
    RequestBuffer.prototype.clear = function() {
    this.requests = [];
    };
    RequestBuffer.prototype.getAll = function() {
    return this.requests;
    };
    RequestBuffer.prototype.add = function(req) {
    this.requests.push(req);
    };
    return new RequestBuffer();
    });
