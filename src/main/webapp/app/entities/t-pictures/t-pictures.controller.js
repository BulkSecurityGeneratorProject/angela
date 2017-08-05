(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_picturesController', T_picturesController);

    T_picturesController.$inject = ['T_pictures'];

    function T_picturesController(T_pictures) {

        var vm = this;

        vm.t_pictures = [];

        loadAll();

        function loadAll() {
            T_pictures.query(function(result) {
                vm.t_pictures = result;
                vm.searchQuery = null;
            });
        }
    }
})();
