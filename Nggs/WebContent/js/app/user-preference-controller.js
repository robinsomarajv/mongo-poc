(function() {
    'use strict';
    
    angular.module('emiApp')
        .controller('userPreferenceController', userPreferenceController)
        .factory('userPreferenceFactory', userPreferenceFactory);

    userPreferenceController.$inject = ['userPreferenceFactory'];
    function userPreferenceController(userPreferenceFactory) {
        var upvm = this;

        upvm.context = userPreferenceFactory;
    }

    function userPreferenceFactory() {
        var api = {
            options: {
                dashboard: {
                    showItemsCreatedByUser: true,
                    showTasksAssignedToUser: false,
                    showLastLogin: true,
                    showRecentActivities: true,
                    noOfRecentActivities: 5,
                    showDataQuality: true
                },
                search: {
                    showFavorites: true,
                    noOfFavorites: 20,
                    showSearchTiles: true,
                    showAddTiles: true,
                    showTilesCreateByMe: false,
                    enableSearchFilters: true
                },
                searchResults: {}
            }
        }

        return api;
    }
})();