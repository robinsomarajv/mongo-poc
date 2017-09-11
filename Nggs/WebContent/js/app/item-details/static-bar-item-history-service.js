(function() {
    'use strict';

    angular.module('item-details')
        .factory('itemHistoryService', itemHistoryService);

    itemHistoryService.$inject = ['$http', '$routeParams', '$mdSidenav'];
    function itemHistoryService($http, $routeParams, $mdSidenav) {
        var api = {
            url: "templates/item-details/static-bar-item-history.html",
            historyData: null,
            selectedHistoryDetails: null,
            loadItemHistoryList: loadItemHistoryList,
            showHistoryDetails: showHistoryDetails,
            showAllHistoryDetails: showAllHistoryDetails,
            showNext: showNext,
            showPrevious: showPrevious,
            onClose: null
        }

        return api;

        function loadItemHistoryList(item) {
            $http({
                method: 'GET',
                url: 'data/item-details/'+$routeParams.gtin+'/product-attributes-item-history.json'
            }).then(
                function(response) {
                    api.historyData = response.data;
                }
            )
        }

        function showHistoryDetails(itemHistoryDetails) {
            api.selectedHistoryDetails = null;
            $mdSidenav('historyBar').toggle()
                .then(function(){
                    if($mdSidenav('historyBar').isOpen()) {
                        api.selectedHistoryDetails = [itemHistoryDetails];
                    }
                });
        }

        function showAllHistoryDetails() {
            api.selectedHistoryDetails = null;

            $mdSidenav('historyBar').toggle()
                .then(function(){
                    if($mdSidenav('historyBar').isOpen()) {
                        api.selectedHistoryDetails = api.historyData;
                    }
                });
        }

        function showNext() {
            var index = api.historyData.indexOf(api.selectedHistoryDetails);
            api.selectedHistoryDetails = [api.historyData[index++]];
        }

        function showPrevious() {
            var index = api.historyData.indexOf(api.selectedHistoryDetails);
            api.selectedHistoryDetails = [api.historyData[index--]];
        }
    }
})();