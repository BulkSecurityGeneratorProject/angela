(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('T_review', T_review);

    T_review.$inject = ['$resource', 'DateUtils'];

    function T_review ($resource, DateUtils) {
        var resourceUrl =  'api/t-reviews/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdate = DateUtils.convertDateTimeFromServer(data.createdate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
