(function() {
    'use strict';

    angular
        .module('angelaApp')
        .filter('trustAsHTML', trustAsHTML)
        .filter('trustAsUrl', trustAsUrl)
        .filter('trustAsResourceUrl', trustAsResourceUrl);

        trustAsHTML.$inject = ['$sce'];
        trustAsUrl.$inject = ['$sce'];
        trustAsResourceUrl.$inject = ['$sce'];

    function trustAsHTML($sce) {

        return $sce.trustAsHTML;
    }

    function trustAsUrl($sce) {

        return $sce.trustAsUrl;
    }

    function trustAsResourceUrl($sce) {

        return $sce.trustAsResourceUrl;
    }
})();
