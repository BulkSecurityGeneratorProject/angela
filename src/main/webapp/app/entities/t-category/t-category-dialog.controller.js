(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_categoryDialogController', T_categoryDialogController);

    T_categoryDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'T_category'];

    function T_categoryDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, T_category) {
        var vm = this;

        vm.t_category = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.t_category.id !== null) {
                T_category.update(vm.t_category, onSaveSuccess, onSaveError);
            } else {
                T_category.save(vm.t_category, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('angelaApp:t_categoryUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
