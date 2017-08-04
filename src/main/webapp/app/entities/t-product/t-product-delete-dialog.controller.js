(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_productDeleteController',T_productDeleteController);

    T_productDeleteController.$inject = ['$uibModalInstance', 'entity', 'T_product'];

    function T_productDeleteController($uibModalInstance, entity, T_product) {
        var vm = this;

        vm.t_product = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            T_product.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
