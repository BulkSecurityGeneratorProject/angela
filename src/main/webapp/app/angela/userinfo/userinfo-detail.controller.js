(function () {
    'use strict';

    angular
        .module('angelaApp')
        .controller('UserinfoDetailController', UserinfoDetailController);

    UserinfoDetailController.$inject = ['Auth', 'Principal', '$uibModal', 'userinfo'];

    function UserinfoDetailController(Auth, Principal, $uibModal, userinfo) {
        var vm = this;

        vm.changePassword = changePassword;
        vm.doNotMatch = null;
        vm.error = null;
        vm.success = null;
        vm.account = {};
        // userinfo.getAllOrderList({}).then(function (d) {
        //     console.log(d)
        // })




        function changePassword() {
            if (vm.password !== vm.confirmPassword) {
                vm.error = null;
                vm.success = null;
                vm.doNotMatch = 'ERROR';
            } else {
                vm.doNotMatch = null;
                Auth.updateAccount(vm.account).then(function () {
                    vm.error = null;
                    vm.success = 'OK';
                }).catch(function () {
                    vm.success = null;
                    vm.error = 'ERROR';
                });
            }
        }
        function loadOrdersList() {
            Principal.identity().then(function (account) {

                vm.account = account;
                console.log(account)
                userinfo.getAllOrderList({ 'id': vm.account.id }).then(function (orderList) {
                    var _orderList = orderList.data.OrderInfo;
                    console.log(orderList)
                    vm.orderList = _orderList;
                })
            });

        }
        loadOrdersList();

        //     vm.updateInfo = function (i) {
        //     $uibModal.open({
        //         //backdrop:false,  
        //         size: 'lg',
        //         animation: true,
        //         templateUrl: 'app/angela/userinfo/userinfo-update.html',
        //         controller: 'UserInfoUpdateController',
        //         controllerAs: 'vm',
        //         backdrop: 'static',
        //         resolve: {
        //             Data: function () {
        //                 return i;
        //             }
        //         }
        //     })
        // }

    }
})();
