(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('StudentDialogController', StudentDialogController);

    StudentDialogController.$inject = ['$timeout', '$http','$scope', '$stateParams', '$uibModalInstance', 'entity', 'Student', 'Course'];

    function StudentDialogController ($timeout, $http,$scope, $stateParams, $uibModalInstance, entity, Student, Course) {
        var vm = this;

        vm.student = entity;
        vm.clear = clear;
        vm.save = save;
        vm.saveStudent = saveStudent;
        vm.courses = [];
        vm.currentStudent = [];
        $http.get('api/courses')
            .then(function onSuccess(response){
                vm.courses = response.data;
                vm.courses.forEach(function (value) {
                    value.checked = false;
                });
                courseInStudent();
            });
        function courseInStudent() {
            vm.student.courses.forEach(function (value) {
                vm.courses.forEach(function (value1) {
                    if(value.id === value1.id){
                        value1.checked = true;
                    }
                });
            });
        }
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        function saveStudent() {
            vm.trueCourses = [];
            vm.courses.forEach(function (value) {
                if(value.checked === true)
                    vm.trueCourses.push(value);
            });
            vm.currentStudent = {
                "id": vm.student.id,
                "name": vm.student.name,
                "address": vm.student.address,
                "phone": vm.student.phone,
                "email": vm.student.email,
                "idstudentbook": vm.student.idstudentbook,
                "avaragemark": vm.student.avaragemark,
                "courses": vm.trueCourses
            };

            save();

        }
        function save () {
            vm.isSaving = true;
            if (vm.currentStudent.id !== null) {
                Student.update(vm.currentStudent, onSaveSuccess, onSaveError);
            } else {
                Student.save(vm.currentStudent, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('courseModelApp:studentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
