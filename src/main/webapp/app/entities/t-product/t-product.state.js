(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('t-product', {
            parent: 'entity',
            url: '/t-product',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_product.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-product/t-products.html',
                    controller: 'T_productController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_product');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('t-product-detail', {
            parent: 't-product',
            url: '/t-product/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_product.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-product/t-product-detail.html',
                    controller: 'T_productDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_product');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'T_product', function($stateParams, T_product) {
                    return T_product.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 't-product',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('t-product-detail.edit', {
            parent: 't-product-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-product/t-product-dialog.html',
                    controller: 'T_productDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_product', function(T_product) {
                            return T_product.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-product.new', {
            parent: 't-product',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-product/t-product-dialog.html',
                    controller: 'T_productDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                productSn: null,
                                categoryId: null,
                                productName: null,
                                clickCount: null,
                                marketPrice: null,
                                productPrice: null,
                                brief: null,
                                productDetails: null,
                                isONSale: null,
                                isHot: null,
                                sortOrder: null,
                                productUnit: null,
                                stock: null,
                                productColor: null,
                                productArea: null,
                                material: null,
                                proTag: null,
                                size: null,
                                sellCount: null,
                                createUser: null,
                                createDate: null,
                                lastUpdate: null,
                                productStatus: null,
                                isDelete: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('t-product', null, { reload: 't-product' });
                }, function() {
                    $state.go('t-product');
                });
            }]
        })
        .state('t-product.edit', {
            parent: 't-product',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-product/t-product-dialog.html',
                    controller: 'T_productDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_product', function(T_product) {
                            return T_product.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-product', null, { reload: 't-product' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-product.delete', {
            parent: 't-product',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-product/t-product-delete-dialog.html',
                    controller: 'T_productDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['T_product', function(T_product) {
                            return T_product.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-product', null, { reload: 't-product' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
