(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('sessions', {
            parent: 'account',
            url: '/sessions',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Sessions'
            },
            views: {
                'content@': {
                    templateUrl: 'app/dashboard/account/sessions/sessions.html',
                    controller: 'SessionsController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
