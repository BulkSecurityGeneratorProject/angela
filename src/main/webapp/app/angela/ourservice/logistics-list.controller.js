(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('logisticsListController', logisticsListController);

    logisticsListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function logisticsListController ($scope, Principal, LoginService, $state) {

    }
})();
