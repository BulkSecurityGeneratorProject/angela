(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_cart_infoController', T_cart_infoController);

    T_cart_infoController.$inject = ['T_cart_info'];

    function T_cart_infoController(T_cart_info) {

        var vm = this;

        vm.t_cart_infos = [];

        loadAll();

        function loadAll() {
            T_cart_info.query(function(result) {
                vm.t_cart_infos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
