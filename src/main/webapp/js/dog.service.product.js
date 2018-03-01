angular.module('dog.service.product', [])
.factory('ProductService', ['$http', function($http){

    var urlBase = '/product';
    var service = {};

    service.save = function (product) {
        return $http.post(urlBase, product);
    };

    service.getAll = function () {
        return $http.get(urlBase);
    };

    return service;

}]);