(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'login@': {
                    templateUrl: 'app/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
