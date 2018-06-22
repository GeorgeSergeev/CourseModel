(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('dashboard', {
            parent: 'app',
            abstract: true,
            bustCache: true,
            views: {
                'navbar@': {
                    templateUrl: 'app/dashboard/layouts/navbar/dashboard.navbar.html',
                    controller: 'DashboardNavbarController',
                    controllerAs: 'vm'
                },
                'sidebar@': {
                    templateUrl: 'app/dashboard/layouts/sidebar/dashboard.sidebar.html',
                    controller: 'DashboardSidebarController',
                    controllerAs: 'vm'
                },
                'footer@': {
                    templateUrl: 'app/dashboard/layouts/footer/dashboard.footer.html',
                    controller: 'DashboardFooterController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ]
            }
        });
    }
})();
