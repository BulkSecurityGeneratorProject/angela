(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('testerminal', testerminal);

    testerminal.$inject = ['$resource', 'DateUtils', '$http', 'PROD'];

    function testerminal ($resource, DateUtils, $http, PROD) {
        var getProductListUrl =  PROD['DATAAPI'] + '/Angela/testerminals/postAddTesterMinals';

        var postAddTesterMinals = function(params) {
            return $http({
                   method: 'POST',
                   url: getProductListUrl,
                   data: params
            });
        }


        return {
            postAddTesterMinals: postAddTesterMinals
        };
    }
})();
