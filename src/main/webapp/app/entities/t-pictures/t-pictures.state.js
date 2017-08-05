(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('t-pictures', {
            parent: 'entity',
            url: '/t-pictures',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_pictures.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-pictures/t-pictures.html',
                    controller: 'T_picturesController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_pictures');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('t-pictures-detail', {
            parent: 't-pictures',
            url: '/t-pictures/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_pictures.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-pictures/t-pictures-detail.html',
                    controller: 'T_picturesDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_pictures');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'T_pictures', function($stateParams, T_pictures) {
                    return T_pictures.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 't-pictures',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('t-pictures-detail.edit', {
            parent: 't-pictures-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-pictures/t-pictures-dialog.html',
                    controller: 'T_picturesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_pictures', function(T_pictures) {
                            return T_pictures.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-pictures.new', {
            parent: 't-pictures',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-pictures/t-pictures-dialog.html',
                    controller: 'T_picturesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                productId: null,
                                imageUrl: null,
                                imageUrlSmall: null,
                                createDate: null,
                                createUser: null,
                                imageType: null,
                                isDelete: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('t-pictures', null, { reload: 't-pictures' });
                }, function() {
                    $state.go('t-pictures');
                });
            }]
        })
        .state('t-pictures.edit', {
            parent: 't-pictures',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-pictures/t-pictures-dialog.html',
                    controller: 'T_picturesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_pictures', function(T_pictures) {
                            return T_pictures.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-pictures', null, { reload: 't-pictures' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-pictures.delete', {
            parent: 't-pictures',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-pictures/t-pictures-delete-dialog.html',
                    controller: 'T_picturesDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['T_pictures', function(T_pictures) {
                            return T_pictures.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-pictures', null, { reload: 't-pictures' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
