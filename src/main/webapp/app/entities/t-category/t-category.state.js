(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('t-category', {
            parent: 'entity',
            url: '/t-category',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_category.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-category/t-categories.html',
                    controller: 'T_categoryController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_category');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('t-category-detail', {
            parent: 't-category',
            url: '/t-category/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_category.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-category/t-category-detail.html',
                    controller: 'T_categoryDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_category');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'T_category', function($stateParams, T_category) {
                    return T_category.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 't-category',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('t-category-detail.edit', {
            parent: 't-category-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-category/t-category-dialog.html',
                    controller: 'T_categoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_category', function(T_category) {
                            return T_category.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-category.new', {
            parent: 't-category',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-category/t-category-dialog.html',
                    controller: 'T_categoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                catName: null,
                                parentId: null,
                                depth: null,
                                status: null,
                                priority: null,
                                isDelete: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('t-category', null, { reload: 't-category' });
                }, function() {
                    $state.go('t-category');
                });
            }]
        })
        .state('t-category.edit', {
            parent: 't-category',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-category/t-category-dialog.html',
                    controller: 'T_categoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_category', function(T_category) {
                            return T_category.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-category', null, { reload: 't-category' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-category.delete', {
            parent: 't-category',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-category/t-category-delete-dialog.html',
                    controller: 'T_categoryDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['T_category', function(T_category) {
                            return T_category.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-category', null, { reload: 't-category' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
