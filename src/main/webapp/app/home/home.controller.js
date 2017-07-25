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
        $scope.myInterval = 5000;
        $scope.noWrapSlides = false;
        $scope.active = 0;
        var slides = $scope.slides = [];
        var currIndex = 0;

        $scope.addSlide = function() {
          var newWidth = 600 + slides.length + 1;
          slides.push({
            image: '//unsplash.it/' + newWidth + '/300',
            text: ['Nice image','Awesome photograph','That is so cool','I love that'][slides.length % 4],
            id: currIndex++
          });
        };

        $scope.randomize = function() {
          var indexes = generateIndexesArray();
          assignNewIndexesToSlides(indexes);
        };

        for (var i = 0; i < 4; i++) {
          $scope.addSlide();
        }

        // Randomize logic below

        function assignNewIndexesToSlides(indexes) {
          for (var i = 0, l = slides.length; i < l; i++) {
            slides[i].id = indexes.pop();
          }
        }

        function generateIndexesArray() {
          var indexes = [];
          for (var i = 0; i < currIndex; ++i) {
            indexes[i] = i;
          }
          return shuffle(indexes);
        }

        // http://stackoverflow.com/questions/962802#962890
        function shuffle(array) {
          var tmp, current, top = array.length;

          if (top) {
            while (--top) {
              current = Math.floor(Math.random() * (top + 1));
              tmp = array[current];
              array[current] = array[top];
              array[top] = tmp;
            }
          }

          return array;
        }
    }
})();
