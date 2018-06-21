(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('register', {
            parent: 'account',
            url: '/register',
            data: {
                authorities: [],
                pageTitle: 'Registration'
            },
            views: {
                'login@': {
                    templateUrl: 'app/dashboard/account/register/register.html',
                    controller: 'RegisterController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
