(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('t-cart-info', {
            parent: 'entity',
            url: '/t-cart-info',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_cart_info.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-cart-info/t-cart-infos.html',
                    controller: 'T_cart_infoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_cart_info');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('t-cart-info-detail', {
            parent: 't-cart-info',
            url: '/t-cart-info/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_cart_info.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-cart-info/t-cart-info-detail.html',
                    controller: 'T_cart_infoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_cart_info');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'T_cart_info', function($stateParams, T_cart_info) {
                    return T_cart_info.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 't-cart-info',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('t-cart-info-detail.edit', {
            parent: 't-cart-info-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-cart-info/t-cart-info-dialog.html',
                    controller: 'T_cart_infoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_cart_info', function(T_cart_info) {
                            return T_cart_info.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-cart-info.new', {
            parent: 't-cart-info',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-cart-info/t-cart-info-dialog.html',
                    controller: 'T_cart_infoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                productId: null,
                                productCount: null,
                                deliveryDate: null,
                                createdate: null,
                                message: null,
                                asiSageNo: null,
                                createDate: null,
                                createUser: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('t-cart-info', null, { reload: 't-cart-info' });
                }, function() {
                    $state.go('t-cart-info');
                });
            }]
        })
        .state('t-cart-info.edit', {
            parent: 't-cart-info',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-cart-info/t-cart-info-dialog.html',
                    controller: 'T_cart_infoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_cart_info', function(T_cart_info) {
                            return T_cart_info.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-cart-info', null, { reload: 't-cart-info' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-cart-info.delete', {
            parent: 't-cart-info',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-cart-info/t-cart-info-delete-dialog.html',
                    controller: 'T_cart_infoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['T_cart_info', function(T_cart_info) {
                            return T_cart_info.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-cart-info', null, { reload: 't-cart-info' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
