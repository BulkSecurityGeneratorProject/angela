(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('product', {
            abstract: true,
            parent: 'angela'
        })
        .state('product-list', {
            parent: 'product',
            url: '/product-list?{orderby}&&{type}&&{title}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/angela/product/product-list.html',
                    controller: 'ProductListController',
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
        .state('product-detail', {
            parent: 'product',
            url: '/product-detail?{id}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/angela/product/product-detail.html',
                    controller: 'ProductDetailController',
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
        .state('product-detail-reviews', {
            parent: 'product-detail',
            url: '/product-detail-reviews',
            data: {
                authorities: []
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/angela/product/product-detail-reviews.html',
                    controller: 'productDetailReviewsController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'mg',
                    resolve: {

                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: true });
                }, function() {
                    $state.go('^', {}, { reload: true });
                });
            }],
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]
            }
        });;
    }
})();
