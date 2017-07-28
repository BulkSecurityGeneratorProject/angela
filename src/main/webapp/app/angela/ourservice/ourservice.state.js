(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('ourservice', {
            abstract: true,
            parent: 'angela'
        })
        .state('ourservice-list', {
            parent: 'ourservice',
            url: '/ourservice-list',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/angela/ourservice/ourservice-list.html',
                    controller: 'OurserviceListController',
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
