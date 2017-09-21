(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('CaseListController', CaseListController);

        CaseListController.$inject = ['$scope', '$stateParams', 'Principal', 'LoginService', '$state', 'cases', 'PROD'];

    function CaseListController ($scope, $stateParams, Principal, LoginService, $state, cases, PROD) {
        var vm = this;
        vm.PROD = PROD;
        loadAll({OrderBy: $stateParams['orderby']});
        function loadAll(params){
            if(!params['OrderBy']) {
                params['OrderBy'] = 'createDate';
            }
            var caseListP = cases.getCaseList(params);
            caseListP.then(function(cases) {
                console.log(cases);
                 vm.caseList = cases['data'];
                 console.log( vm.caseList);
            })
        }
        


    }
})();
