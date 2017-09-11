(function() {

    'use strict';

    angular.module('item-details')
        .factory('dynamicBarOverviewService', dynamicBarOverviewService);

    dynamicBarOverviewService.$inject = ['$routeParams', '$http']
    function dynamicBarOverviewService($routeParams,    $http) {
        var api = {
            data: null,
            selectedItem: null,
            url: 'templates/item-details/dynamic-bar-overview.html',
            loadData: loadData,
            loadParent: loadParent,
            loadChildren: loadChildren
        };

        return api;

        function loadData() {
            $http({
                method: 'GET',
                url: 'data/item-details/'+ $routeParams.prodID +'/overview.json'
            }).then(
                function(response) {
                    api.data = response.data;
                    api.selectedItem = api.data;
                }
            )
        }

        function loadParent() {
            if(api.selectedItem.parent) {
                api.selectedItem = api.selectedItem.parent;
            }
        }

        function loadChildren(selectedItem) {
            selectedItem.parent = api.selectedItem;
            api.selectedItem = selectedItem;
        }
    }
})();