(function() {
    'use strict';

    angular.module('item-details', []);

    angular.module('item-details')
        .run(function($templateCache){
            var gsTextboxTemplate = '<md-input-container style="margin: 0px" flex><input type="text" ng-model="attribute.values" aria-label=" "></md-input-container>';
            var gsSwitchTemplate = '<md-input-container style="margin: 0px" flex><md-switch class="md-primary" aria-label=" " ng-model="attribute.values[0]" ng-true-value="true" ng-false-value="false"></md-switch></md-input-container>';
            var gsSelectTemplate = '<md-input-container style="margin: 0px" flex><md-select ng-model="attribute.values[0]" aria-label=" "><md-option ng-value="opt.value" ng-repeat="opt in attribute.metadata.validValues">{{opt.value}}</md-option></md-select></md-input-container>';
            var gsMultiSelectTemplate = '<md-input-container style="margin: 0px" flex><md-select multiple="true" ng-model="attribute.values[0]" aria-label=" "><md-option ng-value="opt.id" ng-repeat="opt in attribute.metadata.validValues">{{opt.value}}</md-option></md-select></md-input-container>';

            $templateCache.put('global-search-textbox', gsTextboxTemplate);
            $templateCache.put('global-search-switch', gsSwitchTemplate);
            $templateCache.put('global-search-select', gsSelectTemplate);
            $templateCache.put('global-search-multi-select', gsMultiSelectTemplate);

            $templateCache.put('templateHeader', 
                '<md-card class="md-whiteframe-3dp" ng-class="{selectedCard: attribute.selected}" ng-click="attributeClicked()">' +
                    '<md-card-title style="padding-top: 0px;padding-bottom: 0px;">' +
                        '<md-card-title-text>' +
                            '<span class="md-subhead" layout="row" flex="90">' +
                                '<span ng-if="::!attribute.errors" ng-bind="::attribute.metadata.label" style="text-overflow: ellipsis;white-space: nowrap; overflow: hidden;"></span>' +
                                '<span ng-if="::attribute.errors" ng-bind="::attribute.metadata.label" style="text-overflow: ellipsis;white-space: nowrap; overflow: hidden;color:red"></span>' +
                                '<span ng-show="::attribute.metadata.required" style="color:red;padding-left: 5px;">*</span>' +
                            '</span>' +
                        '</md-card-title-text>' +
                    '</md-card-title>' +
                    '<md-card-content layout="row" style="padding: 5px" layout-align="space-between">');
            
            $templateCache.put('templateFooter',
                '</md-card-content>' +
                '</md-card>');
            
            $templateCache.put('attribute-list-template',
                '<div id="{{attribute.metadata.id}}" layout="row" flex class="item-details-attribute-list-list-item">' +
                    '<span class="attribute-title" flex="50" ng-bind="::attribute.metadata.label"></span>' +
                    '<span class="attribute-value" flex="50" ng-bind="::getValue()"></span>' +
                '</div>');
        })
})();