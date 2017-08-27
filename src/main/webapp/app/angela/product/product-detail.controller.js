(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('ProductDetailController', ProductDetailController);

    ProductDetailController.$inject = ['$scope', '$rootScope', 'Principal', 'LoginService', '$state', '$stateParams', 'product', 'PROD'];

    function ProductDetailController ($scope, $rootScope, Principal, LoginService, $state, $stateParams, product, PROD) {
        var vm = this;
        vm.PROD = PROD;
        $rootScope.cartList = []; // 购物车
        vm.type= $stateParams['type'] || 'Category';
        vm.addCart = addCart;

        Principal.identity().then(function(account) {
            vm.quotations = angular.copy(account);
        });

        locadAll();
        function locadAll() {

            loadProductDetail($stateParams['id']);

            loadCategorysList();
        }
        // 加载产品
        function loadProductDetail(id) {

            var productDetailP = product.getProductDetail({"id": (id || '')});

            productDetailP.then(function(product) {
                 vm.product = product['data']['products'];
            })
        }

        // 加载类别
        function loadCategorysList(params) {
            var categoryListP = product.getCategorysList(params);

            categoryListP.then(function(categoryList) {
                vm.categoryList = categoryList['data'];
            })
        }

        // 添加到购物车
        function addCart() {
            $rootScope.cartList.push(vm.quotations);
        }
    }
})();
