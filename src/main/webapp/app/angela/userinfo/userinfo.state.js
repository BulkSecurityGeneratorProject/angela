(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('userinfo', {
            abstract: true,
            parent: 'angela'
        })
        .state('userinfo-list', {
            parent: 'userinfo',
            url: '/userinfo-list',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/angela/userinfo/userinfo-list.html',
                    controller: 'UserinfoListController',
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
