(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('StudentController', StudentController);

    StudentController.$inject = ['Student'];

    function StudentController(Student) {

        var vm = this;

        vm.students = [];

        loadAll();

        function loadAll() {
            Student.query(function(result) {
                vm.students = result;
                vm.searchQuery = null;
            });
        }
    }
})();
