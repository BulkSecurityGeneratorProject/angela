(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('artDesignListController', artDesignListController);

    artDesignListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function artDesignListController ($scope, Principal, LoginService, $state) {

    }
})();
