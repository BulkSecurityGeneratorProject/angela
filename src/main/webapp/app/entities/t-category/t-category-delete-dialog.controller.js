(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_categoryDeleteController',T_categoryDeleteController);

    T_categoryDeleteController.$inject = ['$uibModalInstance', 'entity', 'T_category'];

    function T_categoryDeleteController($uibModalInstance, entity, T_category) {
        var vm = this;

        vm.t_category = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            T_category.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
