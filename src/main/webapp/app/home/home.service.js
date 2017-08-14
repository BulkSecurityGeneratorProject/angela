(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('home', home);

    home.$inject = ['$resource', 'DateUtils', '$http'];

    function home ($resource, DateUtils, $http) {
        var getProductListUrl =  'http://139.196.95.117:8082/Angela/products/getAllproducts';

        // return $resource(resourceUrl, {}, {
        //     'query': { method: 'GET', isArray: true},
        //     'get': {
        //         method: 'GET',
        //         transformResponse: function (data) {
        //             if (data) {
        //                 data = angular.fromJson(data);
        //                 data.createdate = DateUtils.convertDateTimeFromServer(data.createdate);
        //                 data.createDate = DateUtils.convertDateTimeFromServer(data.createDate);
        //             }
        //             return data;
        //         }
        //     },
        //     'update': { method:'PUT' }
        // });
        // return $http({
        //        method: 'POST',
        //        url: url,
        //        data: {}
        // });
        var getProductList = function(params){
            return $http({
                   method: 'GET',
                   url: getProductListUrl,
                   data: {}
            });
        }
        return {
            getProductList: getProductList
        };
    }
})();
