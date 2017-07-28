(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('CaseListController', CaseListController);

    CaseListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function CaseListController ($scope, Principal, LoginService, $state) {

    }
})();
