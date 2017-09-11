(function(){
    'use strict';
    
    angular.module('emiApp')
        .controller('homeController', homeController)
        .factory('homeService', homeService);

    homeController.$inject = ['homeService'];
    function homeController(homeService) {

        var homevm = this;
        homevm.context = homeService;
        homevm.context.loadData();
    }

    homeService.$inject = ['$rootScope', '$location', '$http'];
    function homeService($rootScope, $location, $http) {
        var api = {
            userPreference: $rootScope.userPreference, 
            data: {
                recentItems: null,
                favoriteSearches: null,
                loginDetails: {
                    lastLoggedIn: "12/3/2016 2.30pm",
                    duration: "50 minutes"
                },
                recentActivities: null,
                overAllDataQuality: null
            },
            overAllDataQuality: {
                data: null,
                showDataQualitySettings: showDataQualitySettings,
                setView: setoverAllDataQualityView
            },
            loadData: loadData,
            search: search,
            navigateToItemDetails: navigateToItemDetails
        }

        return api;

        function loadData() {
            if(!api.data.favoriteSearches) {
                loadFavoriteSearchTerms();
            }
            
            loadRecentItems();
            loadRecentActivities();
            loadOverAllDataQuality();
        }

        function loadFavoriteSearchTerms() {
            $http(
                {
                    method: 'GET',
                    url: 'data/search-favorite-terms.json'
                }
            ).then(
                function(response) {
                    api.data.favoriteSearches = response.data;
                }
            )
        }

        function search() {
            $location.path('/search/results')
        }

        function navigateToItemDetails() {
            $location.path('search/item')
        }

        function loadRecentItems() {
            var request = {
                method: 'GET',
                url: 'data/dashboard-recent-items.json'
            }

            $http(request)
                .then(
                    function(response) {
                        api.data.recentItems = response.data;
                    }
                );
        }

        function loadRecentActivities() {
            var request = {
                method: 'GET',
                url: 'data/dashboard-recent-activities.json'
            }

            $http(request)
                .then(
                    function(response) {
                        api.data.recentActivities = response.data;
                    }
                );
        }

        function loadOverAllDataQuality() {
            var request = {
                method: 'GET',
                url: 'data/dashboard-data-quality-overall.json'
            }

            $http(request)
                .then(
                    function(response) {
                        api.overAllDataQuality.data = response.data;
                    }
                );
        }

        function showDataQualitySettings($mdOpenMenu, event) {
            $mdOpenMenu(event);
        }

        function setoverAllDataQualityView(template) {
            api.userPreference.dashboard.overAllDataQuality.view.selectedTemplate = template;
        }
    }
})();
