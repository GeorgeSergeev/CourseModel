(function () {
    'use strict';

    angular
        .module('courseModelApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider.state('admin', {
            abstract: true,
            parent: 'dashboard'
        });
    }
})();
