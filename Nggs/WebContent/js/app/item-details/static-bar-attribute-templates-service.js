(function() {
    'use strict';

    angular.module('item-details')
        .factory('staticBarAttributeTemplatesService', staticBarAttributeTemplatesService);

    staticBarAttributeTemplatesService.$inject = ['repository'];
    function staticBarAttributeTemplatesService(repository) {
        var api = {
            url: 'templates/item-details/static-bar-attribute-templates.html',
            mode: null,
            templateList: [],
            create: {
                name: null,
                selectedAttributes: {},
                addToTemplate: addToTemplate,
                removeFromTemplate: removeFromTemplate,
                saveTemplate: saveTemplate,
                cancelTemplate: cancelTemplate
            },
            showCreateTemplate: showCreateTemplate,
            showList: showList,
            onClose: null,
            onTemplateCreation: null,
            applyTemplate: null
        }

        return api;

        function showList() {
            api.templateList = repository.getTemplates();
            api.mode = 'list';
        }

        function showCreateTemplate() {
            api.mode = 'create';

            if(api.onTemplateCreation) {
                api.onTemplateCreation();
            }
        }
        
        function saveTemplate() {
            var attrObj = angular.copy(api.create.selectedAttributes);

            angular.forEach(api.create.selectedAttributes, function(group) {
                angular.forEach(group.attributes, function(attribute){
                    delete attribute.selected;
                });
            });

            angular.forEach(attrObj, function(group) {
                angular.forEach(group.attributes, function(attribute){
                    delete attribute.selected;
                    attribute.values.valuesList = [];
                });
            });

            repository.addTemplate(api.create.name, attrObj);

            clearData();
            api.showList();
        }
        
        function cancelTemplate() {
            angular.forEach(api.create.selectedAttributes, function(group) {
                angular.forEach(group.attributes, function(attribute){
                    delete attribute.selected;
                });
            });

            clearData();
            api.showList();
        }

        function clearData() {
            api.create.name = null;
            api.create.selectedAttributes = {};
        }

        function addToTemplate(attribute) {
            if(!attribute.selected) {
                if (api.create.selectedAttributes[attribute.metadata.attributeGroup] !== undefined) {
                    api.create.selectedAttributes[attribute.metadata.attributeGroup].attributes[attribute.metadata.id] = attribute;
                }
                else {
                    api.create.selectedAttributes[attribute.metadata.attributeGroup] = {attributes:{}};
                    api.create.selectedAttributes[attribute.metadata.attributeGroup].attributes[attribute.metadata.id] = attribute;
                }

                attribute.selected = true;
            }
            else {
                removeFromTemplate(attribute);
            }
        }

        function removeFromTemplate(attribute) {
            attribute.selected = false;
            delete api.create.selectedAttributes[attribute.metadata.attributeGroup].attributes[attribute.metadata.id];
        }
    }
})();