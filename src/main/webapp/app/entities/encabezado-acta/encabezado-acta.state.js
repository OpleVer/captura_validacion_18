(function() {
    'use strict';

    angular
        .module('capturaValidacion18App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('encabezado-acta', {
            parent: 'entity',
            url: '/encabezado-acta',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Encabezado_actas'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/encabezado-acta/encabezado-actas.html',
                    controller: 'Encabezado_actaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('encabezado-acta-detail', {
            parent: 'encabezado-acta',
            url: '/encabezado-acta/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Encabezado_acta'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/encabezado-acta/encabezado-acta-detail.html',
                    controller: 'Encabezado_actaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Encabezado_acta', function($stateParams, Encabezado_acta) {
                    return Encabezado_acta.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'encabezado-acta',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('encabezado-acta-detail.edit', {
            parent: 'encabezado-acta-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/encabezado-acta/encabezado-acta-dialog.html',
                    controller: 'Encabezado_actaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Encabezado_acta', function(Encabezado_acta) {
                            return Encabezado_acta.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('encabezado-acta.new', {
            parent: 'encabezado-acta',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/encabezado-acta/encabezado-acta-dialog.html',
                    controller: 'Encabezado_actaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                distrito: null,
                                tipo_acta: null,
                                seccion: null,
                                casilla: null,
                                digitalizacion: null,
                                estatus: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('encabezado-acta', null, { reload: 'encabezado-acta' });
                }, function() {
                    $state.go('encabezado-acta');
                });
            }]
        })
        .state('encabezado-acta.edit', {
            parent: 'encabezado-acta',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/encabezado-acta/encabezado-acta-dialog.html',
                    controller: 'Encabezado_actaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Encabezado_acta', function(Encabezado_acta) {
                            return Encabezado_acta.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('encabezado-acta', null, { reload: 'encabezado-acta' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('encabezado-acta.delete', {
            parent: 'encabezado-acta',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/encabezado-acta/encabezado-acta-delete-dialog.html',
                    controller: 'Encabezado_actaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Encabezado_acta', function(Encabezado_acta) {
                            return Encabezado_acta.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('encabezado-acta', null, { reload: 'encabezado-acta' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
