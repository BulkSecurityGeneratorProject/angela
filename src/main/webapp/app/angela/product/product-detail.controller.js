(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('ProductDetailController', ProductDetailController);

    ProductDetailController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$stateParams', 'product', 'PROD'];

    function ProductDetailController ($scope, Principal, LoginService, $state, $stateParams, product, PROD) {
        var vm = this;
        vm.PROD = PROD;

        locadAll();
        function locadAll() {

            console.log("$stateParams['id']", $stateParams['id']);
            loadProductDetail($stateParams['id']);
        }
        // 加载产品
        function loadProductDetail(id) {

            var productDetailP = product.getProductDetail({"id": (id || '')});

            productDetailP.then(function(product) {
                 vm.product = product['data']['products'];
            })
        }
    }
})();
