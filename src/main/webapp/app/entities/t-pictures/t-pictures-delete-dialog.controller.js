(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_picturesDeleteController',T_picturesDeleteController);

    T_picturesDeleteController.$inject = ['$uibModalInstance', 'entity', 'T_pictures'];

    function T_picturesDeleteController($uibModalInstance, entity, T_pictures) {
        var vm = this;

        vm.t_pictures = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            T_pictures.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
