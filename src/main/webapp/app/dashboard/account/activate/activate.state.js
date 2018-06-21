(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('activate', {
            parent: 'account',
            url: '/activate?key',
            data: {
                authorities: [],
                pageTitle: 'Activation'
            },
            views: {
                'login@': {
                    templateUrl: 'app/dashboard/account/activate/activate.html',
                    controller: 'ActivationController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
