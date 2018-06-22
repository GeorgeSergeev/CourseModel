(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('TeacherDeleteController',TeacherDeleteController);

    TeacherDeleteController.$inject = ['$uibModalInstance', 'entity', 'Teacher'];

    function TeacherDeleteController($uibModalInstance, entity, Teacher) {
        var vm = this;

        vm.teacher = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Teacher.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
