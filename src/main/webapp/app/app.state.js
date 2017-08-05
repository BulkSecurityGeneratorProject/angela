(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('app', {
            abstract: true,
            views: {
                'navbar@': {
                    templateUrl: 'app/layouts/navbar/navbar.html',
                    controller: 'NavbarController',
                    controllerAs: 'vm'
                },
                'adminNavbar@': {
                    templateUrl: 'app/admin/navbar/navbar.html',
                    controller: 'AdminNavbarController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                }]
            }
        })
        .state('full-login', {
            //  全屏登陆
            parent: 'app',
            url: '/full-login',
            data: {
            },
            views: {
                'content@': {
                    templateUrl: 'app/components/full-login/full-login.html',
                    controller: 'FullLoginController',
                    controllerAs: 'vm'
                }
            }
        })
    }
})();
