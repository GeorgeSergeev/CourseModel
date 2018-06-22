(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('StudentDetailController', StudentDetailController);

    StudentDetailController.$inject = ['$scope', '$state', '$http', '$rootScope', '$stateParams', 'previousState', 'entity', 'Student', 'Course'];

    function StudentDetailController($scope, $state, $http, $rootScope, $stateParams, previousState, entity, Student, Course) {
        var vm = this;

        vm.student = entity;
        vm.previousState = previousState.name;
        vm.currentStudent = [];
        vm.saveStudent = saveStudent;
        vm.courses = [];
        var unsubscribe = $rootScope.$on('courseModelApp:studentUpdate', function(event, result) {
            vm.student = result;
        });
        $scope.$on('$destroy', unsubscribe);

        $http.get('api/students/' + vm.student.id)
            .then(function onSuccess(response) {
                vm.student = response.data;
                console.log("get students ",vm.student);

            });
        $http.get('api/courses')
            .then(function onSuccess(response){
                vm.courses = response.data;
                vm.courses.forEach(function (value) {
                    value.checked = false;
                });
                courseInStudent();
                // console.log(vm.courses);
            });
        function courseInStudent() {
            vm.student.courses.forEach(function (value) {
                vm.courses.forEach(function (value1) {
                    if(value.id === value1.id){
                        value1.checked = true;
                    }
                });
            });
            console.log(vm.courses);
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

            vm.isSaving = true;
            if (vm.student.id !== null) {
                Student.update(vm.currentStudent);
                $state.reload();
            } else {
                Student.save(vm.currentStudent);
                $state.reload();
            }

        }
    }
})();
