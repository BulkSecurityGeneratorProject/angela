(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_reviewDeleteController',T_reviewDeleteController);

    T_reviewDeleteController.$inject = ['$uibModalInstance', 'entity', 'T_review'];

    function T_reviewDeleteController($uibModalInstance, entity, T_review) {
        var vm = this;

        vm.t_review = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            T_review.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
