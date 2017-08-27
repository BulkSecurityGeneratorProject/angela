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
            vm.account = account;
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
            console.log("vm.product", vm.product);

            // vm.quotation = vm.quotations || {};

            if($rootScope.quotation) {
                $rootScope.quotation.cartList.push(vm.quotation);
            } else {

                $rootScope.quotation = vm.quotation;
                $rootScope.quotation.cartList = [];
                $rootScope.quotation.userId = vm.account['id'];
                $rootScope.quotation.email = vm.account['email'];
                $rootScope.quotation.faxNumber = vm.account['faxNumber'];
                $rootScope.quotation.login = vm.account['login'];
                $rootScope.quotation.companyName = vm.account['companyName'];

                $rootScope.quotation.cartList.push(vm.product);
            }

            console.log("$rootScope.quotation ", $rootScope.quotation);
        }
    }
})();
