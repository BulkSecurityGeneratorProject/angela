(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('mycart', {
            abstract: true,
            parent: 'angela'
        })
        .state('mycart-list', {
            parent: 'mycart',
            url: '/mycart-list',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/angela/mycart/mycart-list.html',
                    controller: 'MycartListController',
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
