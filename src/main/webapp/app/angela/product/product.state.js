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
            url: '/product-list',
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
            url: '/product-detail',
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
        });
    }
})();
