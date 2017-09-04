(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('mycart', mycart);

    mycart.$inject = ['$resource', 'DateUtils', '$http', 'PROD'];

    function mycart ($resource, DateUtils, $http, PROD) {
        var postAddOrdersUrl = PROD['DATAAPI'] + "/Angela/orders/postAddOrders"
        var postAddOrdersList = function(params){
            console.log(params)
            return $http({
                method: 'post',
                url: postAddOrdersUrl,
                data:params
            })
        }

        return {
            postAddOrdersList: postAddOrdersList
        };
    }
})();
