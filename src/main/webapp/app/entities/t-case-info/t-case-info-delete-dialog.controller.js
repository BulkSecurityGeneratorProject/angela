(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_case_infoDeleteController',T_case_infoDeleteController);

    T_case_infoDeleteController.$inject = ['$uibModalInstance', 'entity', 'T_case_info'];

    function T_case_infoDeleteController($uibModalInstance, entity, T_case_info) {
        var vm = this;

        vm.t_case_info = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            T_case_info.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
