(function () {
    'use strict';

    angular
        .module('angelaApp')
        .controller('CaseDetailController', CaseDetailController);

        CaseDetailController.$inject = ['$scope', '$rootScope', 'Principal', 'LoginService', '$state', '$stateParams', 'cases', 'PROD'];

    function CaseDetailController($scope, $rootScope, Principal, LoginService, $state, $stateParams, cases, PROD ) {
        var vm = this;
        vm.PROD = PROD;
        console.log($stateParams)
        loadAll($stateParams['id']);
        function loadAll(id){
            var caseDeatil = cases.getCaseDeatil({ "id": (id || '') });
            caseDeatil.then(function(cases) {
                console.log(cases);
                 vm.caseDeatil = cases['data'].cases;
                 console.log( vm.caseDeatil);
            })
        }

    }
})();
