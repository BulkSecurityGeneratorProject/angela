(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_picturesDetailController', T_picturesDetailController);

    T_picturesDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'T_pictures'];

    function T_picturesDetailController($scope, $rootScope, $stateParams, previousState, entity, T_pictures) {
        var vm = this;

        vm.t_pictures = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('angelaApp:t_picturesUpdate', function(event, result) {
            vm.t_pictures = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
