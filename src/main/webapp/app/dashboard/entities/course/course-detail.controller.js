(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('CourseDetailController', CourseDetailController);

    CourseDetailController.$inject = ['$scope', '$state', '$http','$rootScope', '$stateParams', 'previousState', 'entity', 'Course', 'Subject', 'Student', 'Teacher'];

    function CourseDetailController($scope, $state, $http, $rootScope, $stateParams, previousState, entity, Course, Subject, Student, Teacher) {
        var vm = this;

        vm.course = entity;
        vm.previousState = previousState.name;

        vm.save = save;
        vm.deleteStudents = deleteStudents;
        vm.request = entity;
        vm.studentForDelete = entity;
        vm.student = [];
        vm.deleteStudent = [];
        var unsubscribe = $rootScope.$on('courseModelApp:courseUpdate', function(event, result) {
            vm.course = result;
        });
        $scope.$on('$destroy', unsubscribe);

        $http.get('api/courses/'+vm.course.id+'/get-students')
            .then(function onSuccess(response) {
                vm.request = response.data;
                vm.request.forEach(function (value) {
                    value.checked = false;
                });
                console.log("get students ",vm.request);
            });
        $http.get('api/courses/'+vm.course.id+'/students-for-delete')
            .then(function onSuccess(response) {
                vm.studentForDelete = response.data;
                console.log("students for delete ",vm.studentForDelete);
                vm.studentForDelete.forEach(function (value) {
                    value.checked = true;
                });
            });

        function deleteStudents(studentId) {
            vm.studentForDelete.forEach(function (value) {
                if(value.id === studentId && value.checked === false){
                    value.courses.forEach(function (value1) {
                        if(value1.id === vm.course.id){
                            value.courses.splice(value1,1);
                        }
                    });
                    vm.deleteStudent.push(value);
                }
            });
            console.log("deletedStudent ",vm.deleteStudent);
            $http.put('/api/courses/'+vm.course.id+'/udpate-students',vm.deleteStudent,function onSuccess(response) {
                console.log('Success');
                console.log(response.data);
            });
            $state.reload();
        }
        function save(studentId) {
            vm.request.forEach(function (value) {
                if(value.id === studentId && value.checked === true){
                    value.courses.push({
                        "id" : vm.course.id,
                        "name" : vm.course.name,
                        "number" : vm.course.number,
                        "cost" : vm.course.cost
                    });
                    vm.student.push(value);
                }
            });
            console.log("updateStudent ",vm.student);
            $http.put('/api/courses/'+vm.course.id+'/udpate-students',vm.student,function onSuccess(response) {
                console.log('Success');
                console.log(response.data);
            });
            $state.reload();
        }


    }
})();
