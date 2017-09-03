(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('TesterminalController', TesterminalController);

    TesterminalController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'testerminal', 'AlertService'];

    function TesterminalController ($scope, Principal, LoginService, $state, testerminal, AlertService) {
        var vm = this;
        vm.fromData = {};
        vm.submitComment = submitComment;


        Principal.identity().then(function(account) {
            // 默认全部五星
            vm.fromData.satisfaction = 5;
            vm.fromData.responsiveness = 5;
            vm.fromData.delivery = 5;
            vm.fromData.problemResolution = 5;
            vm.fromData.imprinting = 5;
            vm.fromData.productQuality = 5;

            vm.fromData.company = account['companyName'];
            vm.fromData.telNumber = account['telNumber'];
            vm.fromData.fax = account['faxNumber'];
            vm.fromData.email = account['email'];
            vm.fromData.asi = account['asiSageNumber'];
        });


        function submitComment() {

            console.log(vm.fromData);
            testerminal.postAddTesterMinals(vm.fromData);
            AlertService.success("提交成功！！！");
        }


    }
})();
