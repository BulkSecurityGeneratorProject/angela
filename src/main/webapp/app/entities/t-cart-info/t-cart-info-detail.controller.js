(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_cart_infoDetailController', T_cart_infoDetailController);

    T_cart_infoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'T_cart_info'];

    function T_cart_infoDetailController($scope, $rootScope, $stateParams, previousState, entity, T_cart_info) {
        var vm = this;

        vm.t_cart_info = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('angelaApp:t_cart_infoUpdate', function(event, result) {
            vm.t_cart_info = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
