(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('caseStudyListController', caseStudyListController);

    caseStudyListController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function caseStudyListController ($scope, Principal, LoginService, $state) {

    }
})();
