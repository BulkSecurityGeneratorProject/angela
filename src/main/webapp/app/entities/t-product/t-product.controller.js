(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_productController', T_productController);

    T_productController.$inject = ['T_product'];

    function T_productController(T_product) {

        var vm = this;

        vm.t_products = [];

        loadAll();

        function loadAll() {
            T_product.query(function(result) {
                vm.t_products = result;
                vm.searchQuery = null;
            });
        }
    }
})();
