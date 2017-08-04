(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_categoryController', T_categoryController);

    T_categoryController.$inject = ['T_category'];

    function T_categoryController(T_category) {

        var vm = this;

        vm.t_categories = [];

        loadAll();

        function loadAll() {
            T_category.query(function(result) {
                vm.t_categories = result;
                vm.searchQuery = null;
            });
        }
    }
})();
