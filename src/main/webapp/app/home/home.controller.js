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
        vm.login = LoginService.open;
        vm.register = register;
        vm.loadProductList = loadProductList;
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

                // 图像滚播
                // vm.slides = [{
                //   image: 'content/images/banner0.jpg',
                // //   text: ['Nice image','Awesome photograph','That is so cool','I love that'],
                //   id: currIndex++
                //   },{
                //     image: 'content/images/banner1.jpg',
                //     // text: ['Nice image','Awesome photograph','That is so cool','I love that'],
                //     id: currIndex++
                //   },{
                //     image: 'content/images/banner2.jpg',
                //     // text: ['Nice image','Awesome photograph','That is so cool','I love that'],
                //     id: currIndex++
                //   }];

            })
        }

        // 加载产品
        function loadProductList(type) {

            vm.orderby = type;
            var productListP = home.getProductList({"OrderBy": (type || 'createDate')});

            productListP.then(function(product) {
                 vm.productList = product['data'];
            })
        }

        // 加载case
        // 加载产品
        function loadCaseList(type) {
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

    }
})();
