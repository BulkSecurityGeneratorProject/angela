(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_categoryDetailController', T_categoryDetailController);

    T_categoryDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'T_category'];

    function T_categoryDetailController($scope, $rootScope, $stateParams, previousState, entity, T_category) {
        var vm = this;

        vm.t_category = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('angelaApp:t_categoryUpdate', function(event, result) {
            vm.t_category = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
