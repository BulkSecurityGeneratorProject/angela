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
            url: '/case-list?{orderby}',
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
        .state('case-detail', {
            parent: 'case',
            url: '/case-detail?{id}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/angela/case/case-detail.html',
                    controller: 'CaseDetailController',
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
