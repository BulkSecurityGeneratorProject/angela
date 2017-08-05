(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('UserinfoDetailController', UserinfoDetailController);

    UserinfoDetailController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function UserinfoDetailController ($scope, Principal, LoginService, $state) {

    }
})();
