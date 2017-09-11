(function() {

    'use strict';

    angular.module('item-details')
        .controller('taskController', taskController)
        .factory('taskService', taskService);

    taskController.$inject = ['$scope', '$mdDialog', 'taskService']
    function taskController($scope, $mdDialog, taskService) {
        $scope.task = taskService;

        $scope.cancel = function() {
            clearTask();
            $mdDialog.cancel();
        }

        $scope.create = function() {
            clearTask();
            $mdDialog.hide('create');
        }

        function clearTask() {
            $scope.task.title = null;
            $scope.task.description = null;
            $scope.task.assignedTo = null;
        }
    }

    function taskService() {
        var api = {
            title: null,
            description: null,
            assignedTo: null
        };

        return api;
    }
})();