(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_dictionaryDetailController', T_dictionaryDetailController);

    T_dictionaryDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'T_dictionary'];

    function T_dictionaryDetailController($scope, $rootScope, $stateParams, previousState, entity, T_dictionary) {
        var vm = this;

        vm.t_dictionary = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('angelaApp:t_dictionaryUpdate', function(event, result) {
            vm.t_dictionary = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
