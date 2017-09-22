(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('BrainstormingListController', BrainstormingListController);

    BrainstormingListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function BrainstormingListController ($scope, Principal, LoginService, $state) {
        var vm = this;

        vm.windowHeight = window.innerHeight;
		vm.headerHeight = 80;
		vm.footerHeight = 40;
/*    	vm.directive('autoHeight', function($window){
		  return {
		    restrict: 'A',
		    scope: {},
		    function($scope, element, attrs){
		      var winowHeight = $window.innerHeight;
		      var headerHeight = 80;
		      var footerHeight = 40;
		      console.log(element);
		      element.css('min-height', (winowHeight - headerHeight - footerHeight) + 'px');  
		    }
		  };
		});*/
    }
})();
