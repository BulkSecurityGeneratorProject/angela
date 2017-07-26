(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('angela', {
            abstract: true,
            parent: 'app'
        });
    }
})();
