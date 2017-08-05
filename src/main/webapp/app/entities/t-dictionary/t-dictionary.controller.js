(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_dictionaryController', T_dictionaryController);

    T_dictionaryController.$inject = ['T_dictionary'];

    function T_dictionaryController(T_dictionary) {

        var vm = this;

        vm.t_dictionaries = [];

        loadAll();

        function loadAll() {
            T_dictionary.query(function(result) {
                vm.t_dictionaries = result;
                vm.searchQuery = null;
            });
        }
    }
})();
