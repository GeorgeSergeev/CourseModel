(function() {
    'use strict';

    angular
        .module('courseModelApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope','Principal', 'LoginService', '$state', '$rootScope', '$timeout', 'Auth'];

    function HomeController ($scope, Principal, LoginService, $state, $rootScope, $timeout, Auth) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        // vm.login = LoginService.open;
        vm.register = register;

        vm.authenticationError = false;
        vm.cancel = cancel;
        vm.credentials = {};
        vm.login = login;
        vm.password = null;
        vm.rememberMe = true;
        vm.username = null;
        vm.requestResetPassword = requestResetPassword;

        $timeout(function (){angular.element('#username').focus();});
        function cancel () {
            vm.credentials = {
                username: null,
                password: null,
                rememberMe: true
            };
            vm.authenticationError = false;
        }

        function login (event) {
            event.preventDefault();
            Auth.login({
                username: vm.username,
                password: vm.password,
                rememberMe: vm.rememberMe
            }).then(function () {
                vm.authenticationError = false;

                // if ($state.current.name === 'register' || $state.current.name === 'activate' ||
                //     $state.current.name === 'finishReset' || $state.current.name === 'requestReset') {
                // }

                $state.go('dashboard-home');
                $rootScope.$broadcast('authenticationSuccess');

                // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                // since login is successful, go to stored previousState and clear previousState
                if (Auth.getPreviousState()) {
                    var previousState = Auth.getPreviousState();
                    Auth.resetPreviousState();
                    $state.go(previousState.name, previousState.params);
                }
            }).catch(function () {
                vm.authenticationError = true;
            });
        }

        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

        function requestResetPassword () {
            $state.go('requestReset');
        }
    }
})();
