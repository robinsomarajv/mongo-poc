(function() {
    'use strict';
    
    angular.module('emiApp')
        .controller('searchResultsController', searchResultsController)
        .factory('searchResultsService', searchResultsService);

    searchResultsController.$inject = ['$routeParams','searchResultsService'];
    function searchResultsController($routeParams,searchResultsService) {
        var srvm = this;
        srvm.routeparam = $routeParams.searchterm;
        srvm.context = searchResultsService;
        srvm.context.loadData(srvm.routeparam);
    }

    searchResultsService.$inject = ['$location', '$http']
    function searchResultsService($location, $http) {
        var views = {
            list: {name: "List", url: "templates/search-results-list.html", icon: "list"},
            card: {name: "Cards", url: "templates/search-results-card.html", icon: "call_to_action"}
        };
        
        var api = {
            views: views,
            showBackButton: false,
            searchResultsText: "",
            searchResults: {
                view: null,
                data: []
            },
            searchResults_trade: {
                view: null,
                data: []
            },
            searchResults_supply: {
                view: null,
                data: []
            },
            loadData: loadData,
            showSearchResultsView: showSearchResultsView,
            setSearchResultsView: setSearchResultsView,
            setView: setView,
            itemDetails: itemDetails,
            tradeItemDetails: tradeItemDetails,
            supplyItemDetails: supplyItemDetails
        }

        var baseSearchResult = [];

        return api;

        function showSearchResultsView($mdOpenMenu, event) {
            $mdOpenMenu(event);
        }

        function setSearchResultsView(view) {
            api.searchResults.view = view;
        }

        function itemDetails(item) {
            if(item.assortment) {
                $location.path('search/item/assortment')
            }
            else if (item.shipper) {
                $location.path('search/item/shipper')
            }
            else {
                $location.path('/item/'+item.product_id);
            }
        }
        
        function tradeItemDetails(item) {
          
              $location.path('/tradeItem/'+JSON.stringify(item._id));
           
        }
        function supplyItemDetails(item) {
            
            $location.path('/supplyItem/'+item._id);
         
      }

        function setView(view) {
            api.view = view;
        }

        function loadData(param) {
			var params =param.split(':');
			var requestParam={attrName:params[0],attrVal:params[1]};
			var requestParamTrade={attrName:params[0]=="consumable_gtins"?"tradeItemGtin":"tradeItemDescription",attrVal:params[1]};
			var requestParamSupply={attrName:params[0]=="consumable_gtins"?"consumableGtin":"supplyItemPrimaryDescription",attrVal:params[1]};
			
            $http({
                method: 'GET',
                headers:{
                    'Access-Control-Allow-Origin': '*',
                    'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
                    'Access-Control-Allow-Headers': 'Content-Type, X-Requested-With',
                    'X-Random-Shit':'123123123'
                },
                url: 'http://localhost:8080/product/summary',
				params: requestParam
            }).then(function(response) {
                    baseSearchResult = response.data;
                    api.searchResults.data = baseSearchResult;
						api.searchResults.data.forEach(function(element) {                    
						var timestamp= element.item.product_launch_date;
						 var todate=new Date(timestamp).getDate();
                         var tomonth=new Date(timestamp).getMonth()+1;
                         var toyear=new Date(timestamp).getFullYear();
                        var original_date=tomonth+'/'+todate+'/'+toyear;
						 element.item.product_launch_date= original_date;
                       });
					  
                    api.setSearchResultsView(views.list)
                },$http({
                	
                	method: 'GET',
                	headers:{
                        'Access-Control-Allow-Origin': '*',
                        'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
                        'Access-Control-Allow-Headers': 'Content-Type, X-Requested-With',
                        'X-Random-Shit':'123123123'
                    },
                    url: 'http://localhost:8080/gtItem',
    				params: requestParamTrade
                }).then(function(response) {
                        baseSearchResult = response.data;
                        api.searchResults_trade.data = baseSearchResult;
                        api.setSearchResultsView(views.list)
                    },$http({
                    	
                    	method: 'GET',
                    	headers:{
                            'Access-Control-Allow-Origin': '*',
                            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
                            'Access-Control-Allow-Headers': 'Content-Type, X-Requested-With',
                            'X-Random-Shit':'123123123'
                        },
                        url: 'http://localhost:8080/litem',
        				params: requestParamSupply
                    }).then(function(response) {
                            baseSearchResult = response.data;
                            api.searchResults_supply.data = baseSearchResult;
                            api.setSearchResultsView(views.list)
                        }
                    ,function (error){

               }),function (error){

               }),function (error){

               });
        }
    }
})();