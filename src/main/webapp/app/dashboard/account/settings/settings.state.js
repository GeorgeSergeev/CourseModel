(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('settings', {
            parent: 'account',
            url: '/settings',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Settings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/dashboard/account/settings/settings.html',
                    controller: 'SettingsController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
