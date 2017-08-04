(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_order_infoDialogController', T_order_infoDialogController);

    T_order_infoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'T_order_info'];

    function T_order_infoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, T_order_info) {
        var vm = this;

        vm.t_order_info = entity;
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
            if (vm.t_order_info.id !== null) {
                T_order_info.update(vm.t_order_info, onSaveSuccess, onSaveError);
            } else {
                T_order_info.save(vm.t_order_info, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('angelaApp:t_order_infoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
