(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('SubjectController', SubjectController);

    SubjectController.$inject = ['Subject'];

    function SubjectController(Subject) {

        var vm = this;

        vm.subjects = [];

        loadAll();

        function loadAll() {
            Subject.query(function(result) {
                vm.subjects = result;
                vm.searchQuery = null;
            });
        }
    }
})();
