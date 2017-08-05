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
        .state('userinfo-detail', {
            parent: 'userinfo',
            url: '/userinfo-detail',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/angela/userinfo/userinfo-detail.html',
                    controller: 'UserinfoDetailController',
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
