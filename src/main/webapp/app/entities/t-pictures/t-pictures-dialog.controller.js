(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_picturesDialogController', T_picturesDialogController);

    T_picturesDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'T_pictures'];

    function T_picturesDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, T_pictures) {
        var vm = this;

        vm.t_pictures = entity;
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
            if (vm.t_pictures.id !== null) {
                T_pictures.update(vm.t_pictures, onSaveSuccess, onSaveError);
            } else {
                T_pictures.save(vm.t_pictures, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('angelaApp:t_picturesUpdate', result);
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
