'use strict';

describe('Controller Tests', function() {

    describe('T_order_info Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockT_order_info;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockT_order_info = jasmine.createSpy('MockT_order_info');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'T_order_info': MockT_order_info
            };
            createController = function() {
                $injector.get('$controller')("T_order_infoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'angelaApp:t_order_infoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
