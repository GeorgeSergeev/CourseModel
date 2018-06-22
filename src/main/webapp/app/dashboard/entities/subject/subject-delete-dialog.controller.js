(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('SubjectDeleteController',SubjectDeleteController);

    SubjectDeleteController.$inject = ['$uibModalInstance', 'entity', 'Subject'];

    function SubjectDeleteController($uibModalInstance, entity, Subject) {
        var vm = this;

        vm.subject = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Subject.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
