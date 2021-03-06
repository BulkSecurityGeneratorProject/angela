(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('T_pictures', T_pictures);

    T_pictures.$inject = ['$resource', 'DateUtils'];

    function T_pictures ($resource, DateUtils) {
        var resourceUrl =  'api/t-pictures/:id';

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
