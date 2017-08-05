(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_case_infoDetailController', T_case_infoDetailController);

    T_case_infoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'T_case_info'];

    function T_case_infoDetailController($scope, $rootScope, $stateParams, previousState, entity, T_case_info) {
        var vm = this;

        vm.t_case_info = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('angelaApp:t_case_infoUpdate', function(event, result) {
            vm.t_case_info = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
