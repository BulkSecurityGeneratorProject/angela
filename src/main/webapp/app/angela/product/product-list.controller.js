(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('ProductListController', ProductListController);

    ProductListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function ProductListController ($scope, Principal, LoginService, $state) {

    }
})();
