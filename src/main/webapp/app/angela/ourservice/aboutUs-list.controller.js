(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('aboutUsListController', aboutUsListController);

    aboutUsListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function aboutUsListController ($scope, Principal, LoginService, $state) {
    	var vm = this;
        vm.windowHeight = window.innerHeight;
		vm.headerHeight = 80;
		vm.footerHeight = 40;

    }
})();
