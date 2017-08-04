(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('T_product', T_product);

    T_product.$inject = ['$resource', 'DateUtils'];

    function T_product ($resource, DateUtils) {
        var resourceUrl =  'api/t-products/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createDate = DateUtils.convertDateTimeFromServer(data.createDate);
                        data.lastUpdate = DateUtils.convertDateTimeFromServer(data.lastUpdate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
