(function () {
    'use strict';

    angular
        .module('angelaApp')
        .controller('ProductDetailController', ProductDetailController);

    ProductDetailController.$inject = ['$scope', '$rootScope', '$cookies', '$cookieStore', 'Principal', 'LoginService', '$state', '$stateParams', 'product', 'PROD'];

    function ProductDetailController($scope, $rootScope, $cookies, $cookieStore, Principal, LoginService, $state, $stateParams, product, PROD) {
        var vm = this;
        vm.PROD = PROD;
        vm.type = $stateParams['type'] || 'Category';
        vm.addCart = addCart;
        vm.selectImg = selectImg;
        vm.quotation = {};
        vm.imgIdx = 0;

        Principal.identity().then(function (account) {
            vm.account = account;
            vm.quotation.companyName = account['companyName'];
            vm.quotation.clientName = account['login'];
            vm.quotation.email = account['email'];
            vm.quotation.phoneNumber = account['telNumber'];
            vm.quotation.asi = account['asiSageNumber'];
            vm.quotation.message = account['message'];
            vm.quotation.fox = account['faxNumber'];
            vm.quotation.userid = account['id'];
            vm.quotation.customName = account['login'];
            var keepGoing = true;
            angular.forEach(vm.quotation, function (d) {

                if (keepGoing) {
                    if (d === null) {
                        if (confirm('Your personal information is not perfect, whether to perfect your personal information first?')) {
                            keepGoing = false;
                            $state.go('userinfo-detail.edit');
                        } else {
                            keepGoing = false;
                            return;
                        }
                    }
                }

            })
        });


        locadAll();
        function locadAll() {

            loadProductDetail($stateParams['id']);

            loadCategorysList();
            getAllDictionarysList();
        }
        // 加载产品
        function loadProductDetail(id) {

            var productDetailP = product.getProductDetail({ "id": (id || '') });

            productDetailP.then(function (product) {
                vm.product = product['data']['products'];
            })
        }
        // 加载类别
        function loadCategorysList(params) {
            var categoryListP = product.getCategorysList(params);
            categoryListP.then(function (categoryList) {
                vm.categoryList = categoryList['data'];
                var _cateName = [];
                _cateName = _.filter(vm.categoryList.Categorys, { 'id': vm.product.categoryId })
                if (_cateName.length > 0) {
                    vm.cateName = _cateName[0].cateName;
                } else {
                    angular.forEach(vm.categoryList.Categorys, function (c) {
                        _cateName = _.filter(c.category, { 'id': vm.product.categoryId });
                        if (_cateName.length > 0) {
                            vm.cateName = _cateName[0].cateName;
                        }
                    })
                }

            })
        }
        // 
        function getAllDictionarysList(params) {
            var getAllDictionarysP = product.getAllDictionarys(params);

            getAllDictionarysP.then(function (dictionarysList) {
                vm.dictionarysList = dictionarysList['data'] || [];
                vm.dictionarysMaterial = (vm.dictionarysList['Dictionary'] || []).filter(function (_d) {
                    return _d['dicKey'] == "product_material";
                })

                var _materials = vm.product.material.split(',') || vm.product.material;
                if (Array.isArray(_materials)) {
                    var materialName = [];
                    angular.forEach(_materials, function (d) {
                        var material = _.filter(vm.dictionarysMaterial, { 'dicId': d })[0].dicVal;
                        materialName.push(material);
                    })
                    vm.materialName = materialName.join();
                } else {
                    vm.materialName = _.filter(vm.dictionarysMaterial, { 'dicId': _materials })[0].dicVal;
                }

                vm.dictionarysArea = (vm.dictionarysList['Dictionary'] || []).filter(function (_d) {
                    return _d['dicKey'] == "product_area";
                })
                vm.areaName = _.filter(vm.dictionarysArea, { 'dicId': vm.product.productArea })[0].dicVal;

                vm.dictionarysProofs = (vm.dictionarysList['Dictionary'] || []).filter(function (_d) {
                    return _d['dicKey'] == "product_proofs";
                })
                var _proofs = vm.product.productProofs.split(',') || vm.product.productProofs;
                if (Array.isArray(_proofs)) {
                    var proofsNames = [];
                    angular.forEach(_proofs, function (d) {
                        var proof = _.filter(vm.dictionarysProofs, { 'dicId': d })[0].dicVal;
                        proofsNames.push(proof);
                    })
                    vm.proofsNames = proofsNames.join();
                } else {
                    vm.proofsNames = _.filter(vm.dictionarysProofs, { 'dicId': _proofs })[0].dicVal;
                }

            })
        }

        // 添加到购物车
        function addCart() {
            // 把产品id 加入quotation
            if (vm.account == null) {
                alert("You are not logged in yet!");
                $state.go('full-login');
            } else {
                vm.quotation.productId = vm.product['id'];
                vm.quotation.productName = vm.product['productName'];

                var quotation = angular.copy(vm.quotation);

                var catList = [];

                catList.push({
                    "productId": quotation.productId,
                    "quantity": quotation.quantity,
                    "deliveryTime": quotation.deliveryTime,
                    "targetPrice": quotation.targetPrice,
                    "asiSageNo": quotation.asiSageNo,
                    "message": quotation.message,

                    "productName": quotation.productName

                });
                quotation.orderProduct = catList;

                if (!$cookieStore.get('quotation')) {
                    $cookieStore.put("quotation", quotation);
                } else {
                    var _quotation = $cookieStore.get('quotation');
                    _quotation.userid = quotation['userid'];
                    _quotation.companyName = quotation['companyName'];
                    _quotation.clientName = quotation['clientName'];
                    _quotation.customName = quotation['customName'];
                    _quotation.phoneNumber = quotation['phoneNumber'];
                    _quotation.fox = quotation['fox'];
                    _quotation.asi = quotation['asi'];
                    _quotation.customName = quotation['customName'];
                    _quotation.email = quotation['email'];
                    _quotation.productName = quotation['productName'];
                    _quotation.orderProduct.push(catList[0]);

                    $cookieStore.put("quotation", _quotation);
                }
                alert('submit successfully!');
            }

        }

        //查看图片
        function selectImg(idx) {
            vm.imgIdx = idx;
        }
    }
})();
