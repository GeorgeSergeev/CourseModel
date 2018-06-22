(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
            $stateProvider.state('dashboard-home', {
            parent: 'dashboard',
            url: '/dashboard',
            data: {
                authorities: ['ROLE_ADMIN','ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/dashboard/home/dashboard.home.html'
                }
            }
        });
    }
})();
