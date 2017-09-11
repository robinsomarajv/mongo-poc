(function() {
    'use strict';
    
    angular.module('item-details')
        .factory('staticBarAttributeMetadataService', staticBarAttributeMetadataService);
    
    function staticBarAttributeMetadataService() {
        var api = {
            url: "templates/item-details/static-bar-metadata.html",
            onClose: null
        };

        return api;
    }
})();