(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('CourseController', CourseController);

    CourseController.$inject = ['Course'];

    function CourseController(Course) {

        var vm = this;

        vm.courses = [];

        loadAll();

        function loadAll() {
            Course.query(function(result) {
                vm.courses = result;
                vm.searchQuery = null;
            });
        }
    }
})();
