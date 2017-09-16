(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('financialSupportListController', financialSupportListController);

    financialSupportListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function financialSupportListController ($scope, Principal, LoginService, $state) {

    }
})();
