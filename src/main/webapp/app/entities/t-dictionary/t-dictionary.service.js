(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('T_dictionary', T_dictionary);

    T_dictionary.$inject = ['$resource', 'DateUtils'];

    function T_dictionary ($resource, DateUtils) {
        var resourceUrl =  'api/t-dictionaries/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createDate = DateUtils.convertDateTimeFromServer(data.createDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
