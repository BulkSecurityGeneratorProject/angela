(function () {
    'use strict';

    angular
        .module('angelaApp')
        .controller('ProductDetailController', ProductDetailController);

    ProductDetailController.$inject = ['$scope', '$rootScope', '$cookies', '$cookieStore', 'Principal', 'LoginService', '$state', '$stateParams', 'product', 'PROD'];

    function ProductDetailController($scope, $rootScope, $cookies, $cookieStore, Principal, LoginService, $state, $stateParams, product, PROD) {
        var vm = this;
        vm.PROD = PROD;
        // $cookieStore.put("cartList", []);
        // var cartList = [];
        // $rootScope.cartList = []; // 购物车
        vm.type = $stateParams['type'] || 'Category';
        vm.addCart = addCart;
        vm.selectImg = selectImg;
        vm.quotation = {};
        vm.imgIdx = 0;

        Principal.identity().then(function (account) {
            vm.account = account;

            console.log(account);

            vm.quotation.companyName = account['companyName'];
            vm.quotation.email = account['email'];
            vm.quotation.phoneNumber = account['telNumber'];
            // vm.quotation.deliverTime = account['deliverTime'];
            // vm.quotation.targetPrice = account['targetPrice'];
            vm.quotation.asiSageNo = account['asiSageNumber'];
            vm.quotation.message = account['message'];

            vm.quotation.userid = account['id'];
            vm.quotation.customName = account['login'];

            console.log('account', vm.account)
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
                console.log(vm.product);

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
            vm.quotation.productId = vm.product['id'];
            vm.quotation.productName = vm.product['productName'];
            console.log("quotation", vm.quotation);

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

            console.log(quotation);


            if (!$cookieStore.get('quotation')) {
                $cookieStore.put("quotation", quotation);
            } else {
                var _quotation = $cookieStore.get('quotation');
                _quotation.userid = quotation['userid'];
                _quotation.companyName = quotation['companyName'];
                _quotation.customName = quotation['customName'];
                _quotation.phoneNumber = quotation['phoneNumber'];
                _quotation.targetPrice = quotation['targetPrice'];
                _quotation.asi = quotation['asiSageNo'];
                _quotation.email = quotation['email'];
                _quotation.productName = quotation['productName'];
                // vm.quotation.deliverTime = account['deliverTime'];
                // vm.quotation.targetPrice = account['targetPrice'];
                _quotation.orderProduct.push(catList[0]);

                $cookieStore.put("quotation", _quotation);
            }

            console.log("$cookieStore.get('quotation')", $cookieStore.get('quotation'));




            // vm.quotation = {
            //         id: vm.product['id'],
            //         clientName: vm.account['clientName'] || s['clientName'],
            //         companyName: vm.account['companyName'] || s['companyName'],
            //         email: vm.account['login'] || s['login'],
            //         telNumber: vm.account['telNumber'] || s['telNumber'], 
            //         name: vm.product['productName'],
            //         quantity: m['quantity'],
            //         price: m['taprrget_price'],
            //         asi_sage: m['asi_sage'],
            //         message: m['message']
            //     }
            //     console.log(vm.quotation)
            // console.log("vm.oduct", m,s);
            // if ($cookieStore.get('quotation')) {
            //     // $cookieStore.get('quotation').cartList.push(vm.quotation);
            //     // $cookieStore.put('quotation',{'cartList': []});
            //     console.log("init: ", $cookieStore.get('quotation'));
            //     var cart = $cookieStore.get('quotation');
            //     console.log(cart.cartList)
            //     cart.cartList.push(vm.quotation);
            //     console.log(cart.cartList)
            //     $cookieStore.put('quotation', cart)

            // } else {
            //     // cartList = cartList.push('vm.product');

            //     var data = {
            //         'cartList': [],
            //         'userId': vm.account['id'],
            //         'email': vm.account['email'],
            //         'faxNumber': vm.account['faxNumber'],
            //         'login': vm.account['login'],
            //         'companyName': vm.account['companyName']
            //     }
            //     $cookieStore.put('quotation', data);
            // }
            // console.log("$rootScope.quotation ", $cookieStore.get('quotation'));
        }


        //查看图片
        function selectImg(idx) {
            vm.imgIdx = idx;
        }
    }
})();
