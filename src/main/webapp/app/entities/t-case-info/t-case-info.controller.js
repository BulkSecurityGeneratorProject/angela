(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_case_infoController', T_case_infoController);

    T_case_infoController.$inject = ['T_case_info'];

    function T_case_infoController(T_case_info) {

        var vm = this;

        vm.t_case_infos = [];

        loadAll();

        function loadAll() {
            T_case_info.query(function(result) {
                vm.t_case_infos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
