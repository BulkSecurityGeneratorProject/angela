(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_reviewDialogController', T_reviewDialogController);

    T_reviewDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'T_review'];

    function T_reviewDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, T_review) {
        var vm = this;

        vm.t_review = entity;
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
            if (vm.t_review.id !== null) {
                T_review.update(vm.t_review, onSaveSuccess, onSaveError);
            } else {
                T_review.save(vm.t_review, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('angelaApp:t_reviewUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createdate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
