(function () {
    'use strict';

    angular
        .module('angelaApp')
        .controller('MycartListController', MycartListController);

    MycartListController.$inject = ['$scope', '$rootScope', '$cookies', '$cookieStore', 'Principal', 'LoginService', '$state', 'mycart'];

    function MycartListController($scope, $rootScope, $cookies, $cookieStore, Principal, LoginService, $state, mycart) {
        var vm = this;
        Principal.identity().then(function (account) {
            vm.account = account;
        });
        vm.Product = [];
        vm.quotation = $cookieStore.get('quotation');
        vm.saveCookie = [];
        var quotation = angular.copy(vm.quotation);
            console.log(quotation)
        if (quotation){
            (quotation.orderProduct || []).forEach(function (d) {
                vm.Product.push({
                    productId: d['productId'],
                    name: d['productName'],
                    quantity: d['quantity'],
                    price: d['targetPrice'],
                    asiSageNo: d['asi'],
                    message: d['message']
                });
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
                vm.saveCookie.push({
                    productId: d['productId'],
                    productName: d['name'],
                    quantity: d['quantity'],
                    targetPrice: d['price'],
                    asi: d['asiSageNo'],
                    message: d['message']
                });
                quotation.orderProduct = vm.saveCookie;
                $cookieStore.put('quotation', quotation)
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
        })
        //提交购物信息
        vm.subCart = function () {
            console.log('vm.rrrrrr', vm.quotation)
            var params = {
                "userId": vm.quotation['userid'],
                "companyName": vm.quotation['companyName'],
                "customName": vm.quotation['customName'],
                "phoneNumber": vm.quotation['telNumber'],
                "targetPrice": vm.totalQuantity(),
                "asi": vm.quotation['asi'],
                "fox": vm.quotation['fox'],
                "email": vm.quotation['email'],
                "remarks": "",
                "orderProduct": vm.Product
            };
            var postMycart = mycart.postAddOrdersList(params);
            console.log(params)
            postMycart.then(function (res) {
                alert('Submit successfully')
                vm.Product = [];
                quotation.orderProduct = vm.Product;
                $cookieStore.put('quotation', quotation)
            })

        }


    }
})();
