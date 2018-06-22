(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('SubjectDialogController', SubjectDialogController);

    SubjectDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Subject', 'Course'];

    function SubjectDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Subject, Course) {
        var vm = this;

        vm.subject = entity;
        vm.clear = clear;
        vm.save = save;
        vm.courses = Course.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.subject.id !== null) {
                Subject.update(vm.subject, onSaveSuccess, onSaveError);
            } else {
                Subject.save(vm.subject, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('courseModelApp:subjectUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
