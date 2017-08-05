(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_dictionaryDeleteController',T_dictionaryDeleteController);

    T_dictionaryDeleteController.$inject = ['$uibModalInstance', 'entity', 'T_dictionary'];

    function T_dictionaryDeleteController($uibModalInstance, entity, T_dictionary) {
        var vm = this;

        vm.t_dictionary = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            T_dictionary.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
