(function() {
    'use strict';
    angular
        .module('angelaApp')
        .factory('product', product);

    product.$inject = ['$resource', 'DateUtils', '$http', 'PROD'];

    function product ($resource, DateUtils, $http, PROD) {
        var getProductListUrl =  PROD['DATAAPI'] + '/Angela/products/getAllProductsByParms';
        var getProductDetailUrl = PROD['DATAAPI'] + "/Angela/products/getProductsDetail";
        var getCategorysListUrl = PROD['DATAAPI'] + "/Angela/categorys/getAllCategorys";
        var postAddReviewsUrl = PROD['DATAAPI'] + "/Angela/reviews/postAddReviews";
        var getAllDictionarysUrl = PROD['DATAAPI'] + "/Angela/dictionarys/getAllDictionarys";

        var getProductList = function(params) {
            return $http({
                   method: 'POST',
                   url: getProductListUrl + "?OrderBy=" + params["OrderBy"],
                   data: params
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

        var postAddReviews = function(params) {
            return $http({
                method: 'post',
                url: postAddReviewsUrl,
                data: params
            })
        }

        var getAllDictionarys = function(params) {
            return $http({
                method: 'get',
                url: getAllDictionarysUrl,
                data: params
            })
        }

        return {
            getProductList: getProductList,
            getProductDetail: getProductDetail,
            getCategorysList: getCategorysList,
            postAddReviews: postAddReviews,
            getAllDictionarys: getAllDictionarys
        };
    }
})();
