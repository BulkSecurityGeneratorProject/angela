(function () {
    'use strict';

    angular
        .module('angelaApp')
        .controller('productDetailReviewsController', productDetailReviewsController);

    productDetailReviewsController.$inject = ['$scope', '$rootScope', 'Principal', 'LoginService', '$state', '$stateParams', 'product', 'PROD', 'AlertService', '$uibModalInstance'];

    function productDetailReviewsController($scope, $rootScope, Principal, LoginService, $state, $stateParams, product, PROD, AlertService, $uibModalInstance) {
        var vm = this;
        vm.PROD = PROD;
        vm.close = close;
        vm.submitComment = submitComment;

        vm.fromData = {};

        Principal.identity().then(function (account) {

            console.log(account);
            vm.fromData.satisfaction = 5;
            vm.fromData.responsiveness = 5;
            vm.fromData.delivery = 5;
            vm.fromData.problemResolution = 5;
            vm.fromData.imprinting = 5;
            vm.fromData.productQuality = 5;

            vm.fromData.createUser = account['login'];
            vm.fromData.productId = $stateParams['id'];
        });


        function submitComment() {

            product.postAddReviews(vm.fromData);
            AlertService.success("comment success ！！！");
            close();
        }

        function close() {
            $uibModalInstance.close();
        }


    }
})();
