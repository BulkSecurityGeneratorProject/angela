(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('testerminal', {
            abstract: true,
            parent: 'angela'
        })
        .state('testerminal-detail', {
            parent: 'testerminal',
            url: '/Testerminal',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/angela/testerminal/Testerminal.html',
                    controller: 'TesterminalController',
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
