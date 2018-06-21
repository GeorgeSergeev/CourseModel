(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('course', {
            parent: 'dashboard',
            url: '/course',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Courses'
            },
            views: {
                'content@': {
                    templateUrl: 'app/dashboard/entities/course/courses.html',
                    controller: 'CourseController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('course-detail', {
            parent: 'course',
            url: '/course/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Course'
            },
            views: {
                'content@': {
                    templateUrl: 'app/dashboard/entities/course/course-detail.html',
                    controller: 'CourseDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Course', function($stateParams, Course) {
                    return Course.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'course',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('course-detail.edit', {
            parent: 'course-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/course/course-dialog.html',
                    controller: 'CourseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Course', function(Course) {
                            return Course.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course.new', {
            parent: 'course',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/course/course-dialog.html',
                    controller: 'CourseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                number: null,
                                cost: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('course', null, { reload: 'course' });
                }, function() {
                    $state.go('course');
                });
            }]
        })
        .state('course.edit', {
            parent: 'course',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/course/course-dialog.html',
                    controller: 'CourseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Course', function(Course) {
                            return Course.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course', null, { reload: 'course' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('course.delete', {
            parent: 'course',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/dashboard/entities/course/course-delete-dialog.html',
                    controller: 'CourseDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Course', function(Course) {
                            return Course.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('course', null, { reload: 'course' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
