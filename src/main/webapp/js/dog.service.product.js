angular.module('dog.service.product', [])
.factory('ProductService', ['$http', function($http){

    var urlBase = '/product';
    var service = {};

    service.getAll = function (success, error) {
        return $http.get(urlBase);
    };

    return service;

}]);