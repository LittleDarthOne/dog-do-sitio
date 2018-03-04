var app = angular.module('dog.app', ['ngRoute', 'dog.service.product']);

app.config(function($routeProvider, $locationProvider) {
	$routeProvider.when('/home', {
		templateUrl: 'home.html',
		controller: 'HomeController'
	});

	$routeProvider.when('/products', {
		templateUrl: 'products.html',
		controller: 'ProductsController'
	});

	$routeProvider.otherwise({redirectTo: '/home' });
});

app.controller('TestBreadcrumbController', function($scope, $locale) {
    $scope.items=[
        {url: '#', label: 'Área 1'},
        {url: '#', label: 'Área 2'},
        {url: '#', label: 'Área 3', active: true},
    ];
});

app.controller('NavController', function($scope, $locale) {
    $scope.elements = [
        {label: 'Home', target: '#!home', active: true},
        {label: 'Cardápio', target: '#!products', active: false}
    ];

    $scope.onClick = function(element){
        for(var count = 0; count < $scope.elements.length; count ++) {
            $scope.elements[count].active = false;
        }
        element.active = true;
    };
});

app.controller('HomeController', function($scope, $locale) {
    $scope.resizeBanners = function(){
        $('#banners').css({
            'height': $(window).height(),
            'max-height': $(window).height(),
            'overflow': 'hidden'
        });
    };
    $scope.resizeBanners();
    $(window).resize($scope.resizeBanners);

    $scope.banners = [
        {active: true, backgroundImage: '/img/banner/dog.jpg', title: 'Aqui tem hot dog!', description: 'Começou só com cachorro-quente, mas a essa altura, nem sem mais se ainda sai algum...'},
        {active: false, backgroundImage: '/img/banner/bauru.jpg', title: 'Aqui também xis!', description: 'Acho que agora é o que mais sai...'},
        {active: false, backgroundImage: '/img/banner/prato.jpg', title: 'Será que vai ter prato?', description: 'Quem sabe, daqui a pouco não passa a oferecer prato também...'}
    ];

    $scope.onSelectBanner = function(banner){
        for(var count = 0; count < $scope.elements.length; count ++) {
            $scope.banners[count].active = false;
        }
        banner.active = true;
    };
});

app.controller('ProductsController', function($scope, $locale, ProductService) {
    $scope.products = [];
    $scope.editingProduct = {id: null, name: null, description: null, price: null};

    ProductService.getAll().then(function (response) {
        $scope.products = response.data;
    });

    $scope.save = function(product) {
        ProductService.save(product).then(function (response) {
            $scope.products.push(response.data);
            $scope.editingProduct = {id: null, name: null, description: null, price: null};
            $('#editingProductModal').modal('hide');
        });
    };

    $scope.delete = function(productId) {
        ProductService.delete(productId).then(function (response) {
            if(response.status === 200) {
                var index;
                for(var count = 0; count < $scope.products.length; count ++) {
                    if($scope.products[count].id === productId) {
                        index = count;
                    }
                }

                if(!!index) {
                    $scope.products.splice(index, 1);
                }
            }
        });
    };
});