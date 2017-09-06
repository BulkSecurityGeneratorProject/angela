(function () {
    'use strict';

    angular
        .module('angelaApp')
        .controller('MycartListController', MycartListController);

    MycartListController.$inject = ['$scope', '$rootScope', '$cookies', '$cookieStore', 'Principal', 'LoginService', '$state', 'mycart'];

    function MycartListController($scope, $rootScope, $cookies, $cookieStore, Principal, LoginService, $state, mycart) {
        var vm = this;
        Principal.identity().then(function (account) {
            console.log(account);
            vm.account = account;
        });
        // vm.Product = [{
        //     id: 1000,
        //     name: "iPhone8",
        //     quantity: 1,
        //     price: 8888
        // }, {
        //     id: 1001,
        //     name: "iPhone9",
        //     quantity: 1,
        //     price: 9888
        // }, {
        //     id: 1002,
        //     name: "iPhone 2s",
        //     quantity: 1,
        //     price: 3888
        // }, {
        //     id: 1003,
        //     name: "iPhone 7P+",
        //     quantity: 1,
        //     price: 10088
        // }];
        vm.Product = [];
        vm.quotation = $cookieStore.get('quotation');
        vm.saveCookie = [];
        var quotation = angular.copy(vm.quotation);

        if (quotation){
            (quotation.orderProduct || []).forEach(function (d) {

                console.log("d", d[0]);
                vm.Product.push({
                    id: d['id'],
                    name: d['productName'],
                    quantity: d['quantity'],
                    price: d['targetPrice'],
                    asi_sage: d['asiSageNo'],
                    message: d['message']
                });

                console.log("Product", vm.Product);
            })
            }

        //
        vm.isShow = function(){
            if(vm.Product.length == 0){
                return false;
            }else{
                return true;
            }
        }
        //商品信息保存
           function saveCookis(){
            vm.saveCookie = [];
            (vm.Product || []).forEach(function (d) {
                console.log("d", d[0]);
                vm.saveCookie.push({
                    id: d['id'],
                    productName: d['name'],
                    quantity: d['quantity'],
                    targetPrice: d['price'],
                    asiSageNo: d['asi_sage'],
                    message: d['message']
                });
                quotation.orderProduct = vm.saveCookie;
                $cookieStore.put('quotation', quotation)
                console.log( $cookieStore.get('quotation'))
            })
           }
            
        //减少数量
        vm.reduce = function (index) {
            if (vm.Product[index].quantity > 1) {
                vm.Product[index].quantity--;
                saveCookis();
            } else {
                vm.remove(index);
            }
        }
        //添加数量函数
        vm.add = function (index) {
            vm.Product[index].quantity++;
            saveCookis();
        }
        //所有商品总价函数
        vm.totalQuantity = function () {
            var allprice = 0
            for (var i = 0; i < vm.Product.length; i++) {
                allprice += vm.Product[i].quantity * vm.Product[i].price;
                 quotation.targetPrice = allprice
                saveCookis();
            }
            return allprice;
        }
        //购买总数量函数
        vm.numAll = function () {
            var numAlls = 0
            for (var i = 0; i < vm.Product.length; i++) {
                numAlls += vm.Product[i].quantity;
                 saveCookis();
            }
            return numAlls;
        }
        //删除当前商品
        vm.remove = function (index) {
            if (confirm("Are you sure you want to empty data? ")) {
                console.log(vm.Product)
                vm.Product.splice(index, 1)
                if(vm.Product.length==0){
                    vm.removeAll();
                }else{
                    saveCookis();
                }
                 
            }
        }
        //清空购物车
        vm.removeAll = function () {
            if (confirm("Are you sure to empty the shopping cart?")) {
                vm.Product = [];
                quotation.orderProduct = vm.Product;
                 $cookieStore.put('quotation', quotation)
                alert("Shopping cart is empty!")
                 
            }
        }
        //解决输入框输入负数时变为1
        vm.change = function (index) {
            if (vm.Product[index].quantity >= 1) { } else {
                vm.Product[index].quantity = 1;
                 saveCookis();
            }
        }
        $scope.$watch('vm.Product', function (oldvalue, newvalue) {
            console.log(oldvalue);
            console.log(newvalue);
        })
        //提交购物信息
        vm.subCart = function () {
            var params = {
                "userId": vm.quotation['userid'],
                "companyName": vm.quotation['companyName'],
                "customName": vm.quotation['customName'],
                "phoneNumber": vm.quotation['telNumber'],
                "targetPrice": vm.totalQuantity(),
                "asi": vm.quotation['asi'],
                "fax": vm.quotation['fax'],
                "email": vm.quotation['email'],
                "remarks": "",
                "orderProduct": vm.Product
            };
            console.log(params)
            var postMycart = mycart.postAddOrdersList(params);

            postMycart.then(function (res) {
                alert('Submit successfully')
            })

        }


    }
})();
