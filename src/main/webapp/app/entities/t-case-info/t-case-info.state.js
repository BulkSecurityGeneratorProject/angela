(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('t-case-info', {
            parent: 'entity',
            url: '/t-case-info',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_case_info.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-case-info/t-case-infos.html',
                    controller: 'T_case_infoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_case_info');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('t-case-info-detail', {
            parent: 't-case-info',
            url: '/t-case-info/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_case_info.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-case-info/t-case-info-detail.html',
                    controller: 'T_case_infoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_case_info');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'T_case_info', function($stateParams, T_case_info) {
                    return T_case_info.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 't-case-info',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('t-case-info-detail.edit', {
            parent: 't-case-info-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-case-info/t-case-info-dialog.html',
                    controller: 'T_case_infoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_case_info', function(T_case_info) {
                            return T_case_info.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-case-info.new', {
            parent: 't-case-info',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-case-info/t-case-info-dialog.html',
                    controller: 'T_case_infoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                title: null,
                                brief: null,
                                description: null,
                                createdate: null,
                                isUseful: null,
                                remarks: null,
                                isDelete: null,
                                createDate: null,
                                createUser: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('t-case-info', null, { reload: 't-case-info' });
                }, function() {
                    $state.go('t-case-info');
                });
            }]
        })
        .state('t-case-info.edit', {
            parent: 't-case-info',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-case-info/t-case-info-dialog.html',
                    controller: 'T_case_infoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_case_info', function(T_case_info) {
                            return T_case_info.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-case-info', null, { reload: 't-case-info' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-case-info.delete', {
            parent: 't-case-info',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-case-info/t-case-info-delete-dialog.html',
                    controller: 'T_case_infoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['T_case_info', function(T_case_info) {
                            return T_case_info.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-case-info', null, { reload: 't-case-info' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
