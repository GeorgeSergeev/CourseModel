(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('TeacherController', TeacherController);

    TeacherController.$inject = ['Teacher'];

    function TeacherController(Teacher) {

        var vm = this;

        vm.teachers = [];

        loadAll();

        function loadAll() {
            Teacher.query(function(result) {
                vm.teachers = result;
                vm.searchQuery = null;
            });
        }
    }
})();
