(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('T_category', T_category);

    T_category.$inject = ['$resource'];

    function T_category ($resource) {
        var resourceUrl =  'api/t-categories/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
