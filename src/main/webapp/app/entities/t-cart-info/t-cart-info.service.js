(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('T_cart_info', T_cart_info);

    T_cart_info.$inject = ['$resource', 'DateUtils'];

    function T_cart_info ($resource, DateUtils) {
        var resourceUrl =  'api/t-cart-infos/:id';

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
