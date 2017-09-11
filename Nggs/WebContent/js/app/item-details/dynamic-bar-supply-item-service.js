(function() {

    'use strict';

    angular.module('item-details')
        .factory('dynamicBarSupplyItemService', dynamicBarSupplyItemService);

    dynamicBarSupplyItemService.$inject = ['$routeParams', '$http']
    function dynamicBarSupplyItemService($routeParams, $http) {
        var api = {
            data: null,
            selectedItem: null,
            url: 'templates/item-details/dynamic-bar-supply-item.html',
            loadData: loadData,
            itemSelected: itemSelected,
            onItemSelected: null
        };

        return api;

        function loadData() {
            $http({
                method: 'GET',
                url: 'data/item-details/'+ $routeParams.gtin +'/supply-items.json'
            }).then(
                function(response) {
                    api.data = response.data;
                }
            )
        }

        function itemSelected(supplyItem) {
            api.selectedItem = supplyItem;

            if(api.onItemSelected) {
                api.onItemSelected();
            }
        }
    }
})();