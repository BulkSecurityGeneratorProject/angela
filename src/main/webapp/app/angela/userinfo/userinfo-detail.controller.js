(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('UserinfoDetailController', UserinfoDetailController);

    UserinfoDetailController.$inject = ['Auth', 'Principal'];

    function UserinfoDetailController (Auth, Principal) {
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

    }
})();
