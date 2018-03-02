var app = angular.module('dog.app', ['dog.service.product']);

app.controller('HeaderController', function($scope) {

    $scope.resizeJumbotronBanner = function(){
        $('#jumbotron-banner').css({
            'height': $(window).height()
        });
    };
    $scope.resizeJumbotronBanner();
    $(window).resize($scope.resizeJumbotronBanner);

});

app.controller('TestBreadcrumbController', function($scope, $locale) {
    $scope.items=[
        {url: '#', label: 'Área 1'},
        {url: '#', label: 'Área 2'},
        {url: '#', label: 'Área 3', active: true},
    ];
});

app.controller('ProductListController', function($scope, $locale, ProductService) {

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

    $scope.bannerHeight = $(window).height();
    // make sure div stays full width/height on resize
    $(window).resize(function(){
        $scope.bannerHeight = $(window).height();
    });

});