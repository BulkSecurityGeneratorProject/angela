(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('T_case_info', T_case_info);

    T_case_info.$inject = ['$resource', 'DateUtils'];

    function T_case_info ($resource, DateUtils) {
        var resourceUrl =  'api/t-case-infos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdate = DateUtils.convertDateTimeFromServer(data.createdate);
                        data.createDate = DateUtils.convertDateTimeFromServer(data.createDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
