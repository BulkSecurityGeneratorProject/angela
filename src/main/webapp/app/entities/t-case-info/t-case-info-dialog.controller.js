(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_case_infoDialogController', T_case_infoDialogController);

    T_case_infoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'T_case_info'];

    function T_case_infoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, T_case_info) {
        var vm = this;

        vm.t_case_info = entity;
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
            if (vm.t_case_info.id !== null) {
                T_case_info.update(vm.t_case_info, onSaveSuccess, onSaveError);
            } else {
                T_case_info.save(vm.t_case_info, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('angelaApp:t_case_infoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createdate = false;
        vm.datePickerOpenStatus.createDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
