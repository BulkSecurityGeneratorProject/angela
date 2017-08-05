(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('UserinfoListController', UserinfoListController);

    UserinfoListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function UserinfoListController ($scope, Principal, LoginService, $state) {

    }
})();
