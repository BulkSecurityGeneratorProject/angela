(function() {
    'use strict';

    angular
        .module('angelaApp')
        .controller('ProductListController', ProductListController);

    ProductListController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$sce'];

    function ProductListController ($scope, Principal, LoginService, $state, $sce) {
        var vm = this;

        vm.productList = {
                          "products": [
                            {
                              "marketPrice": 32.0,
                              "clickCount": 32,
                              "productUnit": "43",
                              "productDetails": "4343",
                              "pictures": [
                                {
                                  "imageId": "12",
                                  "productId": "2",
                                  "isDelete": 0,
                                  "imageUrl": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502734095676&di=4cd87d33702438fb11bab024d4fd722f&imgtype=0&src=http%3A%2F%2Fwww.51wendang.com%2Fpic%2F2739fe74d56fd038a607fea1%2F1-810-jpg_6-1080-0-0-1080.jpg",
                                  "createUser": "23",
                                  "imageUrlSmall": null,
                                  "imageType": 1,
                                  "createDate": "2017-08-05 22:29:31"
                                },
                                {
                                  "imageId": "35",
                                  "productId": "2",
                                  "isDelete": 0,
                                  "imageUrl": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503328814&di=b816c97e49c8f44300a4d431c3d95b7f&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.sj33.cn%2Fuploads%2Fallimg%2F201011%2F20101107115644300.jpg",
                                  "createUser": "323",
                                  "imageUrlSmall": null,
                                  "imageType": 2,
                                  "createDate": "2017-08-05 22:29:31"
                                }
                              ],
                              "productName": "4",
                              "productArea": 43,
                              "productColor": 43,
                              "id": "2",
                              "stock": 43,
                              "createDate": "2017-08-14 20:56:51",
                              "brief": "2",
                              "productSn": "43",
                              "isDelete": 0,
                              "productStatus": 34,
                              "material": 32,
                              "size": null,
                              "proTag": "23",
                              "lastUpdate": "2017-08-14 20:57:00",
                              "sortOrder": 43,
                              "sellCount": 43,
                              "isONSale": 232,
                              "createUser": "232",
                              "categoryId": 32,
                              "isHot": null,
                              "productPrice": 43.0
                            },
                            {
                              "marketPrice": 212.0,
                              "clickCount": 2,
                              "productUnit": "323",
                              "productDetails": "22",
                              "pictures": [
                                {
                                  "imageId": "22",
                                  "productId": "1",
                                  "isDelete": 0,
                                  "imageUrl": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503328814&di=b816c97e49c8f44300a4d431c3d95b7f&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.sj33.cn%2Fuploads%2Fallimg%2F201011%2F20101107115644300.jpg",
                                  "createUser": "332",
                                  "imageUrlSmall": null,
                                  "imageType": 4,
                                  "createDate": "2017-08-05 22:29:31"
                                },
                                {
                                  "imageId": "33",
                                  "productId": "1",
                                  "isDelete": 0,
                                  "imageUrl": "upload/image.jpg",
                                  "createUser": "32",
                                  "imageUrlSmall": null,
                                  "imageType": 3,
                                  "createDate": "2017-08-05 22:29:31"
                                },
                                {
                                  "imageId": "34",
                                  "productId": "1",
                                  "isDelete": 0,
                                  "imageUrl": "upload/image.jpg",
                                  "createUser": "32",
                                  "imageUrlSmall": null,
                                  "imageType": 3,
                                  "createDate": "2017-08-05 22:29:31"
                                }
                              ],
                              "productName": "2",
                              "productArea": 2,
                              "productColor": 2,
                              "id": "1",
                              "stock": 3,
                              "createDate": "2017-08-05 22:29:01",
                              "brief": "2",
                              "productSn": "222",
                              "isDelete": 0,
                              "productStatus": 3232,
                              "material": 12,
                              "size": null,
                              "proTag": "222",
                              "lastUpdate": "2017-08-05 22:29:31",
                              "sortOrder": 3,
                              "sellCount": 3,
                              "isONSale": 2,
                              "createUser": "12",
                              "categoryId": 2,
                              "isHot": null,
                              "productPrice": 22.0
                            }
                          ]
                }
    }
})();
