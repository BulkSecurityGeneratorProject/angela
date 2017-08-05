(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_reviewDetailController', T_reviewDetailController);

    T_reviewDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'T_review'];

    function T_reviewDetailController($scope, $rootScope, $stateParams, previousState, entity, T_review) {
        var vm = this;

        vm.t_review = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('angelaApp:t_reviewUpdate', function(event, result) {
            vm.t_review = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
