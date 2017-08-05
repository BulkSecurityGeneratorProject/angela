(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('T_reviewController', T_reviewController);

    T_reviewController.$inject = ['T_review'];

    function T_reviewController(T_review) {

        var vm = this;

        vm.t_reviews = [];

        loadAll();

        function loadAll() {
            T_review.query(function(result) {
                vm.t_reviews = result;
                vm.searchQuery = null;
            });
        }
    }
})();
