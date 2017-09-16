(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('solutionsListController', solutionsListController);

    solutionsListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function solutionsListController ($scope, Principal, LoginService, $state) {

    }
})();
