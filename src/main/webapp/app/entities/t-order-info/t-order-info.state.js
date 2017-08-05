(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('t-order-info', {
            parent: 'entity',
            url: '/t-order-info',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_order_info.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-order-info/t-order-infos.html',
                    controller: 'T_order_infoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_order_info');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('t-order-info-detail', {
            parent: 't-order-info',
            url: '/t-order-info/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_order_info.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-order-info/t-order-info-detail.html',
                    controller: 'T_order_infoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_order_info');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'T_order_info', function($stateParams, T_order_info) {
                    return T_order_info.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 't-order-info',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('t-order-info-detail.edit', {
            parent: 't-order-info-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-order-info/t-order-info-dialog.html',
                    controller: 'T_order_infoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_order_info', function(T_order_info) {
                            return T_order_info.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-order-info.new', {
            parent: 't-order-info',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-order-info/t-order-info-dialog.html',
                    controller: 'T_order_infoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                orderId: null,
                                productId: null,
                                productCount: null,
                                status: null,
                                connectStatus: null,
                                createDate: null,
                                createUser: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('t-order-info', null, { reload: 't-order-info' });
                }, function() {
                    $state.go('t-order-info');
                });
            }]
        })
        .state('t-order-info.edit', {
            parent: 't-order-info',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-order-info/t-order-info-dialog.html',
                    controller: 'T_order_infoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_order_info', function(T_order_info) {
                            return T_order_info.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-order-info', null, { reload: 't-order-info' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-order-info.delete', {
            parent: 't-order-info',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-order-info/t-order-info-delete-dialog.html',
                    controller: 'T_order_infoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['T_order_info', function(T_order_info) {
                            return T_order_info.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-order-info', null, { reload: 't-order-info' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
