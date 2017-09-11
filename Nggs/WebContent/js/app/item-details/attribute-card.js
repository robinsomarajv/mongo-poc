(function() {
    'use strict';
    
    angular.module('item-details')
        .directive('attributeCard', attributeCard);

    attributeCard.$inject = ['$templateCache','$compile'];
    function attributeCard($templateCache, $compile) {
        var ddo = {
            restrict: 'E',
            template: '<div id="{{attribute.metadata.id}}"></div>',
            scope: {
                attribute: '=',
                onAttributeClicked: '&'
            },
            link: attributeLinkFn,
            replace: true
        };

        return ddo;

        function attributeLinkFn(scope, element, attrs) {

            loadTemplate();

            scope.attributeClicked = function() {
                if(scope.onAttributeClicked) {
                    scope.onAttributeClicked({attribute: scope.attribute});
                }
            }

            function loadTemplate() {
                var templateName = getTemplate();
                var template = $templateCache.get('templateHeader') + $templateCache.get(templateName) + $templateCache.get('templateFooter');
                element.html(template);
                $compile(element.contents())(scope);
            }

            function getTemplate() {
                var template = null;
                switch (scope.attribute.metadata.field) {
                    case 'textbox':
                    case 'decimal':
                    case 'numeric':
                        template = 'global-search-textbox';
                        break;
                    case 'boolean':
                        template = 'global-search-switch';
                        break;
                    case 'select':
                        template = 'global-search-select';
                        break;
                    case 'listBox':
                        template = 'global-search-multi-select';
                        break;
                    default:
                        console.log(scope.attribute);
                        break;
                }

                return template;
            }
        }
    }
})();