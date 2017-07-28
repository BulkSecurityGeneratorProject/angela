(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('TesterminalController', TesterminalController);

    TesterminalController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function TesterminalController ($scope, Principal, LoginService, $state) {

    }
})();
