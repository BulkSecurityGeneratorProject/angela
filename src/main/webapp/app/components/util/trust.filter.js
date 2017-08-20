(function() {
    'use strict';

    angular
        .module('angelaApp')
        .filter('trustAsUrl', trustAsUrl);

        trustAsUrl.$inject = ['$sce'];


        function trustAsUrl($sce) {

            return $sce.trustAsUrl;
        }

})();
