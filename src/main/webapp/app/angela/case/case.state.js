(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('case', {
            abstract: true,
            parent: 'angela'
        })
        .state('case-list', {
            parent: 'case',
            url: '/case-list',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/angela/case/case-list.html',
                    controller: 'CaseListController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]
            }
        })
    }
})();
