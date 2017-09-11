(function() {
    'use strict';

    angular.module('emiApp')
        .controller('searchController', searchController)
        .factory('searchService', searchService);

    searchController.$inject = ['$scope', 'searchService'];
    function searchController($scope, searchService) {
        var searchvm = this;

        $scope.basevm.pageClass = 'search-page';
        searchvm.context = searchService;
        searchvm.context.loadData();
    }

    searchService.$inject = ['$location', '$http', '$mdSidenav'];
    function searchService($location, $http, $mdSidenav) {
        var searchTileTabs = [
            {
                id: "shared-tiles", 
                name: "Shared Tiles", 
                url: "templates/search-side-bar-shared-tiles.html"},
            {
                id: "custom-tiles",
                name: "Custom Search Tiles",
                url: "templates/search-side-bar-custom-tile.html",
                data: {
                    tileName: "",
                    filters: [
                        {
                            attributeName: null,
                            compareType: null,
                            attributeValue: null
                        }
                    ],
                    addFilter: function() {
                        this.filters.push({ attributeName: null, compareType: null, attributeValue: null });
                    },
                    deleteFilter: function(index) {
                        if(this.filters.length === 1){
                            var item = this.filters[0];
                            item.attributeName = null;
                            item.compareType = null;
                            item.attributeValue = null;
                        }
                        else {
                            this.filters.splice(index, 1);
                        }
                    },
                    clear: function() {
                        this.tileName = null;
                        this.filters = [{attributeName:null, compareType: null, attributeValue: null }];
                    }
                }
            },
            {
                id: "price-tiles",
                name: "Price Search Tiles",
                url: "templates/search-side-bar-price-tile.html",
                data: {
                    tileName: null,
                    priceFrom: null,
                    priceTo: null,
                    supplierNo: null,
                    department: null,
                    clear: function() {
                        this.tileName = null;
                        this.priceFrom = null;
                        this.priceTo = null;
                        this.supplierNo = null;
                        this.department = null;
                    }
                }
            },
            {
                id: "category-tiles",
                name: "Category Search Tiles",
                url: "templates/search-side-bar-department-tile.html",
                data: {
                    tileName: null,
                    department: null,
                    clear: function() {
                        this.tileName = null;
                        this.department = null;
                    }
                }
            }
        ]
        
        var api = {
            searchText: null,
			searchDescription:null,
			searchGtN:null,
			searchDate:null,
            tiles: [],
            favoriteSearches: null,
            searchTiles: null,
            sideBar: {
                url: null,
                tabs: searchTileTabs,
                selectedTab: null,
                sharedTiles: null,
                selectTab: selectTab,
                close: close,
                show: show,
                addTile: addTile,
                addSharedTile: addSharedTile
            },
            loadData: loadData,
			textChange:textChange,
            search: search
        }

        return api;
        function textChange(id)
		{
		   switch(id)
		    {
			  case 'gtn':
			  api.searchDescription=null;
			  api.searchDate=null;
			  break;
			  case 'desc':
			  api.searchGtN=null;
			  api.searchDate=null;
			  break;
			  case 'date':
			  api.searchDescription=null;
			  api.searchGtN=null;
			  break;
		    }
		}
        function loadData() {
            api.searchText = null;
			api.searchDescription=null;
			api.searchDate=null;
			api.searchGtN=null;
            //loadFavoriteSearchTerms();
            loadSearchTiles();
            loadSharedTiles();
        }

        function loadFavoriteSearchTerms() {
            $http(
                {
                    method: 'GET',
                    url: 'data/search-favorite-terms.json'
                }
            ).then(
                function(response) {
                    api.favoriteSearches = response.data;
                }
            )
        }

        function loadSearchTiles() {
            if(!api.searchTiles) {
                $http(
                    {
                        method: 'GET',
                        url: 'data/search-tiles.json'
                    }
                ).then(
                    function(response) {
                        api.searchTiles = response.data;
                    }
                )
            }
        }

        function loadSharedTiles() {
            $http(
                {
                    method: 'GET',
                    url: 'data/search-tiles-shared.json'
                }
            ).then(
                function(response) {
                    api.sideBar.sharedTiles = response.data;
                }
            )
        }

        function search() {
			
			if(api.searchDescription)
			{
            $location.path('/search/results/'+"product_long_description:"+api.searchDescription);
			}
			if(api.searchDate)
			{
			var newdate= new Date(api.searchDate.getMonth()+"/"+api.searchDate.getDate()+"/"+api.searchDate.getFullYear()).getTime();
            $location.path('/search/results/'+"date:"+newdate);
			}
			if(api.searchGtN)
			{
            $location.path('/search/results/'+"consumable_gtins:"+api.searchGtN);
			}
        }

        function show() {
            $mdSidenav('searchSideBar')
                .toggle()
                .then(
                    function(){
                        if(!api.sideBar.selectedTab) {
                            api.sideBar.selectedTab = searchTileTabs[0];
                            api.sideBar.url = 'templates/search-side-bar.html';
                        }
                    }
                );
        }
        
        function close() {
            $mdSidenav('searchSideBar')
                .toggle()
                .then(
                    function(){
                    }
                );
        }

        function addTile() {
            api.searchTiles.push({
                name: api.sideBar.selectedTab.data.tileName,
                tile: {
                    rowSpan: 1,
                    colSpan: 2
                }
            });

            api.sideBar.selectedTab.data.clear();
            api.sideBar.close();
        }

        function addSharedTile(tile) {
            api.searchTiles.push(tile);
        }
        
        function selectTab(tab) {
            api.sideBar.selectedTab = tab;
        }
    }
})();