(function() {
    'use strict';

    angular.module('item-details')
        .factory('staticBarErrorService', staticBarErrorService);

    staticBarErrorService.$iect = ['$mdDialog', '$mdToast', 'taskService'];
    function staticBarErrorService($mdDialog, $mdToast, taskService) {
        var api = {
            attributesWithErrors: null,
            url: "templates/item-details/static-bar-errors.html",
            loadErrorsList: loadErrorsList,
            onErrorClicked: onErrorClicked,
            createTask: createTask,
            onClose: null
        };

        return api;
        
        function loadErrorsList(itemAttributes) {
            var result = {};
            angular.forEach(itemAttributes, function(groupDetails, group){
                angular.forEach(groupDetails.attributes, function(attribute){
                    if(attribute.errors) {
                        if(result[group]) {
                            result[group].attributes[attribute.metadata.id] = attribute;
                        }
                        else {
                            result[group] = {attributes: {}};
                            result[group].attributes[attribute.metadata.id] = attribute;
                        }
                    }
                });
            });
            
            api.attributesWithErrors = result;
        }

        function onErrorClicked(errors) {
            if(errors.selected) {
                errors.selected = false;
            }
            else {
                errors.selected = true;
            }
        }
        
        function createTask(ev) {

            var description = 'Please rectify the errors in the following attributes.  ';
            var title = "Rectify the errors in the attributes";

            angular.forEach(api.attributesWithErrors, function (groupDetails, group) {
                angular.forEach(groupDetails.attributes, function(attribute){
                    description = description + ' ' + group + ' - '  + attribute.metadata.label + ','
                });
            });

            taskService.title = title;
            taskService.description = description;

            $mdDialog.show({
                controller: 'taskController',
                templateUrl: 'pages/global-search/partials/task.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose: false
            })
                .then(function(confirmation){
                        var task = $mdToast.simple()
                            .textContent('Task created')
                            .position('bottom right')
                            .hideDelay(5000);

                        $mdToast.show(task);
                    },
                    function() {
                    });
        }
    }
})();