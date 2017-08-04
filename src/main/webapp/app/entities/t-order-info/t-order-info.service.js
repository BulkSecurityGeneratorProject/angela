(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('T_order_info', T_order_info);

    T_order_info.$inject = ['$resource', 'DateUtils'];

    function T_order_info ($resource, DateUtils) {
        var resourceUrl =  'api/t-order-infos/:id';

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
