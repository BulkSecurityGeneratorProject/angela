(function() {
    'use strict';

    angular
        .module('angelaApp')
        .directive('adminNactiveMenu', adminNactiveMenu);

    adminNactiveMenu.$inject = ['$translate', '$locale', 'tmhDynamicLocale'];

    function adminNactiveMenu($translate, $locale, tmhDynamicLocale) {
        var directive = {
            restrict: 'A',
            link: linkFunc
        };

        return directive;

        function linkFunc(scope, element, attrs) {
            var language = attrs.adminNactiveMenu;

            scope.$watch(function() {
                return $translate.use();
            }, function(selectedLanguage) {
                if (language === selectedLanguage) {
                    tmhDynamicLocale.set(language);
                    element.addClass('active');
                } else {
                    element.removeClass('active');
                }
            });
        }
    }
})();
