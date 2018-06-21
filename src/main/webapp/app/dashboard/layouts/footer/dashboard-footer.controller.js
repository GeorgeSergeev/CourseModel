(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('DashboardFooterController', DashboardFooterController);

    // DashboardFooterController.$inject = ['Company'];

    function DashboardFooterController () {
        var vm = this;

        vm.company = [];

        loadAll();

        function loadAll() {

            // Company.get(function (result) {
            //     vm.company = result;
            // });

            vm.company = '«ООО Курорт Эксперт»';
        }

    }
})();
