(function() {

    'use strict';

    angular.module('item-details')
        .factory('dynamicBarTradeItemService', dynamicBarTradeItemService);

    dynamicBarTradeItemService.$inject = ['$http', '$routeParams'];
    function dynamicBarTradeItemService($http, $routeParams) {
        var views = {
            tradeItem: "trade",
            tradeItemHierarchy: "hierarchy"
        };

        var api = {
            views: views,
            selectedView: null,
            url: 'templates/item-details/dynamic-bar-trade-item.html',
            data: null,
            selectedTradeItem: null,
            selectedTradeHierarchy: null,
            selectedTradeHierarchyItem: null,
            loadData: loadData,
            tradeItemSelected: tradeItemSelected,
            onTradeItemClicked: onTradeItemClicked,
            showTradeItemHierarchy: showTradeItemHierarchy,
            goBack: goBack,
            tradeHierarchyItemSelected: tradeHierarchyItemSelected,
            onItemSelected: null
        };

        return api;

        function loadData() {
            $http({
                method: 'GET',
                url: 'data/item-details/'+ $routeParams.gtin +'/trade-items.json'
            }).then(
                function(response) {
                    api.data = response.data;
                    api.selectedView = api.views.tradeItem;
                }
            )
        }

        function onTradeItemClicked(tradeItem) {
            if(api.onItemSelected) {
                if(!api.selectedTradeHierarchy) {
                    api.selectedTradeHierarchy = tradeItem.hierarchyItems[0];
                    api.selectedTradeHierarchyItem = api.selectedTradeHierarchy.items[0];
                }

                api.onItemSelected();
            }

            api.selectedTradeItem = tradeItem;
        }

        function tradeItemSelected(tradeItem) {
            api.selectedTradeItem = tradeItem;
            api.selectedView = api.views.tradeItemHierarchy;
        }

        function goBack() {
            api.selectedTradeItem = null;
            api.selectedTradeHierarchy = null;
            api.selectedTradeHierarchyItem = null;
            api.selectedView = api.views.tradeItem;
        }

        function showTradeItemHierarchy(tradeItem) {
            api.selectedTradeItem = tradeItem;
        }

        function tradeHierarchyItemSelected (hierarchy, hierarchyItem) {
            api.selectedTradeHierarchy = hierarchy;
            api.selectedTradeHierarchyItem = hierarchyItem;

            if(api.onItemSelected) {
                api.onItemSelected();
            }
        }
    }
})();