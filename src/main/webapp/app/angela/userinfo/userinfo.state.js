(function () {
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
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('home');
                        return $translate.refresh();
                    }]
                }
            })
            .state('userinfo-detail.edit', {
                parent: 'userinfo-detail',
                url: '/userinfo/detail/edit',
                data: {
                    authorities: ['ROLE_USER']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/angela/userinfo/userinfo-update.html',
                        controller: 'UserInfoUpdateController',
                        controllerAs: 'vm',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            Data: ['Principal', function (Principal) {
                                return Principal.identity().then(function (account) {
                                   return  account;
                                });
                            }]
                        }
                    })
                }]
            })
    }
})();
