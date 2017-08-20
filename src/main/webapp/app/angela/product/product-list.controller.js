(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('ProductListController', ProductListController);

    ProductListController.$inject = ['$scope', '$stateParams', 'Principal', 'LoginService', '$state', '$sce','product', 'PROD'];

    function ProductListController ($scope, $stateParams, Principal, LoginService, $state, $sce,product, PROD) {
        var vm = this;
        vm.PROD = PROD;
        vm.type= $stateParams['type'] || 'Category';


        locadAll();

        function locadAll() {

            console.log("$stateParams", $stateParams);
            loadProductList($stateParams['orderby']);
            loadCategorysList({});
        }
        // 加载产品
        function loadProductList(type) {

            var productListP = product.getProductList({"OrderBy": (type || 'createDate')});

            productListP.then(function(product) {
                console.log(product);
                 vm.productList = product['data'];
            })
        }

        // 加载类别
        function loadCategorysList(params) {
            var categoryListP = product.getCategorysList(params);

            categoryListP.then(function(categoryList) {
                console.log("vm.categoryList", categoryList['data']);
                vm.categoryList = categoryList['data'];
            })
        }

    }
})();
