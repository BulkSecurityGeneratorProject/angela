(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('MycartListController', MycartListController);

    MycartListController.$inject = ['$scope', '$rootScope', 'Principal', 'LoginService', '$state'];

    function MycartListController($scope, $rootScope, Principal, LoginService, $state) {
        var vm = this;
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
        if($rootScope.quotation)
        $rootScope.quotation.cartList.forEach(function(d){
            vm.Product.push({
                id: d['id'],
                name: d['productName'],
                quantity: d['quantity'],
                price: d['target_price'],
                asi_sage: d['asi_sage'],
                message: d['message']
            });
        })

        //减少数量
        vm.reduce = function(index) {
            if (vm.Product[index].quantity > 1) {
                vm.Product[index].quantity--;
            } else {
                vm.remove(index);
            }
        }
        //添加数量函数
        vm.add = function(index) {
            vm.Product[index].quantity++;
        }
        //所有商品总价函数
        vm.totalQuantity = function() {
            var allprice = 0
            for (var i = 0; i < vm.Product.length; i++) {
                allprice += vm.Product[i].quantity * vm.Product[i].price;
            }
            return allprice;
        }
        //购买总数量函数
        vm.numAll = function() {
            var numAlls = 0
            for (var i = 0; i < vm.Product.length; i++) {
                numAlls += vm.Product[i].quantity;
            }
            return numAlls;
        }
        //删除当前商品
        vm.remove = function(index) {
            if (confirm("确定要清空数据吗")) {
                vm.Product.splice(index, 1)
            }
        }
        //清空购物车
        vm.removeAll = function() {
            if (confirm("你确定套清空购物车所有商品吗?")) {
                vm.Product = [];
            }
        }
        //解决输入框输入负数时变为1
        vm.change = function(index) {
            if (vm.Product[index].quantity >= 1) {} else {
                vm.Product[index].quantity = 1;
            }
        }
        $scope.$watch('vm.Product', function(oldvalue, newvalue) {
            console.log(oldvalue);
            console.log(newvalue);
        })


    }
})();
