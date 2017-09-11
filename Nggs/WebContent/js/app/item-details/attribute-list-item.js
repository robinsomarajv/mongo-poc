(function() {
    'use strict';

    angular.module('item-details')
        .directive('attributeListItem', attributeListItem);

    attributeListItem.$inject = ['$templateCache']
    function attributeListItem($templateCache) {
        var ddo = {
            restrict: 'E',
            template: $templateCache.get('attribute-list-template'),
            scope: {
                attribute: '=',
                onAttributeClicked: '&'
            },
            link: attributeLinkFn
        }

        return ddo;

        function attributeLinkFn(scope, element, attrs) {
            scope.getValue = function() {
                var returnVal = null;

                if(scope.attribute.values && scope.attribute.values && scope.attribute.values.length !== 0) {

                    switch (scope.attribute.metadata.field) {
                        case "textbox":
                        case "decimal":
                        case "numeric":
                            returnVal = scope.attribute.values[0];
                            break;
                        case "boolean":
                            returnVal = scope.attribute.values[0].toLowerCase() === 'true' ? 'Yes' : 'No';
                                break;
                        case "select":
                            returnVal = "select";
                            break;
                    }
                }
                else {
                    returnVal = '-';
                }

                return returnVal;
            }
        }
    }
})();