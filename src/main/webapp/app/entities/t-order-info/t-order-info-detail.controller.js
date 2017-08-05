(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_order_infoDetailController', T_order_infoDetailController);

    T_order_infoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'T_order_info'];

    function T_order_infoDetailController($scope, $rootScope, $stateParams, previousState, entity, T_order_info) {
        var vm = this;

        vm.t_order_info = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('angelaApp:t_order_infoUpdate', function(event, result) {
            vm.t_order_info = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
