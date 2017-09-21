(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('cases', cases);

    cases.$inject = ['$resource', 'DateUtils', '$http', 'PROD'];

    function cases ($resource, DateUtils, $http, PROD) {
        var getCaseListUrl = PROD['DATAAPI'] + "/Angela/cases/getAllCases";
        var getCaseListDetailUrl = PROD['DATAAPI'] + "/Angela/cases/getCasesById";

        var getCaseList = function(params) {
            return $http({
                   method: 'GET',
                   url: getCaseListUrl + "?OrderBy=" + params["OrderBy"],
                   data: {}
            });
        }
        var getCaseDeatil = function(params) {
            console.log(params)
            return $http({
                   method: 'GET',
                   url: getCaseListDetailUrl + "?caseId=" + params["id"],
                   data: {}
            });

        }
        return {
            getCaseList: getCaseList,
            getCaseDeatil: getCaseDeatil
        };
    }
})();
