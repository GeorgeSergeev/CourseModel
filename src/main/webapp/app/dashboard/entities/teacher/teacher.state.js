(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('teacher', {
            parent: 'dashboard',
            url: '/teacher',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Teachers'
            },
            views: {
                'content@': {
                    templateUrl: 'app/dashboard/entities/teacher/teachers.html',
                    controller: 'TeacherController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('teacher-detail', {
            parent: 'teacher',
            url: '/teacher/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Teacher'
            },
            views: {
                'content@': {
                    templateUrl: 'app/dashboard/entities/teacher/teacher-detail.html',
                    controller: 'TeacherDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Teacher', function($stateParams, Teacher) {
                    return Teacher.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'teacher',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('teacher-detail.edit', {
            parent: 'teacher-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/teacher/teacher-dialog.html',
                    controller: 'TeacherDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Teacher', function(Teacher) {
                            return Teacher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('teacher.new', {
            parent: 'teacher',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/teacher/teacher-dialog.html',
                    controller: 'TeacherDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                address: null,
                                phonenumber: null,
                                salary: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('teacher', null, { reload: 'teacher' });
                }, function() {
                    $state.go('teacher');
                });
            }]
        })
        .state('teacher.edit', {
            parent: 'teacher',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/teacher/teacher-dialog.html',
                    controller: 'TeacherDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Teacher', function(Teacher) {
                            return Teacher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('teacher', null, { reload: 'teacher' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('teacher.delete', {
            parent: 'teacher',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/teacher/teacher-delete-dialog.html',
                    controller: 'TeacherDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Teacher', function(Teacher) {
                            return Teacher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('teacher', null, { reload: 'teacher' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
