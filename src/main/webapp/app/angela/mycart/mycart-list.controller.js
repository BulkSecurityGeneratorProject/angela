(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('MycartListController', MycartListController);

    MycartListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function MycartListController ($scope, Principal, LoginService, $state) {

    }
})();
