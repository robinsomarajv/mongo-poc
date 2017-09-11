(function(){
    "use strict";
    
    angular.module('emiApp', ['ngRoute','ngSanitize', 'ngAnimate', 'globalSearch', 'ngMaterial', 'item-details'])
        .config(function($routeProvider, $mdThemingProvider){
            $routeProvider
                .when('/', {
                    templateUrl: 'pages/home.html',
                    controller: 'homeController',
                    controllerAs: 'homevm'
                })
                .when('/search/home', {
                    templateUrl: 'pages/global-search/search.html',
                    controller: 'searchController',
                    controllerAs: 'searchvm'
                })
                .when('/search/item', {
                    templateUrl: 'pages/global-search/home.html',
                    controller: 'globalSearchController',
                    controllerAs: 'gsvm'
                })
                .when('/search/item/:itemType', {
                    templateUrl: 'pages/global-search/home.html',
                    controller: 'globalSearchController',
                    controllerAs: 'gsvm'
                })
                .when('/search/results/:searchterm', {
                    templateUrl: 'pages/global-search/search-result.html',
                    controller: 'searchResultsController',
                    controllerAs: 'srvm'
                })
				.when('/search/results', {
                    templateUrl: 'pages/global-search/search-result.html',
                    controller: 'searchResultsController',
                    controllerAs: 'srvm'
                })
                .when('/user', {
                    templateUrl: 'pages/user-pref.html',
                    controller: 'userPreferenceController',
                    controllerAs: 'upvm'
                })
                .when('/item/:prodID', {
                    templateUrl: 'pages/item-details/home.html',
                    controller: 'itemDetailsController',
                    controllerAs: 'idvm'
                })
                .when('/tradeItem/:tId', {
                    templateUrl: 'pages/tradeitem-details/home.html',
                    controller: 'itemDetailsController',
                    controllerAs: 'idvm'
                })
                .when('/supplyItem/:itemNbr', {
                    templateUrl: 'pages/supplyitem-details/home.html',
                    controller: 'itemDetailsController',
                    controllerAs: 'idvm'
                });

            $mdThemingProvider.definePalette('walmartPrimaryPalette', {
                '50': '#76c043',
                '100': '#76c043',
                '200': '#76c043',
                '300': '#76c043',
                '400': '#76c043',
                '500': '#007dc4',
                '600': '#007dc4',
                '700': '#007dc4',
                '800': '#007dc4',
                '900': '#0F4F8C',
                'A100': '#0F4F8C',
                'A200': '#0F4F8C',
                'A400': '#0F4F8C',
                'A700': '#0F4F8C',
                'contrastDefaultColor': 'light'
            });

            $mdThemingProvider.theme('default')
                .primaryPalette('walmartPrimaryPalette', {
                    'default': '500',
                    'hue-1': '900',
                    'hue-2': '200'
                })
                .accentPalette('cyan', {
                    'default': '600'
                });
            $mdThemingProvider.theme('default-amber').primaryPalette('blue').accentPalette('amber');
        });
    
    angular.module('emiApp')
        .run(function($http, $templateCache){
            var templateList = [
                { name: "dashboard-recent-items-landscape", url: "templates/dashboard/recent-items-landscape.html" },
                { name: "dashboard-recent-items-portrait", url: "templates/dashboard/recent-items-portrait.html" }
            ];

            angular.forEach(templateList, function(template){
                $http({
                    method: 'GET',
                    url: template.url
                }).then(function(resp){
                   $templateCache.put(template.name, resp.data);
                });
            })
        });
})();