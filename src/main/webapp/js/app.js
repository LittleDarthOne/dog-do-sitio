angular.module('app', [])

.controller('TestBreadcrumbController', function($scope, $locale) {
    $scope.items=[
        {url: '#', label: 'Área 1'},
        {url: '#', label: 'Área 2'},
        {url: '#', label: 'Área 3', active: true},
    ];
})

.controller('TestOrderController', function($scope, $locale) {
    $scope.orders=[
        {
            id: '1',
            date: new Date('Mon Feb 19 2018 16:50:00 GMT-0300'),
            client: {id: 1, name: 'Cliente 1', adress: 'Rua onde mora o cliente1, 1234', phone: '53998765432'},
            products:[
                {id: 1, name: 'Lanche 1', quantity: 1, details: 'sem alface'},
                {id: 2, name: 'Lanche 2', quantity: 2}
            ]
        },
        {
            id: '2',
            date: new Date('Mon Feb 19 2018 16:30:00 GMT-0300'),
            client: {id: 4, name: 'Cliente 4', adress: 'Rua onde mora o cliente 4, 456', phone: '53912345678'},
            products:[
                {id: 1, name: 'Lanche 1', quantity: 2}
            ]
        },
        {
            id: '3',
            date: new Date('Mon Feb 19 2018 15:55:00 GMT-0300'),
            client: {id: 2, name: 'Cliente 2', adress: 'Rua onde mora o cliente 2, 222', phone: '53989562314'},
            products:[
                {id: 4, name: 'Lanche 4', quantity: 2},
                {id: 1, name: 'Lanche 1', quantity: 1},
                {id: 5, name: 'Refrigerante', quantity: 1}
            ]
        }
    ];

    $scope.countProducts = function(order){
        var count = 0;
        if(order.products && order.products.length) {
            angular.forEach(order.products, function(product) {
                count += product.quantity;
            });
        }
        return count;
    };
});