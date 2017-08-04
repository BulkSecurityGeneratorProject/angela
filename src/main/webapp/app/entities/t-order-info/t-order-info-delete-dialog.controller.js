(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_order_infoDeleteController',T_order_infoDeleteController);

    T_order_infoDeleteController.$inject = ['$uibModalInstance', 'entity', 'T_order_info'];

    function T_order_infoDeleteController($uibModalInstance, entity, T_order_info) {
        var vm = this;

        vm.t_order_info = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            T_order_info.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
