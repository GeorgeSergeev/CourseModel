(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('error', {
                url: '/error',
                data: {
                    authorities: [],
                    pageTitle: 'Error page!'
                },
                views: {
                    'login@': {
                        templateUrl: 'app/layouts/error/error.html'
                    }
                }
            })
            .state('accessdenied', {
                url: '/accessdenied',
                data: {
                    authorities: []
                },
                views: {
                    'login@': {
                        templateUrl: 'app/layouts/error/accessdenied.html'
                    }
                }
            });
    }
})();
