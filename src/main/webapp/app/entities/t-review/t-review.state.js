(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('t-review', {
            parent: 'entity',
            url: '/t-review',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_review.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-review/t-reviews.html',
                    controller: 'T_reviewController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_review');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('t-review-detail', {
            parent: 't-review',
            url: '/t-review/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_review.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-review/t-review-detail.html',
                    controller: 'T_reviewDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_review');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'T_review', function($stateParams, T_review) {
                    return T_review.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 't-review',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('t-review-detail.edit', {
            parent: 't-review-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-review/t-review-dialog.html',
                    controller: 'T_reviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_review', function(T_review) {
                            return T_review.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-review.new', {
            parent: 't-review',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-review/t-review-dialog.html',
                    controller: 'T_reviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userId: null,
                                productId: null,
                                content: null,
                                createdate: null,
                                isUseful: null,
                                remarks: null,
                                isDelete: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('t-review', null, { reload: 't-review' });
                }, function() {
                    $state.go('t-review');
                });
            }]
        })
        .state('t-review.edit', {
            parent: 't-review',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-review/t-review-dialog.html',
                    controller: 'T_reviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_review', function(T_review) {
                            return T_review.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-review', null, { reload: 't-review' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-review.delete', {
            parent: 't-review',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-review/t-review-delete-dialog.html',
                    controller: 'T_reviewDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['T_review', function(T_review) {
                            return T_review.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-review', null, { reload: 't-review' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
