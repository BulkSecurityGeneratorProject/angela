(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_order_infoController', T_order_infoController);

    T_order_infoController.$inject = ['T_order_info'];

    function T_order_infoController(T_order_info) {

        var vm = this;

        vm.t_order_infos = [];

        loadAll();

        function loadAll() {
            T_order_info.query(function(result) {
                vm.t_order_infos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
