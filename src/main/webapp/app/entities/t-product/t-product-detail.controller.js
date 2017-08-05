(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_productDetailController', T_productDetailController);

    T_productDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'T_product'];

    function T_productDetailController($scope, $rootScope, $stateParams, previousState, entity, T_product) {
        var vm = this;

        vm.t_product = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('angelaApp:t_productUpdate', function(event, result) {
            vm.t_product = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
