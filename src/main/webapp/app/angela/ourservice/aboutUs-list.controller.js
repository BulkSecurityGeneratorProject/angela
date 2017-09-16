(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('aboutUsListController', aboutUsListController);

    aboutUsListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function aboutUsListController ($scope, Principal, LoginService, $state) {

    }
})();
