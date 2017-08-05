(function() {
    'use strict';

    angular
        .module('angelaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('t-dictionary', {
            parent: 'entity',
            url: '/t-dictionary',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_dictionary.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-dictionary/t-dictionaries.html',
                    controller: 'T_dictionaryController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_dictionary');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('t-dictionary-detail', {
            parent: 't-dictionary',
            url: '/t-dictionary/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'angelaApp.t_dictionary.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/t-dictionary/t-dictionary-detail.html',
                    controller: 'T_dictionaryDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('t_dictionary');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'T_dictionary', function($stateParams, T_dictionary) {
                    return T_dictionary.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 't-dictionary',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('t-dictionary-detail.edit', {
            parent: 't-dictionary-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-dictionary/t-dictionary-dialog.html',
                    controller: 'T_dictionaryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_dictionary', function(T_dictionary) {
                            return T_dictionary.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-dictionary.new', {
            parent: 't-dictionary',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-dictionary/t-dictionary-dialog.html',
                    controller: 'T_dictionaryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dictName: null,
                                dictKey: null,
                                dictVal: null,
                                dictDes: null,
                                createDate: null,
                                isDelete: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('t-dictionary', null, { reload: 't-dictionary' });
                }, function() {
                    $state.go('t-dictionary');
                });
            }]
        })
        .state('t-dictionary.edit', {
            parent: 't-dictionary',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-dictionary/t-dictionary-dialog.html',
                    controller: 'T_dictionaryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['T_dictionary', function(T_dictionary) {
                            return T_dictionary.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-dictionary', null, { reload: 't-dictionary' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('t-dictionary.delete', {
            parent: 't-dictionary',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/t-dictionary/t-dictionary-delete-dialog.html',
                    controller: 'T_dictionaryDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['T_dictionary', function(T_dictionary) {
                            return T_dictionary.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('t-dictionary', null, { reload: 't-dictionary' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
