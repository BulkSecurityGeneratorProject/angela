(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_cart_infoDeleteController',T_cart_infoDeleteController);

    T_cart_infoDeleteController.$inject = ['$uibModalInstance', 'entity', 'T_cart_info'];

    function T_cart_infoDeleteController($uibModalInstance, entity, T_cart_info) {
        var vm = this;

        vm.t_cart_info = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            T_cart_info.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
