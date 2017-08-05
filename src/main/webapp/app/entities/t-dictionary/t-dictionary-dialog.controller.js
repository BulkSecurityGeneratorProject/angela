(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_dictionaryDialogController', T_dictionaryDialogController);

    T_dictionaryDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'T_dictionary'];

    function T_dictionaryDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, T_dictionary) {
        var vm = this;

        vm.t_dictionary = entity;
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
            if (vm.t_dictionary.id !== null) {
                T_dictionary.update(vm.t_dictionary, onSaveSuccess, onSaveError);
            } else {
                T_dictionary.save(vm.t_dictionary, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('angelaApp:t_dictionaryUpdate', result);
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
