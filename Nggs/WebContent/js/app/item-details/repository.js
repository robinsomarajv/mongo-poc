(function() {
    'use strict';
    
    angular.module('item-details')
        .factory('repository', repository);

    function repository() {
        var api = {
            addTemplate: addTemplate,
            getTemplates: getTemplates
        }

        var templates = {};

        return api;

        function addTemplate(name, template) {
            templates[name] = null;
            templates[name] = template;

            console.log(templates);
        }

        function getTemplates() {
            return templates;
        }
    }
})();