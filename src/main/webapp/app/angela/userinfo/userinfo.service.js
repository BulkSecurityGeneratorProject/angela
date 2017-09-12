  (function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('userinfo', userinfo);

    userinfo.$inject = ['$resource', 'DateUtils', '$http', 'PROD'];

    function userinfo ($resource, DateUtils, $http, PROD) {
        var getAllOrderUrl = PROD['DATAAPI'] + "/Angela/orders/getOrders"
        var getAllOrderList = function(params){
            return $http({
                method: 'get',
                 url: getAllOrderUrl + "?userId=" + params["id"],
                data: params 
            })
        }

        return {
            getAllOrderList: getAllOrderList
        };
    }
})();
