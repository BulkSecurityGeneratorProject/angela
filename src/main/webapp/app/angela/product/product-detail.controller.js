(function () {
    'use strict';

    angular
        .module('angelaApp')
        .controller('ProductDetailController', ProductDetailController);

    ProductDetailController.$inject = ['$scope', '$rootScope', '$cookies', '$cookieStore', 'Principal', 'LoginService', '$state', '$stateParams', 'product', 'PROD'];

    function ProductDetailController($scope, $rootScope, $cookies, $cookieStore, Principal, LoginService, $state, $stateParams, product, PROD) {
        var vm = this;
        vm.PROD = PROD;
        vm.type = $stateParams['type'] || 'Category';
        vm.addCart = addCart;
        vm.selectImg = selectImg;
        vm.quotation = {};
        vm.imgIdx = 0;

        Principal.identity().then(function (account) {
            vm.account = account;
            vm.quotation.companyName = account['companyName'];
            vm.quotation.clientName = account['login'];
            vm.quotation.email = account['email'];
            vm.quotation.phoneNumber = account['telNumber'];
            vm.quotation.asi = account['asiSageNumber'];
            vm.quotation.message = account['message'];
            vm.quotation.fox = account['faxNumber'];
            vm.quotation.userid = account['id'];
            vm.quotation.customName = account['login'];
            var keepGoing = true;
            angular.forEach(vm.quotation, function (d) {

                if (keepGoing) {
                    if (d === null) {
                        if (confirm('Your personal information is not perfect, whether to perfect your personal information first?')) {
                            keepGoing = false;
                            $state.go('userinfo-detail.edit');
                        } else {
                            keepGoing = false;
                            return;
                        }
                    }
                }

            })
        });


        locadAll();
        function locadAll() {

            loadProductDetail($stateParams['id']);

            loadCategorysList();
        }
        // 加载产品
        function loadProductDetail(id) {

            var productDetailP = product.getProductDetail({ "id": (id || '') });

            productDetailP.then(function (product) {
                vm.product = product['data']['products'];
            })
        }
        // 加载类别
        function loadCategorysList(params) {
            var categoryListP = product.getCategorysList(params);
            categoryListP.then(function (categoryList) {
                vm.categoryList = categoryList['data'];
            })
        }

        // 添加到购物车
        function addCart() {
            // 把产品id 加入quotation
            if (vm.account == null) {
                alert("You are not logged in yet!");
                $state.go('full-login');
            } else {
                vm.quotation.productId = vm.product['id'];
                vm.quotation.productName = vm.product['productName'];

                var quotation = angular.copy(vm.quotation);

                var catList = [];

                catList.push({
                    "productId": quotation.productId,
                    "quantity": quotation.quantity,
                    "deliveryTime": quotation.deliveryTime,
                    "targetPrice": quotation.targetPrice,
                    "asiSageNo": quotation.asiSageNo,
                    "message": quotation.message,

                    "productName": quotation.productName

                });
                quotation.orderProduct = catList;

                if (!$cookieStore.get('quotation')) {
                    $cookieStore.put("quotation", quotation);
                } else {
                    var _quotation = $cookieStore.get('quotation');
                    _quotation.userid = quotation['userid'];
                    _quotation.companyName = quotation['companyName'];
                    _quotation.clientName = quotation['clientName'];
                    _quotation.customName = quotation['customName'];
                    _quotation.phoneNumber = quotation['phoneNumber'];
                    _quotation.fox = quotation['fox'];
                    _quotation.asi = quotation['asi'];
                    _quotation.customName = quotation['customName'];
                    _quotation.email = quotation['email'];
                    _quotation.productName = quotation['productName'];
                    _quotation.orderProduct.push(catList[0]);

                    $cookieStore.put("quotation", _quotation);
                }
                alert('submit successfully!');
            }

        }

        //查看图片
        function selectImg(idx) {
            vm.imgIdx = idx;
        }
    }
})();
