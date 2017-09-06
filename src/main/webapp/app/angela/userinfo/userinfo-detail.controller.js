(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('UserinfoDetailController', UserinfoDetailController);

    UserinfoDetailController.$inject = ['Auth', 'Principal', '$uibModal'];

    function UserinfoDetailController (Auth, Principal, $uibModal) {
            var vm = this;

            vm.changePassword = changePassword;
            vm.doNotMatch = null;
            vm.error = null;
            vm.success = null;

            Principal.identity().then(function(account) {
                console.log(account);
                vm.account = account;
            });

            function changePassword () {
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
            vm.updateInfo = function (i) {
            console.log(i)
            $uibModal.open({
                //backdrop:false,  
                size: 'lg',
                animation: true,
                templateUrl: 'app/angela/userinfo/userinfo-update.html',
                controller: 'UserInfoUpdateController',
                controllerAs: 'vm',
                backdrop: 'static',
                resolve: {
                    Data: function () {
                        return i;
                    }
                }
            })
        }

    }
})();
