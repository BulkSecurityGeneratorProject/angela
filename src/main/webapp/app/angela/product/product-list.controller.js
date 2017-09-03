(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('ProductListController', ProductListController);

    ProductListController.$inject = ['$scope', '$stateParams', 'Principal', 'LoginService', '$state', '$sce','product', 'PROD'];

    function ProductListController ($scope, $stateParams, Principal, LoginService, $state, $sce,product, PROD) {
        var vm = this;
        vm.PROD = PROD;
        vm.getShowImg = getShowImg;
        vm.stateParams = $stateParams;
        vm.type= $stateParams['type'] || 'Category';
        // 菜单适应小屏
        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.loadProductList = loadProductList;
        vm.baseProductList = {"New Arrvial": "createDate" ,"Top 100": "sellCount", "Hot Sales": "isHot"};
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
        // 菜单适应小屏
        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }


        // 需要显示的图片
        function getShowImg(data) {
            var url = (data || []).find(function(d) {
                return d['imageType'] == 1;
            })
            return url['imageUrl'];
        }

        // 加载产品
        function loadProductList(type, name) {
            console.log(type, name);

            vm.productTitle = name;

            vm.orderby = type;
            var productListP = product.getProductList({"OrderBy": (type || 'createDate')});
            productListP.then(function(product) {
                 vm.productList = product['data'];
            })
        }
    }
})();
