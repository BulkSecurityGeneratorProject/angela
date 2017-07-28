(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('OurserviceListController', OurserviceListController);

    OurserviceListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function OurserviceListController ($scope, Principal, LoginService, $state) {

    }
})();
