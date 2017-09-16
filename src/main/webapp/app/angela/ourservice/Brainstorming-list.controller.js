(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('BrainstormingListController', BrainstormingListController);

    BrainstormingListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function BrainstormingListController ($scope, Principal, LoginService, $state) {

    }
})();
