(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('qualityControlListController', qualityControlListController);

    qualityControlListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function qualityControlListController ($scope, Principal, LoginService, $state) {

    }
})();
