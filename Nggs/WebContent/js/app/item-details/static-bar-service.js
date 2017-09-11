(function() {
    'use strict';

    angular.module('item-details')
        .factory('staticBarService', staticBarService);

    staticBarService.$inject = ['staticBarDefaultService', 'staticBarAttributeTemplatesService', 'staticBarAttributeMetadataService', 'staticBarErrorService', 'itemHistoryService'];
    function staticBarService(staticBarDefaultService, staticBarAttributeTemplatesService, staticBarAttributeMetadataService, staticBarErrorService, itemHistoryService) {
        var api = {
            selected: null,
            showDefaultStaticBar: showDefault,
            showTemplateStaticBar: showTemplate,
            showMetadataStaticBar: showMetadata,
            showErrorStaticBar: showError,
            showItemHistoryStaticBar: showHistory
        }

        function showDefault() {
            api.selected = staticBarDefaultService;
        }

        function showTemplate() {
            api.selected = staticBarAttributeTemplatesService;
            api.selected.showList();
        }

        function showMetadata() {
            api.selected = staticBarAttributeMetadataService;
        }
        
        function showError() {
            api.selected = staticBarErrorService;
        }

        function showHistory() {
            api.selected = itemHistoryService;
        }

        return api;
    }
})();