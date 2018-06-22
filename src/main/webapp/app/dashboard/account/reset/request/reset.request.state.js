(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('requestReset', {
            parent: 'account',
            url: '/reset/request',
            data: {
                authorities: []
            },
            views: {
                'login@': {
                    templateUrl: 'app/dashboard/account/reset/request/reset.request.html',
                    controller: 'RequestResetController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
