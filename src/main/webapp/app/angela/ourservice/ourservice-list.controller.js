(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('OurserviceListController', OurserviceListController);

    OurserviceListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function OurserviceListController ($scope, Principal, LoginService, $state) {

    	var vm = this;

        vm.windowHeight = window.innerHeight;
		vm.headerHeight = 80;
		vm.footerHeight = 40;

    }
})();
