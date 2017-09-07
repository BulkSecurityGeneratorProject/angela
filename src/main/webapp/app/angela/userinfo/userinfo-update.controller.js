(function () {
    'use strict';

    angular
        .module('angelaApp')
        .controller('UserInfoUpdateController', UserInfoUpdateController);

    UserInfoUpdateController.$inject = ['$filter', '$rootScope', '$scope','$uibModalInstance','$cookieStore','$state','Data','Auth'];

    function UserInfoUpdateController($filter, $rootScope, $scope, $uibModalInstance, $cookieStore, $state, Data, Auth) {
        var vm = this;
        vm.clear = clear;
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        vm.data = Data;
        console.log(Data)
         vm.updateBaseInfo = updateBaseInfo;
            vm.doNotMatch = null;
            vm.error = null;
            vm.success = null;
            function updateBaseInfo () {
                if(confirm("Confirm the changes?")){
                      if (vm.password !== vm.confirmPassword) {
                    vm.error = null;
                    vm.success = null;
                    vm.doNotMatch = 'ERROR';
                } else {
                    vm.doNotMatch = null;
                    Auth.updateAccount(vm.data).then(function () {
                        vm.error = null;
                        vm.success = 'OK';
                        alert('modify successfully!')
                        $uibModalInstance.dismiss('cancel');
                    }).catch(function () {
                        vm.success = null;
                        vm.error = 'ERROR';
                    });
                }
                    
                }
              
            }
        }   
})();
