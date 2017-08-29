(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('product', product);

    product.$inject = ['$resource', 'DateUtils', '$http', 'PROD'];

    function product ($resource, DateUtils, $http, PROD) {
        var getProductListUrl =  PROD['DATAAPI'] + '/Angela/products/getAllProducts';
        var getProductDetailUrl = PROD['DATAAPI'] + "/Angela/products/getProductsDetail";
        var getCategorysListUrl = PROD['DATAAPI'] + "/Angela/categorys/getAllCategorys";

        var getProductList = function(params) {
            return $http({
                   method: 'GET',
                   url: getProductListUrl + "?OrderBy=" + params["OrderBy"],
                   data: {}
            });
        }

        var getProductDetail = function(params) {
            return $http({
                   method: 'GET',
                   url: getProductDetailUrl + "?productId=" + params["id"],
                   data: {}
            });

        }

        var getCategorysList = function(params) {
            return $http({
                method: 'get',
                url: getCategorysListUrl,
                data: {}
            })
        }

        return {
            getProductList: getProductList,
            getProductDetail: getProductDetail,
            getCategorysList: getCategorysList
        };
    }
})();