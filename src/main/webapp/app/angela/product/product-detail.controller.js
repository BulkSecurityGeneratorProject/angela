(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('ProductDetailController', ProductDetailController);

    ProductDetailController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function ProductDetailController ($scope, Principal, LoginService, $state) {

    }
})();
