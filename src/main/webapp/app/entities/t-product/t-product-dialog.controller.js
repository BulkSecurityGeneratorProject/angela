(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_productDialogController', T_productDialogController);

    T_productDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'T_product'];

    function T_productDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, T_product) {
        var vm = this;

        vm.t_product = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.t_product.id !== null) {
                T_product.update(vm.t_product, onSaveSuccess, onSaveError);
            } else {
                T_product.save(vm.t_product, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('angelaApp:t_productUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createDate = false;
        vm.datePickerOpenStatus.lastUpdate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
