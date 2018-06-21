(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('subject', {
            parent: 'dashboard',
            url: '/subject',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Subjects'
            },
            views: {
                'content@': {
                    templateUrl: 'app/dashboard/entities/subject/subjects.html',
                    controller: 'SubjectController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('subject-detail', {
            parent: 'subject',
            url: '/subject/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Subject'
            },
            views: {
                'content@': {
                    templateUrl: 'app/dashboard/entities/subject/subject-detail.html',
                    controller: 'SubjectDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Subject', function($stateParams, Subject) {
                    return Subject.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'subject',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('subject-detail.edit', {
            parent: 'subject-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/subject/subject-dialog.html',
                    controller: 'SubjectDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Subject', function(Subject) {
                            return Subject.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('subject.new', {
            parent: 'subject',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/subject/subject-dialog.html',
                    controller: 'SubjectDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                rating: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('subject', null, { reload: 'subject' });
                }, function() {
                    $state.go('subject');
                });
            }]
        })
        .state('subject.edit', {
            parent: 'subject',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/subject/subject-dialog.html',
                    controller: 'SubjectDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Subject', function(Subject) {
                            return Subject.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('subject', null, { reload: 'subject' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('subject.delete', {
            parent: 'subject',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/subject/subject-delete-dialog.html',
                    controller: 'SubjectDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Subject', function(Subject) {
                            return Subject.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('subject', null, { reload: 'subject' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
