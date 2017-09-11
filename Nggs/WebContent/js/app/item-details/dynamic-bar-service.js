(function() {
    'use strict';

    angular.module('item-details')
        .factory('dynamicBarService', dynamicBarService);

    dynamicBarService.$inject = ['$mdSidenav', 'dynamicBarOverviewService', 'dynamicBarTradeItemService', 'dynamicBarSupplyItemService']
    function dynamicBarService($mdSidenav, dynamicBarOverviewService, dynamicBarTradeItemService, dynamicBarSupplyItemService) {
        var tabs = [
            { id: "overview", name: "Overview", context: dynamicBarOverviewService},
            { id: "trade", name: "Trade Item", context: dynamicBarTradeItemService},
            { id: "supply", name: "Supply Item", context: dynamicBarSupplyItemService},
            { id: "offer", name: "Offer" },
            { id: "shippers", name: "Shippers" },
            { id: "assortments", name: "Assortments" },
            { id: "related", name: "Related Items" }
        ];

        var api = {
            tabs: tabs,
            active: null,
            navigateTo: navigateTo,
            onItemSelected: null
        };

        function navigateTo(tab) {
            api.active = tab;
            api.active.context.loadData();
            api.active.context.onItemSelected = onItemSelected;
        }
        
        function onItemSelected() {
            $mdSidenav('dynamicBar').toggle()
                .then(function(){
                    if(api.onItemSelected) {
                        api.onItemSelected(api.active.id);
                    }
                });
        }

        function showOverviewTab() {
            
        }

        function showTradeItemTab() {

        }

        function showSupplyItemTab() {

        }

        function showOfferTab() {

        }

        function showShipperTab() {

        }

        function showAssortmantTab() {

        }

        function showRelatedItemTab() {

        }

        return api;
    }
})();