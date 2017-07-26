(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function HomeController ($scope, Principal, LoginService, $state) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
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
        // jzh
        vm.myInterval = 5000;
        vm.noWrapSlides = false;
        vm.active = 0;
        var slides = $scope.slides = [];
        var currIndex = 0;



        vm.slides = [{
          image: 'content/images/banner0.jpg',
          text: ['Nice image','Awesome photograph','That is so cool','I love that'],
          id: currIndex++
          },{
            image: 'content/images/banner1.jpg',
            text: ['Nice image','Awesome photograph','That is so cool','I love that'],
            id: currIndex++
          },{
            image: 'content/images/banner2.jpg',
            text: ['Nice image','Awesome photograph','That is so cool','I love that'],
            id: currIndex++
          }];
    }
})();
