(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$rootScope', '$scope', 'Principal', 'LoginService', '$state', 'home', 'PROD'];

    function HomeController (rootScope, $scope, Principal, LoginService, $state, home, PROD) {
        var vm = this;
        vm.PROD = PROD;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.productsTitle =null;
        vm.getShowImg = getShowImg;
        vm.login = LoginService.open;
        vm.categorysList = [];
        vm.register = register;
        vm.loadProductList = loadProductList;
        vm.baseProductList = {"New Arrival": "createDate" ,"Top Seller": "sellCount", "Weekly Discount": "isHot"};
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }


        loadAll();
        // jzh

        function loadAll() {

            loadCaseList();
            // getCategorysList();
            getAdPicturesList();

            loadCategorysList();
        }


        // 加载广告轮播页
        function getAdPicturesList(params) {

            vm.myInterval = 5000;
            vm.noWrapSlides = false;
            vm.active = 0;
            var currIndex = 0;
            vm.slides = [];

            var getAdPicturesListP = home.getAdPicturesList(params);

            getAdPicturesListP.then(function(adPicturesList) {

                vm.adPicturesList = adPicturesList['data'];

                vm.adPicturesList.products.forEach(function(d) {
                    vm.slides.push({
                        image: vm.PROD.IMAGEURL + d['imageUrl'],
                        id: currIndex++
                    })
                })

            })
        }

        // 加载产品
        function loadProductList(type, name) {
            console.log(type, name);

            vm.productTitle = name;

            vm.orderby = type;
            var productListP = home.getProductList({"OrderBy": (type || 'createDate')});
            productListP.then(function(product) {
                 vm.productList = product['data'];
            })
        }

        // 加载类别
        function loadCategorysList() {

            var getCategorysListP = home.getCategorysList();
            getCategorysListP.then(function(categorys) {

                vm.categorysList = (categorys['data']['Categorys'] || []).find(function(d) {
                    return d['cateName'] == "Category";
                })['category'];
            })
        }

        // 加载case
        // 加载产品
        function loadCaseList(type, name) {

            var caseListP = home.getCaseList({"OrderBy": (type || 'createDate')});

            caseListP.then(function(casees) {
                 vm.caseList = casees['data'];
            })
        }

        // 加载类别
        function getCategorysList(params) {
            var categorysListP = home.getCategorysList(params);

            categorysListP.then(function(categorysList) {
                console.log("categorysList", categorysList);
                vm.categorysList = categorysList['data'];
            })
        }

        // 需要显示的图片
        function getShowImg(data) {
            var url = (data || []).find(function(d) {
                return d['imageType'] == 1;
            })
            return url['imageUrl'];
        }

    }
})();
